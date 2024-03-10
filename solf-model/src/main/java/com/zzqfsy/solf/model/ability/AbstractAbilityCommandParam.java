package com.zzqfsy.solf.model.ability;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AbstractAbilityCommandParam implements Serializable {

    /**
     * 业务身份
     */
    private String bizCode;

    /**
     * 场景
     */
    private String scenario;

    /**
     * 业务实例Id(如订单号)
     */
    private String bizInstanceId;

    private HashMap<String, Object> extension = new HashMap<>();

    /**
     * 不要直接调用此方法，建议使用 addExtend 方法
     */
    public HashMap<String, Object> getExtension() {
        return extension;
    }

    /**
     * @param extension the extend to set
     */
    public void setExtension(HashMap<String, Object> extension) {
        this.extension = extension;
    }

    public void addExtend(String key, Object obj) {
        extension.put(key, obj);
    }

    /**
     * 按照类型作为key存储
     * @param obj
     */
    public void addExtendByType(Object obj) {
        String key = obj.getClass().getTypeName();
        if (obj instanceof List) {
            key = List.class.getTypeName();
        } else if (obj instanceof Map) {
            key = Map.class.getTypeName();
        } else if (obj instanceof Set) {
            key = Set.class.getTypeName();
        }
        extension.put(key, obj);
    }

    public Object getExtend(String key) {
        return extension.get(key);
    }

    /**
     * 按照类型作为key取值
     * @param clazz
     * @return
     */
    public  <T> T getExtendByType(Class clazz) {
        String key = clazz.getTypeName();
        return (T)extension.get(key);
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getBizInstanceId() {
        return bizInstanceId;
    }

    public void setBizInstanceId(String bizInstanceId) {
        this.bizInstanceId = bizInstanceId;
    }
}
