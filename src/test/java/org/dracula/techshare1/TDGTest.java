package org.dracula.techshare1;

import org.dracula.techshare1.to_tdg.bo.*;
import org.dracula.techshare1.to_tdg_phase1.tdg.TC0016;
import org.junit.Test;

public class TDGTest {

    TdgSao tdgSao = new TdgSao();

    /**
     * 最初的调用形式，直接调用tdg
     */
    @Test
    public void step0(){
        TC0016ReqBO tc0016ReqBO = new TC0016ReqBO();
        TC0016RespBO tc0016RespBO = tdgSao.execute("tdg.tc0016", tc0016ReqBO, TC0016RespBO.class);
        System.out.println(tc0016RespBO);
    }

    /**
     * 演进step1，每个交易一个接口
     */
    TC0016 tc0016 = null;
    @Test
    public void setp1(){
        TC0016ReqBO tc0016ReqBO = new TC0016ReqBO();
        TC0016RespBO tc0016RespBO = tc0016.execute(tc0016ReqBO);
        System.out.println(tc0016RespBO);
    }

}
