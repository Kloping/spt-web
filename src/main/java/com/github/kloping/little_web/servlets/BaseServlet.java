package com.github.kloping.little_web.servlets;

import com.github.kloping.little_web.impl.RequestManagerImpl0;
import org.apache.catalina.servlets.DefaultServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author github.kloping
 */
public class BaseServlet extends DefaultServlet {
    @Override
    protected String getPathPrefix(HttpServletRequest request) {
        return super.getPathPrefix(request);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        if (RequestManagerImpl0.INSTANCE.exist(req)) {
            RequestManagerImpl0.INSTANCE.service(req, res);
        }
        super.service(req, res);
    }
}
