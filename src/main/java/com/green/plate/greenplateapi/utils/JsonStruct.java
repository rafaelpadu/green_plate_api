package com.green.plate.greenplateapi.utils;

import java.util.HashMap;
import java.util.Map;

public class JsonStruct {
    private String message = null;
    private String code = null;
    private Map<String, Object> data = new HashMap<String, Object>();

    public Map<String, Object> getData() {
        return data;
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
