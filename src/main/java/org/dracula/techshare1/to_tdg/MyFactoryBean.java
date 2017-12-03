package org.dracula.techshare1.to_tdg;

import org.dracula.techshare1.TdgSao;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

import java.lang.reflect.Proxy;

/**
 * FactoryBean + 动态代理
 * @param <T>
 */
public class MyFactoryBean<T> implements FactoryBean<T> {

    private Class<T> itfc = null;

    private TdgSao tdgSao;

    private String simulator_switch = null;

    @SuppressWarnings("unckecked")  //动态代理一定是这个类型的
    @Nullable
    @Override
    public T getObject() throws Exception {
        //TODO 校验。1不要有重载函数。如果有同名的话，后续比较麻烦。2入参有且仅有1个。3返回值不为void

        //
        String prefix = getInteractionIdPrefix(itfc);
        return (T)Proxy.newProxyInstance(
                itfc.getClassLoader(),
                new Class<?>[]{itfc},
                new Handler(prefix, tdgSao));
    }

    public static String getInteractionIdPrefix(Class<?> itfc){
        String prefix = null;
        //有注解的话，先看注解
        CustomInteractionIdPrefix tmpAnno = itfc.getAnnotation(CustomInteractionIdPrefix.class);
        if(tmpAnno != null){
            prefix = tmpAnno.value();
        }
        //没有注解的话，用名字
        if(prefix == null){
            prefix = itfc.getSimpleName().toLowerCase() + ".";
        }
        return prefix;
    }

    @Nullable
    @Override
    public Class<T> getObjectType() {
        return itfc;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Class<T> getItfc() {
        return itfc;
    }

    public void setItfc(Class<T> itfc) {
        this.itfc = itfc;
    }

    public TdgSao getTdgSao() {
        return tdgSao;
    }

    public void setTdgSao(TdgSao tdgSao) {
        this.tdgSao = tdgSao;
    }
}
