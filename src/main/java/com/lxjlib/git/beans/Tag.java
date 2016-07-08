package com.lxjlib.git.beans;

/**
 * 记录代码仓库的tag信息
 * Created by douhua on 2/26/16.
 */
public class Tag {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public Tag(String name, String ci){
        this.name = name;
        this.ci = ci;
    }

    private String name;
    private String ci; //该tag对应的commit id

}
