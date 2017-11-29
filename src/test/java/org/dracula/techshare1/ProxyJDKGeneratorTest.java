package org.dracula.techshare1;

import org.dracula.techshare1.to_tdg.interaction.TDG;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 */
public class ProxyJDKGeneratorTest {

    /**
     * 得到动态代理生成的类的.class文件
     */
    @Test
    public void test(){
        //得到.class文件的byte[]
        byte[] byteArray = ProxyGenerator.generateProxyClass("SomeName", new Class[]{TDG.class});
        //写文件
        writeByteArrayToFile(byteArray, "e:/SomeName.class");
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
