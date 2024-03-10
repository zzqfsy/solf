package com.zzqfsy.solf.core.handle;

import com.zzqfsy.solf.model.ability.AbstractAbilityCommandParam;
import com.zzqfsy.solf.model.ability.AbstractAbilityReturnParam;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public interface FlowChainHandle<T extends AbstractAbilityCommandParam, E extends AbstractAbilityReturnParam> {

    /**
     * transactionalHandle
     *
     * @param flowCode
     * @param t
     */
    E transactionalHandle(String flowCode, T t);

    /**
     * handle
     *
     * @param flowCode
     * @param t
     */
    E handle(String flowCode, T t);
}
