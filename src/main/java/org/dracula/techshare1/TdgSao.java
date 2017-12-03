package org.dracula.techshare1;

/**
 * 模拟对tdg的调用
 */
public class TdgSao {

    /**
     * 各产品都能封装到的程度
     * @param interactionId 交易码
     * @param reqBO 交易请求对象
     * @param returnTypeClass 预期交易返回类型Class
     * @return 交易返回对象
     */
    public <T> T execute(String interactionId, Object reqBO, Class<T> returnTypeClass){
        System.out.println("TdgSao.execute");
        System.out.println("interactionId = [" + interactionId + "], reqBO = [" + reqBO + "], returnTypeClass = [" + returnTypeClass + "]");
        return null;
    }

}
