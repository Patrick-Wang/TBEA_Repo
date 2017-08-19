package com.frame.script.el;

import java.lang.reflect.Method;
import java.util.List;

import com.frame.script.util.ClosureMap;
import com.frame.script.util.TypeUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PackingMap extends ClosureMap {

	private final static String METHOD_ETEND_ISARRAY = "isArray";
	private final static String METHOD_ETEND_ISLIST = "isList";
	private final static String METHOD_ETEND_TOJSON = "asJson";
	private final static String METHOD_ETEND_DISTINCT = "distinct";
	private final static String METHOD_ETEND_TRANSPOSE = "transpose";

	Object packageObj;
	int nextSize = 0;
	Method md;

	public Object unpack() {
		return packageObj;
	}

	public PackingMap(Object packageObj) {
		super();
		this.packageObj = packageObj;
	}

	@Override
	protected boolean validate(List<Object> args) {
		if (1 == args.size()) {
			this.md = null;
			Method[] mds = packageObj.getClass().getMethods();
			for (Method md : mds) {
				if (md.getName().equals((String) args.get(0))) {
					this.md = md;
					break;
				}
			}
			if (this.md != null) {
				nextSize = md.getParameterCount() + 1;
			} else {
				return true;
			}
		}
		return args.size() == nextSize;
	}

	@Override
	protected Object onGetProp(List<Object> args) throws Exception {
		if (null == this.md) {
			return invokeExtendMethod(args);
		}
		nextSize = 0;
		Object ret = this;
		if (!args.isEmpty()) {
			args.remove(0);
			if (TypeUtil.typeOf(md.getReturnType(), void.class)) {
				this.md.invoke(packageObj, args.toArray());
			} else {
				ret = this.md.invoke(packageObj, args.toArray());
			}
		} else {
			if (TypeUtil.typeOf(md.getReturnType(), void.class)) {
				this.md.invoke(packageObj);
			} else {
				ret = this.md.invoke(packageObj);
			}
		}

		args.clear();
		return ret;
	}

	private Object invokeExtendMethod(List<Object> args) {
		if (METHOD_ETEND_ISARRAY.equals((String) args.get(0))) {
			return packageObj.getClass().isArray();
		} else if (METHOD_ETEND_ISLIST.equals((String) args.get(0))) {
			return TypeUtil.instanceOf(packageObj, List.class);
		} else if (METHOD_ETEND_TRANSPOSE.equals((String) args.get(0))) {
			if (TypeUtil.instanceOf(packageObj, List.class)) {
				return transpose((List) packageObj);
			}
		} else if (METHOD_ETEND_TOJSON.equals((String) args.get(0))) {
			if (TypeUtil.instanceOf(packageObj, List.class)
					|| packageObj.getClass().isArray()) {
				return JSONArray.fromObject(packageObj).toString();
			} else {
				return JSONObject.fromObject(packageObj).toString();
			}
		} else if (METHOD_ETEND_DISTINCT.equals((String) args.get(0))) {
			if (TypeUtil.instanceOf(packageObj, List.class)) {
				List pkg = (List) packageObj;
				for (int i = 0; i < pkg.size(); ++i) {
					for (int j = pkg.size() - 1; j > i; --j) {
						if ((pkg.get(i) == null && pkg.get(j) == null)
								|| pkg.get(i).equals(pkg.get(j))) {
							pkg.remove(j);
						}
					}
				}
				return pkg;
			}
		}
		return null;
	}

	private Object transposeArray(List<Object[]> matrix) {
		Object retMatrix[][] = new Object[matrix.get(0).length][matrix.size()];
		for (int i = 0; i < matrix.size(); ++i) {
			for (int j = 0; j < matrix.get(i).length; ++j) {
				retMatrix[j][i] = matrix.get(i)[j];
			}
		}
		return retMatrix;
	}

	private Object transpose(List matrix) {
		if (matrix.isEmpty()) {
			return matrix;
		}

		if (matrix.get(0).getClass().isArray()) {
			return transposeArray(matrix);
		}
		if (matrix.get(0) instanceof List) {
			return transposeList(matrix);
		}

		return null;
	}

	private Object transposeList(List<List> matrix) {
		Object retMatrix[][] = new Object[matrix.get(0).size()][matrix.size()];
		for (int i = 0; i < matrix.size(); ++i) {
			for (int j = 0; j < matrix.get(i).size(); ++j) {
				retMatrix[j][i] = matrix.get(i).get(j);
			}
		}
		return retMatrix;
	}

}
