package com.gfl.common.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class ExtensibleEnum implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(ExtensibleEnum.class);

    private static Map<Class<? extends ExtensibleEnum>, Map<Object, ExtensibleEnum>> Enums  = new HashMap<>();

    private int code;

    private String name;

    @Override
    public String toString() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    protected ExtensibleEnum(int code) {
        this(code, null);
    }

    protected ExtensibleEnum(int code, String name) {
        this.code = code;
        this.name = name;
        register(this);
    }

    protected void register(ExtensibleEnum child) {
        Map<Object, ExtensibleEnum> eMap = Enums.get(child.getClass());
        if (eMap == null) {
            eMap = new HashMap<>();
            Enums.put(child.getClass(), eMap);
        } else {
            if (eMap.containsKey(code)) {
                logger.warn("{} duplicated Code[{}]", child.getClass().getSimpleName(), code);
            }
        }
        eMap.put(code, child);
        if (!"".equals(name)) {
            eMap.put(name, child);
        }
    }

    private static Map<Object, ExtensibleEnum> findClassInfosMap(Class type) {
        Map<Object, ExtensibleEnum> map = Enums.get(type);
        if (map == null) {
            try {
                Class.forName(type.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            map = Enums.get(type);
        }

        return map;
    }

    public static <T extends ExtensibleEnum> T valueOf(String name, Class<T> type) {
        return (T) findClassInfosMap(type).get(name);
    }

    public static <T extends ExtensibleEnum> T findByCode(int code, Class<T> type) {
        return (T) findClassInfosMap(type).get(code);
    }

    public static Collection<ExtensibleEnum> values(Class<?> type) {
        return findClassInfosMap(type).values();
    }

}