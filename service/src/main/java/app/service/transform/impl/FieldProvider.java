package app.service.transform.impl;


import app.infra.util.ReflectionUtil;

import java.util.List;

/**
 * Base functionality of the field preparation
 * */
public class FieldProvider {
    public List<String> getFieldNames(Class<?> source, Class<?> dest) {
        return ReflectionUtil.findSimilarFields(source, dest);
    }
}
