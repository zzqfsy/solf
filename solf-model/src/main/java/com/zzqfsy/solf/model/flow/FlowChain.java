package com.zzqfsy.solf.model.flow;

import com.zzqfsy.solf.annotations.flow.FlowNodeOrderSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public class FlowChain {

    /**
     * 名称
     */
    private String name;

    /**
     * 名称
     */
    private String code;

    /**
     * 第一个
     */
    private FlowNode first;

    public FlowChain(String name, String code) {
        this.name = name;
        this.code = code;
        this.first = null;
    }


    public boolean isEmpty() {
        return this.first == null;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public FlowNode getFirst() {
        return first;
    }

    /**
     * 插入
     *
     * @param domainName
     * @param abilityName
     */
    public void insert(String domainName, String abilityName) {
        insert(domainName, abilityName, FlowNodeOrderSpec.LOWEST_PRECEDENCE);
    }

    /**
     * 插入
     *
     * @param domainName
     * @param abilityName
     * @param nodeOrder
     */
    public void insert(String domainName, String abilityName, Integer nodeOrder) {
        insert("", "", domainName, abilityName, nodeOrder);
    }

    /**
     * 插入
     *
     * @param name
     * @param code
     * @param domainName
     * @param abilityName
     * @param nodeOrder
     */
    public void insert(String name, String code, String domainName, String abilityName, Integer nodeOrder) {
        FlowNode newLink = new FlowNode(name, code, domainName, abilityName, nodeOrder);
        FlowNode previous = null;
        FlowNode current = first;

        while ((current != null) && (nodeOrder > current.order)) {
            previous = current;
            current = current.next;
        }

        if (current == null && previous == null) {
            first = newLink;
            newLink.prev = null;
            newLink.next = null;
        } else if (current == null) {
            previous.next = newLink;
            newLink.prev = previous;
            newLink.next = null;
        } else if (previous == null) {
            newLink.prev = null;
            newLink.next = current;
            current.prev = newLink;
            first = newLink;
        } else {
            previous.next = newLink;
            newLink.prev = previous;
            newLink.next = current;
            current.prev = newLink;
        }

    }

    /**
     * 删除头部
     */
    public void removeHeader() {
        if (isEmpty()) {
            return;
        }

        first = first.next;
        if (first != null && first.prev != null) {
            first.prev = null;
        }
    }

    /**
     * 指定删除
     *
     * @param domainName
     * @param abilityName
     */
    public void remove(String domainName, String abilityName) {
        FlowNode searchNode = this.first;

        if (searchNode == null) {
            return;
        }

        if (!searchNode.getDomainName().equals(domainName) ||
                !searchNode.getAbilityName().equals(abilityName)) {
            searchNode = searchNode.next;
        }

        if (searchNode == null) {
            return;
        }

        if (searchNode.prev != null && searchNode.next != null) {
            searchNode.prev.next = searchNode.next;
            searchNode.next.prev = searchNode.prev;
        } else if (searchNode.prev == null) {
            searchNode = searchNode.next;
            searchNode.prev = null;
        } else if (searchNode.next == null) {
            searchNode.prev.next = null;
        }
    }

    /**
     * 打印
     */
    public void displayList() {
        System.out.println("Begin List (first-->last):");
        FlowNode current = first;
        while (current != null) {
            current.displayNode();
            current = current.next;
        }
        System.out.println("End List (first-->last) \n");
    }

    /**
     * 获取所有节点
     *
     * @return
     */
    public List<FlowNode> getAllNode() {
        List<FlowNode> result = new ArrayList<>();

        FlowNode flowNode = this.first;
        while (flowNode != null) {
            result.add(flowNode);

            flowNode = flowNode.next;
        }

        return result;
    }

    public static void main(String[] args) {
        FlowChain linkListSort = new FlowChain("入库单接单", "stockInNoticeDomain");

        linkListSort.insert("stockInDomain", "handler", 40);
        linkListSort.insert("stockInDomain", "valid", 20);
        linkListSort.insert("stockInDomain", "assmebly", 30);
        linkListSort.insert("stockInDomain", "assmebly2", 31);
        linkListSort.insert("stockInDomain", "assmebly3", 32);
        linkListSort.insert("stockInDomain", "assmebly4", 33);

        linkListSort.displayList();

        linkListSort.remove("stockInDomain", "assmebly");
        linkListSort.displayList();

        linkListSort.removeHeader();
        linkListSort.displayList();

        linkListSort.remove("stockInDomain", "assmebly3");
        linkListSort.displayList();

        linkListSort.removeHeader();
        linkListSort.displayList();
    }

}
