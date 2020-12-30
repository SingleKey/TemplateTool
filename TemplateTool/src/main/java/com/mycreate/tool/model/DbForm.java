package com.mycreate.tool.model;

import java.io.Serializable;

public class DbForm implements Serializable {

    private static final long serialVersionUID = -121412544L;

    private String username;
    private String password;
    private String url;
    private String version;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DbForm{");
        sb.append("username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
