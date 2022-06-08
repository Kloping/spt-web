package io.github.kloping.little_web.interfaces;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author github.kloping
 */
public interface ServletIntercept {
    /**
     * 拦截器
     *
     * @param req
     * @param res
     * @return
     */
    boolean onService(ServletRequest req, ServletResponse res);
}
