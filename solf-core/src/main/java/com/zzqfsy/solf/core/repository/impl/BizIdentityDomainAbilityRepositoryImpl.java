package com.zzqfsy.solf.core.repository.impl;

import com.zzqfsy.solf.core.context.impl.BizIdentityContext;
import com.zzqfsy.solf.core.context.impl.DomainAbilityContext;
import com.zzqfsy.solf.core.repository.BizIdentityDomainAbilityRepository;
import com.zzqfsy.solf.model.ability.AbilityMethodObject;
import com.zzqfsy.solf.model.identity.BizIdentity;
import com.zzqfsy.solf.model.identity.BizIdentityMethodObject;
import com.zzqfsy.solf.model.view.BizIdentityDomainAbilityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Component
public class BizIdentityDomainAbilityRepositoryImpl implements BizIdentityDomainAbilityRepository {

    private List<BizIdentityDomainAbilityVo> list = null;
    private Boolean initialized = false;

    @Autowired
    private BizIdentityContext bizIdentityContext;
    @Autowired
    private DomainAbilityContext domainAbilityContext;

    @Override
    public List<BizIdentity> getAllBizIdentity() {
        return initAndGetAll().stream()
                .map(bizIdentityDomainAbilityVo -> BizIdentity.Builder.aBizIdentity()
                        .code(bizIdentityDomainAbilityVo.getBizIdentityCode())
                        .name(bizIdentityDomainAbilityVo.getBizIdentityName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<BizIdentityDomainAbilityVo> getByBizIdentity(String bizIdentityCode) {
        return initAndGetAll().stream()
                .filter(bizIdentityDomainAbilityVo ->
                        bizIdentityDomainAbilityVo.getBizIdentityCode().equals(bizIdentityCode))
                .collect(Collectors.toList());
    }

    @Override
    public List<BizIdentityDomainAbilityVo> getByDomainName(String domain) {
        return initAndGetAll().stream()
                .filter(bizIdentityDomainAbilityVo ->
                        bizIdentityDomainAbilityVo.getDomainCode().equals(domain))
                .collect(Collectors.toList());
    }

    @Override
    public List<BizIdentityDomainAbilityVo> getAllConfig() {
        return initAndGetAll();
    }

    /**
     * 初始化并获取所有
     *
     * @return
     */
    private List<BizIdentityDomainAbilityVo> initAndGetAll() {
        if (list != null && initialized) {
            return list;
        }

        return initAll();
    }

    /**
     * 初始化列表
     *
     * @return
     */
    private List<BizIdentityDomainAbilityVo> initAll() {
        if (list == null) {
            list = new ArrayList<>();
        }

        // domain name -> ability name -> bean name, method
        Map<String, Map<String, LinkedList<AbilityMethodObject>>> map = domainAbilityContext.getDomainAbilityBeanMethodMap();
        for (Map.Entry<String, Map<String, LinkedList<AbilityMethodObject>>> domainMapEntry : map.entrySet()) {
            String domainCode = domainMapEntry.getKey();

            for (Map.Entry<String, LinkedList<AbilityMethodObject>> abilityLinkedListEntry : domainMapEntry.getValue().entrySet()) {
                String abilityScenario = abilityLinkedListEntry.getKey();

                for (AbilityMethodObject abilityMethodObject : abilityLinkedListEntry.getValue()) {

                    String bizIdentityCode = abilityMethodObject.getAbilitySpec().bizIdentityCode();
                    BizIdentityMethodObject bizIdentityMethodObject = null;
                    Map<String, BizIdentityMethodObject> bizIdentityMethodObjectMap = bizIdentityContext.getBizIdentityBeanMethodMap().get(bizIdentityCode);
                    if (bizIdentityMethodObjectMap != null) {
                        bizIdentityMethodObject = bizIdentityMethodObjectMap.get(abilityMethodObject.getParameterType().toString());
                    }

                    list.add(BizIdentityDomainAbilityVo.builder()
                            .domainCode(domainCode)
                            .domainName(abilityMethodObject.getDomainSpec().name())
                            .abilityScenario(abilityScenario)
                            .abilityName(abilityMethodObject.getAbilitySpec().name())
                            .abilityOrder(abilityMethodObject.getOrder())
                            .abilityMethodObject(abilityMethodObject)
                            .bizIdentityCode(bizIdentityCode)
                            .bizIdentityName(bizIdentityMethodObject == null ? "" : bizIdentityMethodObject.getBizIdentitySpec().name())
                            .bizIdentityMethodObject(bizIdentityMethodObject)
                            .build());
                }
            }
        }

        initialized = true;
        return list;
    }
}
