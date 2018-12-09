package org.dracula.techshare1;

import org.dracula.techshare1.to_tdg.Handler;
import org.dracula.techshare1.to_tdg.interaction.TDG;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 *
 */
public class ProxyJDKGeneratorTest {

    /**
     * 得到动态代理生成的类的.class文件，再用jd-gui反编译，以供观察
     */
    @Test
    public void test(){
        Object obj = Proxy.newProxyInstance(TDG.class.getClassLoader(), new Class[]{TDG.class}, new Handler(null, null));
        System.out.println(obj.getClass());
    }

    /**
     * jdk11，sun.misc.ProxyGenerator移入java.lang.reflect包，访问权限为包，用不到了
     */
    public void previousTest(){
//        //得到.class文件的byte[]
//        byte[] byteArray = ProxyGenerator.generateProxyClass("SomeName", new Class[]{TDG.class});
//        //写文件
//        writeByteArrayToFile(byteArray, "e:/SomeName.class");
    }

    public static void writeByteArrayToFile(byte[] byteArray, String filePath) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(fos !=null){
            try {
                fos.write(byteArray);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
