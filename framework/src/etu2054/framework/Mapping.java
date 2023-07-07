package etu2054.framework;

import etu2054.framework.annotations.UrlAnnot;

public class Mapping {
    String className;
    String method;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Mapping() {
    }

    public Mapping(String className, String method) {
        setClassName(className);
        setMethod(method);
    }
}
