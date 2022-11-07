package com.zzqfsy.solf.core.handle;

/**
 * 领域能力处理
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public interface DomainAbilityHandle {

    /**
     *
     * @param domainName
     * @param abilityName
     * @param t
     * @param returnClazz
     * @param <T>
     * @param <E>
     */
    <T, E> void transactionalHandle(String domainName, String abilityName, T t, Class<E> returnClazz);


    /**
     *
     * @param domainName
     * @param abilityName
     * @param t
     * @param returnClazz
     * @param <T>
     * @param <E>
     */
    <T, E> E transactionalHandleReturn(String domainName, String abilityName, T t, Class<E> returnClazz);


    /**
     *
     * @param domainName
     * @param abilityName
     * @param t
     * @param returnClazz
     * @param <T>
     * @param <E>
     */
    <T, E> void handle(String domainName, String abilityName, T t, Class<E> returnClazz);

    /**
     *
     * @param domainName
     * @param abilityName
     * @param t
     * @param returnClazz
     * @param <T>
     * @param <E>
     */
    <T, E> E handleReturn(String domainName, String abilityName, T t, Class<E> returnClazz);
}
