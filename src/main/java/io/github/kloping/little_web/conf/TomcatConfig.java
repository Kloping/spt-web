package io.github.kloping.little_web.conf;

import io.github.kloping.little_web.WebExtension;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author github.kloping
 */
public class TomcatConfig {
    public static final String CLASSPATH_KEY = "classpath:";
    private Integer port = 80;
    private String staticPath = CLASSPATH_KEY + "static";
    private String name = "tomcat-default";
    private String errorPage = "/error.html";
    private Charset charset = StandardCharsets.UTF_8;

    public TomcatConfig() {
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
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
