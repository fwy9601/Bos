package com.dusto.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    public static final String HOME = "home";
    
    //模型对象
    protected T model;
    public T getModel() {
        return model;
    }

    //在构造方法中动态获取实体类型，通过反射创建model对象
    public BaseAction(){
        ParameterizedType superclass = (ParameterizedType)this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>)actualTypeArguments[0];
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        
    }
}
