package com.tbea.ic.operation.common.formatter.raw;

public class RawEmptyHandler extends RawAbstractFormatterHandler {

		public RawEmptyHandler(String[] rows, Integer[] cols) {
			super(toList(rows), toList(cols));
		}
		
		public RawEmptyHandler(String[] rows) {
			this(rows, null);
		}
		
		public RawEmptyHandler() {
			this(null, null);
		}


		@Override
		protected String onHandle(String val) {
			return val;
		}

	}
