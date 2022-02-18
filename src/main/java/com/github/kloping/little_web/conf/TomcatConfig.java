package com.github.kloping.little_web.conf;

import com.github.kloping.little_web.WebExtension;

/**
 * @author github.kloping
 */
public class TomcatConfig {
    private Integer port = 80;
    private String staticPath = "static";
    private String name = "tomcat-default";
    private String errorPage = null;

    public static TomcatConfig DEFAULT = new TomcatConfig();

    public TomcatConfig() {
    }

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = WebExtension.filter(errorPage);
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getStaticPath() {
        return staticPath;
    }

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
