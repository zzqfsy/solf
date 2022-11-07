package com.zzqfsy.solf.core.handle;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public interface FlowChainHandle {

    /**
     * transactionalHandle
     *
     * @param flowCode
     * @param t
     * @param returnClazz
     * @param <T>
     * @param <E>
     */
    <T, E> void transactionalHandle(String flowCode, T t, Class<E> returnClazz);

    /**
     * handle
     *
     * @param flowCode
     * @param t
     * @param returnClazz
     * @param <T>
     * @param <E>
     */
    <T, E> void handleByNodeTransactional(String flowCode, T t, Class<E> returnClazz);

    /**
     * handle
     *
     * @param flowCode
     * @param t
     * @param returnClazz
     * @param <T>
     * @param <E>
     */
    <T, E> void handle(String flowCode, T t, Class<E> returnClazz);
}
