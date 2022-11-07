package com.zzqfsy.solf.service.demo.controller.view;

import com.zzqfsy.solf.core.repository.BizIdentityDomainAbilityRepository;
import com.zzqfsy.solf.view.BizIdentityDomainAbilityVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 业务身份领域能力视图
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@RestController
@RequestMapping("/domain/ability")
public class BizIdentityDomainAbilityController {

    @Resource
    private BizIdentityDomainAbilityRepository bizIdentityDomainAbilityRepository;

    @RequestMapping("/config")
    public List<BizIdentityDomainAbilityVo> config() {
        return bizIdentityDomainAbilityRepository.getAllConfig();
    }
}
