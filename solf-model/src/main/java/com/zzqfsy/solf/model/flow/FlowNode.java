package com.zzqfsy.solf.model.flow;

import com.zzqfsy.solf.annotations.flow.FlowNodeOrderSpec;

import java.beans.Transient;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public class FlowNode {

    /**
     * 名称
     */
    private String name;

    /**
     * 名称
     */
    private String code;

    /**
     * 领域名称
     */
    private String domainName;

    /**
     * 能力名称
     */
    private String abilityName;

    /**
     * 顺序
     */
    public Integer order;

    /**
     * 上一个
     */
    public FlowNode prev;

    /**
     * 下一个
     */
    public FlowNode next;

    /**
     * 流程节点
     *
     * @param domainName
     * @param abilityName
     */
    public FlowNode(String domainName, String abilityName) {
        this.domainName = domainName;
        this.abilityName = abilityName;
        this.order = FlowNodeOrderSpec.LOWEST_PRECEDENCE;
    }

    public FlowNode(String domainName, String abilityName, Integer order) {
        this.domainName = domainName;
        this.abilityName = abilityName;
        this.order = order;
    }

    public FlowNode(String name, String code, String domainName, String abilityName, Integer order) {
        this.name = name;
        this.code = code;
        this.domainName = domainName;
        this.abilityName = abilityName;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public Integer getOrder() {
        return order;
    }

    @Transient
    public FlowNode getPrev() {
        return prev;
    }

    @Transient
    public FlowNode getNext() {
        return next;
    }

    public void displayNode(){
        System.out.println("Node:" + this.toString());
    }

    @Override
    public String toString(){
        return name + ":" + code + ":" +domainName + ":" + abilityName + ":" + order;
    }


}
