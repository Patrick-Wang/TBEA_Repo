package com.tbea.ic.operation.reportframe.el;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ELParser {

	static Pattern elPattern = Pattern.compile("\\$\\{[^\\$]*\\}");

	public static interface ObjectLoader {
		Object onGetObject(String key);
	}

	ObjectLoader loader;

	public ELParser(ObjectLoader loader) {
		this.loader = loader;
	}

	public List<ELExpression> parser(String express) {
		List<ELExpression> exps = new ArrayList<ELExpression>();
		if (null != express && !express.isEmpty()) {
			Matcher matcher = elPattern.matcher(express);
			while (matcher.find()) {
				String val = matcher.group();
				exps.add(new ELExpression(matcher.start(), matcher.end(), val
						.substring(2, val.length() - 1).replaceAll("\\s", ""),
						loader));
			}
		}
		return exps;
	}
}
