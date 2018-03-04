package com.rahmania.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Transformer extends DozerBeanMapper {

    @PostConstruct
     public void setMappingFiles(){
        List<String> mappingFiles = Arrays.asList("mapper.xml");
        setMappingFiles(mappingFiles);
    }

    public <T> T transform(Object source, Class<T> clazz) {
        if (source == null)
            return null;
        return map(source, clazz);
    }

    public <T> List<T> transform(Iterable<?> sources, Class<T> destinationClass) {
        if (sources == null)
            return null;
        ArrayList<T> targets = new ArrayList<T>();
        for (Object source : sources) {
            targets.add(map(source, destinationClass));
        }
        return targets;
    }

}