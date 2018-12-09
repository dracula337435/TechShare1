package org.dracula.techshare1;

import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.dracula.techshare1.to_tdg.interaction.TDG;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 *
 */
public class ProxyCglibGeneratorTest {

    /**
     * 测试cglib在运行时生成的子类，得到byte[]写文件，再用jd-gui反编译，以供观察
     * jd-gui反编译会递归扫描当前目录，舍弃原来放在e盘根目录的做法，改为<b>先写一个不存在的目录，记得新建</b>
     */
    @Test
    public void test(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SomeSuper.class);
        enhancer.setInterfaces(new Class[]{TDG.class});
        enhancer.setCallback(new SomeInterceptor());

        Object o = enhancer.create();
//        System.out.println(o.someFunc());

        try {
            byte[] bytes = DefaultGeneratorStrategy.INSTANCE.generate(enhancer);
            ProxyJDKGeneratorTest.writeByteArrayToFile(bytes, "e:/non-exist/tmp2.class");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class SomeSuper{

        public String someFunc(){
            System.out.println("hello world");
            return "0";
        }

    }

    public static class SomeInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            return null;
        }
    }

}
