package org.dracula.techshare1;

import org.dracula.techshare1.to_tdg.MyFactoryBean;
import org.dracula.techshare1.to_tdg.ScannerConfigurer;
import org.dracula.techshare1.to_tdg.bo.CustomReqBO;
import org.dracula.techshare1.to_tdg.bo.CustomRespBO;
import org.dracula.techshare1.to_tdg.bo.TC0016ReqBO;
import org.dracula.techshare1.to_tdg.bo.TC0016RespBO;
import org.dracula.techshare1.to_tdg.interaction.CustomId;
import org.dracula.techshare1.to_tdg.interaction.TDG;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TDGTest2.Conf.class)
public class TDGTest2 {

    /**
     * 配置
     */
    @Configuration
    public static class Conf{

        @Bean
        TdgSao tdgSao(){
            return new TdgSao();
        }

        @Bean
        MyFactoryBean<CustomId> factoryBean(){
            MyFactoryBean<CustomId> factoryBean = new MyFactoryBean<>();
            factoryBean.setTdgSao(tdgSao());
            return factoryBean;
        }

        @Bean
        ScannerConfigurer configurer(){
            ScannerConfigurer configurer = new ScannerConfigurer();
            configurer.setBasePackage("org.dracula.techshare1.to_tdg.interaction");
            configurer.setFactoryBeanName("factoryBean");
            return configurer;
        }

    }


    @Autowired
    TDG tdg;

    @Autowired
    CustomId customId;

    /**
     * 演进step3,
     * <p>每个交易一个接口中的函数，带有自动扫描
     */
    @Test
    public void step3(){
        //
        TC0016ReqBO tc0016ReqBO = new TC0016ReqBO();
        TC0016RespBO tc0016RespBO1 = tdg.tc0016(tc0016ReqBO);
        System.out.println(tc0016RespBO1);
        //
        CustomReqBO reqBO = new CustomReqBO();
        CustomRespBO respBO = customId.customInteraction(reqBO);
        System.out.println(respBO);
    }

}
