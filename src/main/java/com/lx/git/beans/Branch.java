package com.lx.git.beans;

/**
 * 记录相关的branch信息
 *
 *
 * Created by douhua on 2/26/16.
 */
public final class Branch {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewestCi() {
        return newestCi;
    }

    public void setNewestCi(String newestCi) {
        this.newestCi = newestCi;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Branch(String name, String newestCi, Long updateTime) {
        this.name = name;
        this.newestCi = newestCi;
        this.updateTime = updateTime;
    }

    private String name;
    private String newestCi;  //该branch对应的最新commit id
    private Long updateTime; //该branch的最新提交时间, 一般与newestCI对应
}
