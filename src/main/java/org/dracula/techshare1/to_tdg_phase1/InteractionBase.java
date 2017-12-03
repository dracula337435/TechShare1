package org.dracula.techshare1.to_tdg_phase1;

/**
 * 交易基础接口，各交易实现后无需写方法
 * @param <ReqBO>
 * @param <RespBO>
 */
public interface InteractionBase<ReqBO, RespBO> {

    RespBO execute(ReqBO reqBO);

}
