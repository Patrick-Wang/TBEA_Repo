package com.frame.script.el;

public interface ElContext {
    Object onGetObject(String key);
    boolean hasObject(String key);
    void storeObject(String key, Object obj);
}