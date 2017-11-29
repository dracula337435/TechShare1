package org.dracula.techshare1;

public class RemoteDemo {

    public static void main(String[] args){
        long tmp = 0;
        while(true){
            tmp = System.currentTimeMillis();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tmp);
        }
    }

}
