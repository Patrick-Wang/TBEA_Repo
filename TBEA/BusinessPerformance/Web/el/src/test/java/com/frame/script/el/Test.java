package com.frame.script.el;

public class Test {
    public static void main(String[] args) throws Exception {

        ELParser el = new ELParser(new ElContext() {
            @Override
            public Object onGetObject(String key) {
                return null;
            }

            @Override
            public boolean hasObject(String key) {
                return false;
            }

            @Override
            public void storeObject(String key, Object obj) {

            }
        });

        System.out.println(el.parser("${'${abcdef}'}").get(0).value());
    }
}
