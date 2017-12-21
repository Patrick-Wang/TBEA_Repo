package com.frame.script.el;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.frame.script.el.querier.ExpressionQuerier;
import com.frame.script.el.querier.IndexQuerier;
import com.frame.script.el.querier.ObjectQuerier;
import com.frame.script.el.querier.Querier;
import com.frame.script.util.TypeUtil;
import com.util.tools.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ELExpression {

    static final Set<String> jsKeyWords = new HashSet<String>();
    static final Pattern varPattern = Pattern.compile("var\\s+");

    static {
        jsKeyWords.add("indexOf");
        jsKeyWords.add("true");
        jsKeyWords.add("false");
        jsKeyWords.add("null");
        jsKeyWords.add("undefined");
        jsKeyWords.add("var");
    }

    int start;
    int end;
    String expression;
    ElContext elContext;
    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    public static ScriptEngine getJse() {
        return jse;
    }

    public ELExpression(int start, int end, String expression,
                        ElContext elContext) {
        super();
        this.start = start;
        this.end = end;
        this.expression = expression;
        this.elContext = elContext;
    }

    public String exp() {
        return expression;
    }

    public int start() {
        return start;
    }

    public int end() {
        return end;
    }

    private String makeGetMethodName(String propName) {
        String methodName = null;
        if (!propName.isEmpty()) {
            if (propName.length() == 1) {
                methodName = "get" + propName.toUpperCase();
            } else {
                methodName = "get" + propName.substring(0, 1).toUpperCase() + propName.substring(1);
            }
        }
        return methodName;
    }

    private Method getMethod(Object obj, String name) {
        try {
            return obj.getClass().getMethod(name);
        } catch (NoSuchMethodException | SecurityException e) {
            Object o = e;
        }
        return null;
    }


    private Object invokeMethod(Method md, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object result = obj;
        if (TypeUtil.typeOf(md.getReturnType(), void.class)) {
            md.invoke(obj);
        } else {
            result = md.invoke(obj);
        }
        return result;
    }

    private Object getObjectProp(Object obj, String propName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method md = getMethod(obj, propName);
        Object result;
        if (null == md) {
            String getMdName = makeGetMethodName(propName);
            md = getMethod(obj, getMdName);
            if (null == md) {
                result = new PackingMap(obj, elContext).get(propName);
                if (null == result) {
                    result = new PackingMap(obj, elContext).get(getMdName);
                }
            } else {
                result = invokeMethod(md, obj);
            }
        } else {
            result = invokeMethod(md, obj);
        }
        return result;
    }

    private int getInt(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else if (obj instanceof Double) {
            return ((Double) obj).intValue();
        } else if (obj instanceof Long) {
            return ((Long) obj).intValue();
        } else {
            return (int) obj;
        }
    }

    private Object getPropByIndex(Object obj, List<Object> indexs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        for (int i = 0; i < indexs.size() && null != obj; ++i) {
            if (obj instanceof List) {
                obj = ((List) obj).get(getInt(indexs.get(i)));
            } else if (obj.getClass().isArray()) {
                obj = ((Object[]) obj)[getInt(indexs.get(i))];
            } else if (obj instanceof JSONArray) {
                obj = ((JSONArray) obj).get(getInt(indexs.get(i)));
            } else if (obj instanceof JSONObject) {
                JSONObject jsonObj = (JSONObject) obj;
                obj = jsonObj.get(indexs.get(i));
            } else if (obj instanceof Map) {
                if ("remove".equals(indexs.get(i)) || "put".equals(indexs.get(i))) {
                    obj = getObjectProp(obj, (String) indexs.get(i));
                } else {
                    obj = ((Map) obj).get(indexs.get(i));
                }
            } else if (null != indexs.get(i) && TypeUtil.isString(indexs.get(i).getClass())) {
                obj = getObjectProp(obj, (String) indexs.get(i));
            }
        }
        return obj;
    }

    private Object getPropByName(Object obj, String propName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Object propValue = null;
        if (null != obj) {
            if (obj instanceof JSONObject) {
                JSONObject jsonObj = (JSONObject) obj;
                propValue = jsonObj.get(propName);
            } else if (obj instanceof Map) {
                if ("remove".equals(propName) || "put".equals(propName)) {
                    propValue = getObjectProp(obj, propName);
                } else {
                    propValue = ((Map) obj).get(propName);
                }
            } else {
                propValue = getObjectProp(obj, propName);
            }
        }
        return propValue;
    }

    private boolean isNumber(Object ob) {
        return ob instanceof Integer ||
                ob instanceof Long ||
                ob instanceof Double ||
                ob instanceof Float;
    }

    private boolean isString(Object ob) {
        return ob instanceof String;
    }

    //user.take[a.b[]] + user.walk
    public Object value() throws Exception {

        String expressTmp = expression;
        int offset = 0;
        boolean isObject = false;
        Object obj = null;
        Querier querier = new ExpressionQuerier(expression);
        while (querier.hasNext()) {
            try {
                obj = parseObject(querier.next());
                if (null == obj || isNumber(obj) || isString(obj) || TypeUtil.isBoolean(obj.getClass())) {
                    if (isString(obj)) {
                        obj = ((String) obj).replaceAll("\r", "\\\\r");
                        obj = ((String) obj).replaceAll("\n", "\\\\n");
                    }
                    String objVal = expressTmp.substring(0, querier.start()) + obj;
                    expressTmp = objVal + expressTmp.substring(querier.end());
                    offset = objVal.length();
                    querier.reset(expressTmp, offset);
                } else {
                    isObject = true;
                    break;
                }
            } catch (ELInitObjectNotExist e) {
                if (!jsKeyWords.contains(e.getMessage())) {
                    if (!Pattern.compile("var\\s+" + e.getMessage()).matcher(expression).find()) {
//						e.printStackTrace();
                    }
                }
            }
        }

        if (!isObject && !expressTmp.equals(obj)) {
            try {
                obj = jse.eval(expressTmp);
            } catch (ScriptException e) {
                obj = null;
                e.printStackTrace();
            }
            if (null != obj &&
                    !TypeUtil.isDouble(obj.getClass()) &&
                    !TypeUtil.isInt(obj.getClass()) &&
                    !TypeUtil.isBoolean(obj.getClass()) &&
                    !TypeUtil.isString(obj.getClass())) {

                if (TypeUtil.isLong(obj.getClass())) {
                    return ((Long) obj).intValue();
                }

                return expressTmp;
            }
        }
        return obj;
    }

    List<Object> getIndexs(String indexs) throws Exception {
        List<Object> indxs = new ArrayList<Object>();
        Querier querier = new IndexQuerier(indexs);
        while (querier.hasNext()) {
            String val = StringUtil.trim(querier.next());
            Object index = TypeUtil.asInt(val);
            if (null != index) {
                indxs.add(index);
                continue;
            }

            index = TypeUtil.asBoolean(val);
            if (null != index) {
                indxs.add(index);
                continue;
            }

            index = TypeUtil.asString(val);
            if (null != index) {
                indxs.add(index);
                continue;
            }

            ELExpression exp = new ELExpression(0, val.length(), val, this.elContext);
            indxs.add(exp.value());
        }
        return indxs;
    }

    private Object loadStartObject(String objExp)
            throws Exception {
        Object ret = null;
        int index = objExp.indexOf('[');
        String objName = objExp;
        if (index > 0) {
            objName = objExp.substring(0, index);
            if (!elContext.hasObject(objName)) {
                throw new ELInitObjectNotExist(objName);
            }
            ret = elContext.onGetObject(objName);
            ret = getPropByIndex(ret, getIndexs(objExp.substring(index)));
        } else {
            if (!elContext.hasObject(objName)) {
                throw new ELInitObjectNotExist(objName);
            }
            ret = elContext.onGetObject(objName);
        }
        return ret;
    }

    private Object parseObject(String exp) throws
            Exception {
        Querier querier = new ObjectQuerier(exp);
        Object obj = null;

        if (querier.hasNext()) {
            obj = loadStartObject(querier.next());
        }

        String objExp = null;
        int index = -1;
        while (querier.hasNext()) {
            objExp = querier.next();
            index = objExp.indexOf('[');
            if (index > 0) {
                obj = getPropByName(obj, StringUtil.trim(objExp.substring(0, index)));
                obj = getPropByIndex(obj, getIndexs(objExp.substring(index)));
            } else {
                obj = getPropByName(obj, objExp);
            }
        }
        return obj;
    }
}