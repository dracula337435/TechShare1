package org.dracula.techshare1.to_tdg;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在接口上，自定义本接口中函数代表交易的交易码前缀
 * <p>如“tdg.tc0012”“tdg.tc0016”中的前缀“tdg.”
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomInteractionIdPrefix {

    String value();

}
