package com.github.kloping.little_web.page;

/**
 * @author github.kloping
 */
public class ErrorPage extends org.apache.tomcat.util.descriptor.web.ErrorPage {
    private String url;

    public ErrorPage(String url) {
        super();
        this.url = url;
    }

    @Override
    public int getErrorCode() {
        return super.getErrorCode();
    }

    @Override
    public void setErrorCode(int errorCode) {
        super.setErrorCode(errorCode);
    }

    @Override
    public void setErrorCode(String errorCode) {
        super.setErrorCode(errorCode);
    }

    @Override
    public String getExceptionType() {
        return super.getExceptionType();
    }

    @Override
    public void setExceptionType(String exceptionType) {
        super.setExceptionType(exceptionType);
    }

    @Override
    public String getLocation() {
        return url == null ? super.getLocation() : url;
    }

    @Override
    public void setLocation(String location) {
        super.setLocation(location);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
