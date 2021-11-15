package com.shaprj.test.iocframework.core;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private ObjectFactory objectFactory = new ObjectFactory();
    private Map<String, Object> cashe = new HashMap<>();

    public <T> T getInjectable(Class<T> clazz) {
        var key = clazz.getName();
        if (!cashe.containsKey(key)) {
            objectFactory.createObject(this, clazz);
        }
        return (T) cashe.get(key);
    }

    public void putInjectableToCashe(String key, Object value) {
        cashe.put(key, value);
    }
}
