package com.happy.util.base;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @author chengxia
 * @version 2017-01-13 12:02
 */
public class ParameterizedTypeTest {
    
    @Test
    public void parameterizedType(){
        Children<List,Map> children = new Children(){};
        Type genericSuperClass = children.getClass().getGenericSuperclass();
        System.out.println(genericSuperClass.getTypeName());
        System.out.println(genericSuperClass.getClass());
        
    }
    @Test
    public void parameterizedTypeArgs(){
        Children<List, Map> children = new Children<List, Map>(){
            
        };
        Type genericSuperClass = children.getClass().getGenericSuperclass();
        System.out.println(genericSuperClass.getClass());
        ParameterizedType parameterizedType = (ParameterizedType)genericSuperClass;
        System.out.println(parameterizedType.getTypeName());
        System.out.println(parameterizedType.getActualTypeArguments().length);
        System.out.println(parameterizedType.getActualTypeArguments()[0].getTypeName());
        System.out.println(parameterizedType.getActualTypeArguments()[0].getClass());
        System.out.println(parameterizedType.getRawType());
        System.out.println(parameterizedType.getOwnerType());
    }
    
}
class Parent<T,V> {
    
}
abstract class Children<T,V> extends Parent<T,V> {
    
}
