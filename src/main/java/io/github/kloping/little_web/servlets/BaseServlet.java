package io.github.kloping.little_web.servlets;

import io.github.kloping.little_web.WebExtension;
import io.github.kloping.little_web.interfaces.ServletIntercept;
import org.apache.catalina.servlets.DefaultServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author github.kloping
 */
public class BaseServlet extends DefaultServlet {
    private WebExtension extension;
    public List<ServletIntercept> intercepts = new ArrayList<>();

    public BaseServlet(WebExtension webExtension) {
        this.extension = webExtension;
    }

    @Override
    protected String getPathPrefix(HttpServletRequest request) {
        return super.getPathPrefix(request);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            for (ServletIntercept intercept : intercepts) {
                if (intercept.onService(req, res)) return;
            }
            if (extension.requestManagerImpl0.exist((HttpServletRequest) req)) {
                extension.requestManagerImpl0.service((HttpServletRequest) req, (HttpServletResponse) res);
                return;
            }
        }
        super.service(req, res);
    }
}
