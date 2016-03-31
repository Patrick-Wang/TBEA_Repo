package com.tbea.ic.util.json;

import android.annotation.SuppressLint;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tbea.ic.util.json.JsonTypes.SupportedType;

@SuppressLint({ "SimpleDateFormat", "DefaultLocale" })
public class JsonReflector {

//	private static Class<?> getGenericType(Class<?> clazz) {
//		Class<?> cls = null;
//		Type t = clazz.getGenericSuperclass();
//		if (t instanceof ParameterizedType) {
//			Type[] args = ((ParameterizedType) t).getActualTypeArguments();
//			if (args[0] instanceof Class) {
//				cls = (Class<?>) args[0];
//			}
//		}
//		return cls;
//	}

	private static Object getValue(Class<?> cls, Object obj) throws Exception {

		if (obj == null) {
			return null;
		}

		SupportedType type = JsonTypes.typeOf(cls);
		switch (type) {
		case BIGDECIMAL:
			obj = new BigDecimal((Double) obj);
			break;
		case BYTE:
			obj = Byte.valueOf(obj.toString());
			break;
		case UTIL_DATE:
		case SQL_DATE:
		case TIMESTAMP: {
			String time = obj.toString();
			String format = null;
			if (time.indexOf(":") > 0) {
				if (time.indexOf(":") == time.lastIndexOf(":")) {
					format = "yyyy-MM-dd H:mm";
				} else {
					format = "yyyy-MM-dd H:mm:ss";
				}
			} else {
				format = "yyyy-MM-dd";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			switch (type) {
			case UTIL_DATE:
				obj = sdf.parse(time);
				break;
			case SQL_DATE:
				obj = new Date(sdf.parse(time).getTime());
				break;
			case TIMESTAMP:
				obj = new Timestamp(sdf.parse(time).getTime());
				break;
			default:
				break;
			}
		}
			break;
		case OTHER:
			obj = null;
			break;
		default:
			break;
		}
		return obj;
	}

	private static void setValue(Class<?> cls, JSONObject json, String key,
			Object bean, Method method) throws Exception {

		if (!json.has(key)) {
			throw new JsonException("Field " + key + " wasn't included in json object!");
		}
		
		if (json.isNull(key)) {
			return;
		}

		SupportedType type = JsonTypes.typeOf(cls);
		switch (type) {
		case ARRAY:
			method.invoke(
					bean,
					new Object[] { toArray(json.getJSONArray(key),
							cls.getComponentType())});

			break;
		case LIST:
			Type[] types = method.getGenericParameterTypes();
			Type[] parameterArgTypes = ((ParameterizedType) types[0]).getActualTypeArguments();
			method.invoke(
					bean,
					new Object[] { toList(json.getJSONArray(key),(Class<?>) parameterArgTypes[0]) });
			break;
		case OTHER:
			method.invoke(bean,
					new Object[] { toObject(json.getJSONObject(key), cls) });
			break;
		default: {
			Object obj = getValue(cls, json.get(key));
			if (obj != null) {
				method.invoke(bean, new Object[] { obj });
			} else {
				throw new JsonException("unsupported type : " + cls.getName() + " " + json.get(key));
			}
		}
			break;
		}

	}

	public static Object toObject(JSONObject json, Class<?> cls)
			throws Exception {

		if (json == null) {
			return null;
		}

		Object obj = cls.newInstance();
		Method[] methods = cls.getMethods();
		for (int i = 0; i < methods.length; i++) {
			String methodName = methods[i].getName();
			Class<?>[] pcls = methods[i].getParameterTypes();
			if (pcls.length == 1 && methodName.length() > 3
					&& methodName.startsWith("set")) {
				String key = methodName.substring(3, 4).toLowerCase()
						+ methodName.substring(4);
				setValue(pcls[0], json, key, obj, methods[i]);
			}
		}

		return obj;
	}

	public static Object[] toArray(JSONArray arr, Class<?> cls)
			throws Exception {
		if (arr == null) {
			return null;
		}
		return toList(arr, cls).toArray();
	}

	public static List<Object> toList(JSONArray arr, Class<?> cls)
			throws Exception {

		if (arr == null) {
			return null;
		}
		
		List<Object> list = new ArrayList<Object>(arr.length());
		SupportedType type = JsonTypes.typeOf(cls);
		switch (type){
		case LIST:
//			for (int i = 0; i < arr.length(); ++i) {
//				list.add(null);
//				if (arr.get(i) != null) {
//					list.set(i, toList(arr.getJSONArray(i), getGenericType(cls)));
//				}
//			}
			throw new JsonException("unsupported type : List<List<T>> " + arr);
		case ARRAY:
			for (int i = 0; i < arr.length(); ++i) {
				list.add(null);
				if (arr.get(i) != null) {
					list.set(i, toArray(arr.getJSONArray(i),
							cls.getComponentType()));
				}
			}
			break;
		case OTHER:
			for (int i = 0; i < arr.length(); ++i) {
				list.add(null);
				if (arr.get(i) != null) {
					list.set(i, toObject(arr.getJSONObject(i), cls));
				}
			}
			break;
		default:
			for (int i = 0; i < arr.length(); ++i) {
				list.add(null);
				if (arr.get(i) != null) {
					Object obj = getValue(cls, arr.get(i));
					if (obj != null) {
						list.set(i, obj);
					} else {
						throw new JsonException("unsupported type : " + cls.getName() + " " + arr.get(i));
					}
				}
			}
			break;
		}
		return list;
	}

	/**
	 * 将Model转换成JSONObject
	 */
	public static JSONObject coverModelToJSONObject(Object o) throws Exception {
		JSONObject json = new JSONObject();
		Class<? extends Object> clazz = o.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			json.put(f.getName(), invokeMethod(clazz, f.getName(), o));
		}
		return json;
	}

	/**
	 * 将list转换成JSONArray
	 */
	public static JSONArray coverModelToJSONArray(List<?> list)
			throws Exception {
		JSONArray array = null;
		if (list.isEmpty()) {
			return array;
		}
		array = new JSONArray();
		for (Object o : list) {
			array.put(coverModelToJSONObject(o));
		}
		return array;
	}

	private static Object invokeMethod(Class<? extends Object> c,
			String fieldName, Object o) {
		String methodName = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		Method method = null;
		try {
			method = c.getMethod("get" + methodName);
			return method.invoke(o);
		} catch (Exception e) {
			// LogUtil.errorLog(e);
			return "";
		}
	}

}