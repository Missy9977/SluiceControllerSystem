package com.sluice.data;

public class KvInfo {
    private String id;

    private String type;

    private String name;

    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "KvInfo{" + "id='" + id + '\'' + ", type='" + type + '\'' + ", name='" + name + '\'' + ", value='"
            + value + '\'' + '}';
    }
}
