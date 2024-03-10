package com.zzqfsy.solf.model.ability;

import lombok.Data;

@Data
public class AbilityReturnWrapper<E extends AbstractAbilityReturnParam> {
    /**
     * 响应参数
     */
    private E data;

    /**
     * 是否成功
     */
    private Boolean isSuccess;

    /**
     * 错误码、开发排查用、非必填，i18n
     */
    private String errCode;

    /**
     * 响应msg，前端展示用
     */
    private String msg;

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static AbilityReturnWrapper ofSuccess(AbstractAbilityReturnParam abstractAbilityReturnParam) {
        AbilityReturnWrapper<AbstractAbilityReturnParam> abilityReturnWrapper = new AbilityReturnWrapper<>();
        abilityReturnWrapper.setData(abstractAbilityReturnParam);
        abilityReturnWrapper.setMsg("成功");
        abilityReturnWrapper.setErrCode("0");
        abilityReturnWrapper.setIsSuccess(true);
        return abilityReturnWrapper;
    }

    /**
     * 失败
     *
     * @param errCode
     * @param msg
     * @return
     */
    public static AbilityReturnWrapper ofFail(String errCode, String msg) {
        AbilityReturnWrapper<AbstractAbilityReturnParam> abilityReturnWrapper = new AbilityReturnWrapper<>();
        abilityReturnWrapper.setMsg(msg);
        abilityReturnWrapper.setErrCode(errCode);
        abilityReturnWrapper.setIsSuccess(false);
        return abilityReturnWrapper;
    }
}
