package org.dracula.techshare1.to_tdg;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在方法上，自定义交易码后半部分
 * <p>如“tdg.tc0012”“tdg.tc0016”中后半部分的“tc0012”“tc0016”
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomInteractionIdPostfix {

    String value();

}
