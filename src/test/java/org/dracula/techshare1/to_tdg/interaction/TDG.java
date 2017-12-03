package org.dracula.techshare1.to_tdg.interaction;

import org.dracula.techshare1.to_tdg.CustomInteractionIdPostfix;
import org.dracula.techshare1.to_tdg.bo.CustomReqBO;
import org.dracula.techshare1.to_tdg.bo.CustomRespBO;
import org.dracula.techshare1.to_tdg.bo.TC0016ReqBO;
import org.dracula.techshare1.to_tdg.bo.TC0016RespBO;

/**
 * 自动演示，接口名后接上“.”为前缀，函数名为后半部分
 */
public interface TDG {

    /**
     * 自动，交易码后半部分为函数名
     * @param tc0016ReqBO
     * @return
     */
    TC0016RespBO tc0016(TC0016ReqBO tc0016ReqBO);

    /**
     * 指定交易码后半部分，通过注解
     * @param reqBO
     * @return
     */
    @CustomInteractionIdPostfix("hello")
    CustomRespBO customInteraction(CustomReqBO reqBO);

}
