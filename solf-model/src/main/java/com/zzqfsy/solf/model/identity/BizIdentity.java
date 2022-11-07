package com.zzqfsy.solf.model.identity;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 业务身份
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public class BizIdentity implements Serializable {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 配置
     */
    private ConcurrentHashMap<String, String> configMap;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConcurrentHashMap<String, String> getConfigMap() {
        return configMap;
    }

    public void setConfigMap(ConcurrentHashMap<String, String> configMap) {
        this.configMap = configMap;
    }

    public static final class Builder {
        private String code;
        private String name;
        private ConcurrentHashMap<String, String> configMap;

        private Builder() {
        }

        public static Builder aBizIdentity() {
            return new Builder();
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder configMap(ConcurrentHashMap<String, String> configMap) {
            this.configMap = configMap;
            return this;
        }

        public BizIdentity build() {
            BizIdentity bizIdentity = new BizIdentity();
            bizIdentity.setCode(code);
            bizIdentity.setName(name);
            bizIdentity.setConfigMap(configMap);
            return bizIdentity;
        }
    }
}
