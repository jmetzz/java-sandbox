package com.github.jmetzz.guava;

import com.google.common.base.MoreObjects;


/**
 * Created by exi853 on 03/11/2015.
 */
public class SimplePojo {
    private String name;
    private String desc;
    private int qtt;

    public SimplePojo() {
    }

    public SimplePojo(String name, String desc, int qtt) {
        this.name = name;
        this.desc = desc;
        this.qtt = qtt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQtt() {
        return qtt;
    }

    public void setQtt(int qtt) {
        this.qtt = qtt;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("desc", desc)
                .add("qtt", qtt)
                .omitNullValues()
                .toString();
    }
}

