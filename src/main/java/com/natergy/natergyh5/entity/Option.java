package com.natergy.natergyh5.entity;

public class Option    {
    private String name;
    private String id;
    private String pos;

    @Override
    public String toString() {
        return "Option{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", pos='" + pos + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPos() {
        return pos;
    }
}
