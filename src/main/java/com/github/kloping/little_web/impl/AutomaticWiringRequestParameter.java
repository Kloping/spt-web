package com.github.kloping.little_web.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kloping.little_web.annotations.RequestBody;
import com.github.kloping.little_web.annotations.RequestParm;
import io.github.kloping.MySpringTool.interfaces.component.ContextManager;
import io.github.kloping.object.ObjectUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author github.kloping
 */
public class AutomaticWiringRequestParameter {
    public static final AutomaticWiringRequestParameter INSTANCE = new AutomaticWiringRequestParameter();

    public Object[] wiring(Method method, ContextManager contextManager, ServletRequest req, ServletResponse res, byte[] body) {
        Object[] rs = new Object[method.getParameterTypes().length];
        for (int i = 0; i < method.getParameters().length; i++) {
            Parameter parameter = method.getParameters()[i];
            rs[i] = mather(parameter, req, res, body);
        }
        return rs;
    }

    private Object mather(Parameter parameter, ServletRequest req, ServletResponse res, byte[] bytes) {
        Class type = parameter.getType();
        if (parameter.isAnnotationPresent(RequestParm.class)) {
            RequestParm parm = parameter.getDeclaredAnnotation(RequestParm.class);
            String name = parm.value();
            if (req.getParameterMap().containsKey(name)) {
                String value = req.getParameter(name);
                return getMatherObj(type, value);
            }
        }
        if (parameter.isAnnotationPresent(RequestBody.class)) {
            String value = new String(bytes);
            return getMatherObj(type, value);
        }
        return null;
    }

    private Object getMatherObj(Class type, String value) {
        if (ObjectUtils.isBaseOrPack(type)) {
            return ObjectUtils.asPossible(type, value);
        } else if (type == String.class) {
            return value;
        } else {
            try {
                Object o = JSON.parseObject(value).toJavaObject(type);
                return o;
            } catch (Exception e) {
                return null;
            }
        }
    }
}
