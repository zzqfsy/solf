package com.zzqfsy.solf.view;

import com.zzqfsy.solf.model.ability.AbilityMethodObject;
import com.zzqfsy.solf.model.identity.BizIdentityMethodObject;
import lombok.Builder;
import lombok.Data;

/**
 * 业务身份领域能力Vo
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Data
@Builder
public class BizIdentityDomainAbilityVo {

    /**
     * 业务身份名称
     */
    private String bizIdentityName;

    /**
     * 业务身份编码
     */
    private String bizIdentityCode;

    /**
     * 业务身份识别方法
     */
    private BizIdentityMethodObject bizIdentityMethodObject;

    /**
     * 领域编码
     */
    private String domainCode;

    /**
     * 领域名称
     */
    private String domainName;

    /**
     * 能力场景
     */
    private String abilityScenario;

    /**
     * 能力名称
     */
    private String abilityName;

    /**
     * 能力顺序
     */
    private Integer abilityOrder;

    /**
     * 能力方法
     */
    private AbilityMethodObject abilityMethodObject;
}
