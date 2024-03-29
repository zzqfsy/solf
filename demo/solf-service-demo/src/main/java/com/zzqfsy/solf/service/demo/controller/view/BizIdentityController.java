package com.zzqfsy.solf.service.demo.controller.view;

import com.zzqfsy.solf.core.repository.BizIdentityDomainAbilityRepository;
import com.zzqfsy.solf.model.identity.BizIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 业务身份视图
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@RestController
@RequestMapping("/bizIdentity")
public class BizIdentityController {

    @Autowired
    private BizIdentityDomainAbilityRepository bizIdentityDomainAbilityRepository;

    @Autowired
    private FlowChainController flowChainController;

    @RequestMapping("/config")
    public Collection<BizIdentity> config() {
        return bizIdentityDomainAbilityRepository.getAllBizIdentity();
    }
}
