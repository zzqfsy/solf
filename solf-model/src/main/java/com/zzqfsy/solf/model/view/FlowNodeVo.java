package com.zzqfsy.solf.model.view;

import com.zzqfsy.solf.model.flow.FlowNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 流程能力Vo
 *
 * @author zzqfsy
 * @email zzqfsy@gmail.com
 * Created on 2022/10/18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowNodeVo {

    /**
     * 业务身份名称
     */
    private String chainName;

    /**
     * 业务身份编码
     */
    private String chainCode;

    /**
     * 业务身份识别方法
     */
    private List<FlowNode> flowNodeList = new ArrayList<>();
}
