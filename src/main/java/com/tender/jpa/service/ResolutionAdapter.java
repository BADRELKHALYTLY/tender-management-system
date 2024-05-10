package com.tender.jpa.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tender.jpa.dao.ClassDao;
import com.google.gson.Gson;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResolutionAdapter {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ClassDao classDao;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public Object getResolutionAdapter(String name,Integer id){
        JpaRepository jpaRepository = null;
        try {
            jpaRepository = (JpaRepository) applicationContext.getBean(Class.forName("com.tender.jpa.repository."+name+"Repository"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return jpaRepository.findById(id).get();
    }

    public Object getResolutionAdapterSaver(String name,Object object,String methodType) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JsonProcessingException, NoSuchFieldException, JSONException {
        JpaRepository jpaRepository = null;
        Class<?> clazz = Class.forName("com.tender.jpa.pojo."+name);
        ObjectMapper objectMapper = new ObjectMapper();

        modifyStringValues((LinkedHashMap<String, Object>) object);
        Gson gson = new Gson();

        try {
            jpaRepository = (JpaRepository) applicationContext.getBean(Class.forName("com.tender.jpa.repository."+name+"Repository"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(methodType.equals("POST") || methodType.equals("PUT"))

            return jpaRepository.save(gson.fromJson( transformString(object.toString()), clazz));

        return null;
    }

    public static List<Object> parseLinkedHashMap(LinkedHashMap<String, Object> map) {
        List<Object> parsedValues = new ArrayList<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String || value instanceof Integer || value instanceof Double) {
                parsedValues.add(value);
            } else if (value instanceof ArrayList) {
                ArrayList<?> arrayList = (ArrayList<?>) value;
                for (Object obj : arrayList) {
                    if (obj instanceof String || obj instanceof Integer || obj instanceof Double) {
                        parsedValues.add(obj);
                    }
                }
            }
        }

        return parsedValues;
    }

    public void getResolutionAdapterDeleter(String name,Integer id) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JsonProcessingException, NoSuchFieldException, JSONException {
        JpaRepository jpaRepository = null;
        try {
            jpaRepository = (JpaRepository) applicationContext.getBean(Class.forName("com.tender.jpa.repository."+name+"Repository"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

         jpaRepository.deleteById(id);

    }

    public void modifyStringValues(LinkedHashMap<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                // If the value is a string, add '  ' around it
                entry.setValue("'" + value + "'");
            }
        }
    }

    public   String transformString(String input) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;

        for (char ch : input.toCharArray()) {
            if (ch == '_') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(ch));
                    capitalizeNext = false;
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();

    }


    public Object getResolutionAdapterNativeQuery(Object object)  {
        return null;
    }
}
