package com.tender.jpa.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.tender.jpa.service.ResolutionAdapter;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/producer-app")
public class EventController{



    @Autowired
    ResolutionAdapter resolutionAdapter;

    @GetMapping("/findById/{name}/{id}")
    Object all(@PathVariable("name") String name,@PathVariable("id") Integer id) {
        return   resolutionAdapter.getResolutionAdapter(name,id);
    }

    @PostMapping("/saves/{name}")
    Object save(@PathVariable("name") String name,@RequestBody Object object) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JsonProcessingException, NoSuchFieldException, JSONException {
        return   resolutionAdapter.getResolutionAdapterSaver(name,object,"POST");
    }

    @PutMapping("/updates/{name}")
    Object update(@PathVariable("name") String name,@RequestBody Object object) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JsonProcessingException, NoSuchFieldException, JSONException {
        return   resolutionAdapter.getResolutionAdapterSaver(name,object,"PUT");
    }

    @DeleteMapping("/delete/{name}/{id}")
    void delete(@PathVariable("name") String name,@PathVariable("id") Integer id) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JsonProcessingException, NoSuchFieldException, JSONException {
        resolutionAdapter.getResolutionAdapterDeleter(name,id);
    }

    @PostMapping("/executeQueryNative")
    Object retrieveByName(@RequestBody Object object) {
        return resolutionAdapter.getResolutionAdapterNativeQuery(object);
    }










}
