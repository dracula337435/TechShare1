package org.dracula.techshare1.to_tdg.interaction;

import org.dracula.techshare1.to_tdg.CustomInteractionIdPostfix;
import org.dracula.techshare1.to_tdg.CustomInteractionIdPrefix;
import org.dracula.techshare1.to_tdg.bo.CustomReqBO;
import org.dracula.techshare1.to_tdg.bo.CustomRespBO;

/**
 * 自定义交易码演示，用注解
 */
@CustomInteractionIdPrefix("some-prefix.")
public interface CustomId {

    @CustomInteractionIdPostfix("hello")
    CustomRespBO customInteraction(CustomReqBO reqBO);

}
