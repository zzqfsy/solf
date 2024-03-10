package com.zzqfsy.solf.core.repository;

import com.zzqfsy.solf.model.identity.BizIdentity;
import com.zzqfsy.solf.model.view.BizIdentityDomainAbilityVo;

import java.util.List;

/**
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
public interface BizIdentityDomainAbilityRepository {

    /**
     * 获取所有的业务身份
     */
    List<BizIdentity> getAllBizIdentity();

    /**
     * 获取指定的业务身份领域能力
     *
     * @param bizIdentityCode 业务身份编码
     * @return
     */
    List<BizIdentityDomainAbilityVo> getByBizIdentity(String bizIdentityCode);

    /**
     * 获取指定的业务身份领域能力
     *
     * @param domain 领域名称
     * @return
     */
    List<BizIdentityDomainAbilityVo> getByDomainName(String domain);

    /**
     * 获取所有的业务身份领域能力
     *
     * @return
     */
    List<BizIdentityDomainAbilityVo> getAllConfig();
}
