package com.github.kloping.little_web.impl;

import com.alibaba.fastjson.JSON;
import com.github.kloping.little_web.MimeMapping;
import com.github.kloping.little_web.WebExtension;
import com.github.kloping.little_web.annotations.GetMethod;
import com.github.kloping.little_web.annotations.PostMethod;
import com.github.kloping.little_web.annotations.RequestMethod;
import com.github.kloping.little_web.interfaces.RequestManager;
import io.github.kloping.MySpringTool.StarterApplication;
import io.github.kloping.MySpringTool.interfaces.component.ContextManager;
import io.github.kloping.io.ReadUtils;
import io.github.kloping.object.ObjectUtils;
import org.apache.catalina.connector.RequestFacade;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.github.kloping.little_web.WebExtension.filter;
import static io.github.kloping.MySpringTool.partUtils.getExceptionLine;

/**
 * @author github.kloping
 */
public class RequestManagerImpl0 implements RequestManager {
    public static final RequestManagerImpl0 INSTANCE = new RequestManagerImpl0();

    public Map<String, Method> GET_PATH_MAPPING = new HashMap<>();
    public Map<String, Method> POST_PATH_MAPPING = new HashMap<>();
    private ContextManager contextManager;

    @Override
    public void manager(Method method, ContextManager contextManager) throws IllegalAccessException, InvocationTargetException {
        this.contextManager = contextManager;
        WebExtension extension = contextManager.getContextEntity(WebExtension.class);
        String pre = extension.getFatherPath(method.getDeclaringClass());
        String suffix = null;
        if (method.isAnnotationPresent(GetMethod.class)) {
            GetMethod a0 = method.getDeclaredAnnotation(GetMethod.class);
            suffix = filter(a0.value());
            GET_PATH_MAPPING.put(filter(pre + suffix), method);
        } else if (method.isAnnotationPresent(PostMethod.class)) {
            PostMethod a0 = method.getDeclaredAnnotation(PostMethod.class);
            suffix = filter(a0.value());
            POST_PATH_MAPPING.put(filter(pre + suffix), method);
        } else if (method.isAnnotationPresent(RequestMethod.class)) {
            RequestMethod a0 = method.getDeclaredAnnotation(RequestMethod.class);
            suffix = filter(a0.value());
            String n = filter(pre + suffix);
            GET_PATH_MAPPING.put(n, method);
            POST_PATH_MAPPING.put(n, method);
        } else {
            return;
        }
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) {
        String u0 = ((RequestFacade) req).getRequestURI();
        Method method = GET_PATH_MAPPING.get(u0);
        byte[] body = null;
        try {
            if (req.getContentLength() >= 0) {
                body = ReadUtils.readAll(req.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object o = contextManager.getContextEntity(method.getDeclaringClass());
        Object[] ps = AutomaticWiringRequestParameter.INSTANCE.wiring(method, contextManager, req, res, body);
        Object r = null;
        try {
            r = method.invoke(o, ps);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
            StarterApplication.logger.error("in web controller has exception "+getExceptionLine(e));
        }
        if (r != null) {
            String json = null;
            if (ObjectUtils.isBaseOrPack(r.getClass()) || r.getClass() == String.class) {
                json = r.toString();
            } else {
                json = JSON.toJSONString(r);
            }
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            res.setContentType(MimeMapping.INSTANCE.getMimeMap().get("json"));
            res.setContentLength(json.length());
            res.setBufferSize(bytes.length);
            try {
                res.getOutputStream().write(bytes);
                res.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean exist(HttpServletRequest request) {
        if (request instanceof RequestFacade) {
            RequestFacade requestFacade = (RequestFacade) request;
            String url = requestFacade.getRequestURI();
            if (GET_PATH_MAPPING.containsKey(url)) {
                return true;
            } else if (POST_PATH_MAPPING.containsKey(url)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
