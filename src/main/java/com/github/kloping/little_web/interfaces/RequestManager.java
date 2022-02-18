package com.github.kloping.little_web.interfaces;

import io.github.kloping.MySpringTool.interfaces.component.MethodManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
    void service(ServletRequest req, ServletResponse res);

    /**
     * request path exist
     *
     * @param request
     * @return
     */
    boolean exist(ServletRequest request);
}
