package com.zzqfsy.solf.core.handle;


import com.zzqfsy.solf.model.identity.BizIdentity;

/**
 * 业务身份处理
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public interface BizIdentityHandle {

    /**
     * 业务身份识别
     *
     * @param bizIdentityCode
     * @param t
     * @param <T>
     * @return
     */
    <T> BizIdentity bizIdentityRecognize(String bizIdentityCode, T t);
}
