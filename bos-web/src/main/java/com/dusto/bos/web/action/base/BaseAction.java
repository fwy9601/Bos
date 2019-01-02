package com.dusto.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.dusto.bos.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    DetachedCriteria detachedCriteria = null;
    protected PageBean pageBean = new PageBean();

    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

    /**
     * 将java指定对象转为json
     * 
     * @param o
     * @param exclueds
     */
    public void java2Json(Object o, String[] exclueds) {
        // 将pageBean对象转为json，输出流写回页面
        // 指定那些属性不需要装json
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclueds);
        String json = JSONObject.fromObject(o, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将java指定对象转为json
     * 
     * @param o
     * @param exclueds
     */
    public void java2Json(List o, String[] exclueds) {
        // 将pageBean对象转为json，输出流写回页面
        // 指定那些属性不需要装json
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(exclueds);
        String json = JSONArray.fromObject(o, jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String LIST = "list";
    public static final String HOME = "home";

    // 模型对象
    protected T model;

    public T getModel() {
        return model;
    }

    // 在构造方法中动态获取实体类型，通过反射创建model对象
    public BaseAction() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        Class<T> entityClass = (Class<T>) actualTypeArguments[0];
        detachedCriteria = DetachedCriteria.forClass(entityClass);
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            model = entityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
