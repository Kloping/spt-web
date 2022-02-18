package com.github.kloping.little_web.interfaces;

import io.github.kloping.MySpringTool.interfaces.component.MethodManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author github.kloping
 */
public interface RequestManager extends MethodManager {
    /**
     * request service
     *
     * @param req
     * @param res
     */
    void service(HttpServletRequest req, HttpServletResponse res);

    /**
     * request path exist
     *
     * @param request
     * @return
     */
    boolean exist(HttpServletRequest request);
}
