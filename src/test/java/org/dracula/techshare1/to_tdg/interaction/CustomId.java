package org.dracula.techshare1.to_tdg.interaction;

import org.dracula.techshare1.to_tdg.CustomInteractionIdPostfix;
import org.dracula.techshare1.to_tdg.CustomInteractionIdPrefix;
import org.dracula.techshare1.to_tdg.bo.CustomReqBO;
import org.dracula.techshare1.to_tdg.bo.CustomRespBO;

@CustomInteractionIdPrefix("some-prefix.")
public interface CustomId {

    @CustomInteractionIdPostfix("hello")
    CustomRespBO customInteraction(CustomReqBO reqBO);

}
