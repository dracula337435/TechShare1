package org.dracula.techshare1.to_tdg;

import org.dracula.techshare1.TdgSao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Handler implements InvocationHandler {

    private String prefix = null;

    private TdgSao tdgSao = null;

    private Map<String, String> nameToIdMap = new HashMap<>();

    public Handler(String interactionIdPrefix, TdgSao tdgSao){
        this.prefix = interactionIdPrefix;
        this.tdgSao = tdgSao;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){    //没这个的话，很难受
            return method.invoke(proxy, args);
        }else{
            String interactionId = getInteractionId(prefix, method);
            Class<?> returnType = method.getReturnType();
            if(args.length > 0 ){
                return tdgSao.execute(interactionId, args[0], returnType);
            }else{
                return null;
            }
        }
    }

    public String getInteractionId(String prefix, Method method){
        String name = method.getName();
        String postfix = null;
        //有注解的话，先看注解
        CustomInteractionIdPostfix tmpAnno = method.getAnnotation(CustomInteractionIdPostfix.class);
        if(tmpAnno != null){
            postfix = tmpAnno.value();
        }
        //没有注解的话，用名字
        if(postfix == null){
            postfix = name.toLowerCase();
        }
        String rslt = prefix+postfix;
        nameToIdMap.put(name, rslt);
        return rslt;
    }

}
