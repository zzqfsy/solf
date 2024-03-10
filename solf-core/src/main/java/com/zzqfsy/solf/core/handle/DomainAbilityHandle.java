package com.zzqfsy.solf.core.handle;

import com.zzqfsy.solf.model.ability.AbstractAbilityCommandParam;
import com.zzqfsy.solf.model.ability.AbstractAbilityReturnParam;

/**
 * 领域能力处理
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public interface DomainAbilityHandle<T extends AbstractAbilityCommandParam, E extends AbstractAbilityReturnParam> {

    /**
     *
     * @param domainName
     * @param abilityName
     * @param t
     * @param returnClazz
     * @param <T>
     * @param <E>
     */
    E transactionalHandle(String domainName, String abilityName, T t);

    /**
     *
     * @param domainName
     * @param abilityName
     * @param t
     * @param returnClazz
     * @param <T>
     * @param <E>
     */
    E handle(String domainName, String abilityName, T t);
}
