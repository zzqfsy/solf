package com.zzqfsy.solf.core.handle;

import com.alibaba.fastjson.JSON;
import com.zzqfsy.solf.model.ability.AbilityReturnWrapper;
import com.zzqfsy.solf.model.ability.AbstractAbilityCommandParam;
import com.zzqfsy.solf.model.ability.AbstractAbilityReturnParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

@Slf4j
public abstract class AbstractAbility<T extends AbstractAbilityCommandParam, E extends AbstractAbilityReturnParam>
        implements BeanFactoryAware {
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    protected abstract AbilityReturnWrapper<E> checkBizIdentityMatch(T abilityPo);

    protected AbilityReturnWrapper<E> checkHandler(T abilityPo){
        return AbilityReturnWrapper.ofSuccess(null);
    }

    protected abstract AbilityReturnWrapper<E> checkIdempotence(T abilityPo);

    protected abstract AbilityReturnWrapper<E> executeAbility(T abilityPo);

    public AbilityReturnWrapper<E> execute(T abilityPo){
        // 校验业务身份匹配
        AbilityReturnWrapper<E> checkBizIdentityMatchResult = checkBizIdentityMatch(abilityPo);
        if (null != checkBizIdentityMatchResult && !checkBizIdentityMatchResult.getIsSuccess()) {
            log.debug("[{}]能力业务身份校验失败,参数=[{}]", this.getClass().getName(), JSON.toJSONString(abilityPo));
            return null;
        }

        // 校验入参
        AbilityReturnWrapper<E> checkHandlerResult = checkHandler(abilityPo);
        if (null != checkHandlerResult && !checkHandlerResult.getIsSuccess()) {
            log.error("[{}]能力前置校验失败,参数=[{}]", this.getClass().getName(), JSON.toJSONString(abilityPo));
            return checkHandlerResult;
        }
        // 校验幂等
        AbilityReturnWrapper<E> checkIdempotenceResult = checkIdempotence(abilityPo);
        if (null != checkIdempotenceResult && !checkIdempotenceResult.getIsSuccess()) {
            log.info("[{}]能力幂等,参数=[{}]", this.getClass().getName(), JSON.toJSONString(abilityPo));
            return checkIdempotenceResult;
        }
        // 执行
        return executeAbility(abilityPo);
    }

}
