package com.sluice.data;

public class KvInfo {
    private String id;

    private String dataType;

    private String name;

    private String value;

    private String timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "KvInfo{" + "id='" + id + '\'' + ", dataType='" + dataType + '\'' + ", name='" + name + '\''
            + ", value='" + value + '\'' + ", timestamp='" + timestamp + '\'' + '}';
    }
}
