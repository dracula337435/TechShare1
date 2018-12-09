# 技术分享1，动态代理

从实际调用tdg的做法说起，演进  
1. 17年夏天东某积某某项目的做法
1. 动态代理
1. FactoryBean
1. 自动扫描
  
试验了jdk动态代理和cglib动态代理，分别保存运行时生成类，使用jd-gui反编译

## jdk动态代理

测试```ProxyJDKGeneratorTest.test()```试验了jdk动态代理  
```ProxyGenerator.generateProxyClass(...)```源码中可见，是否保存生成的class文件的flag：```saveGeneratedFiles```  
通过系统变量```jdk.proxy.ProxyGenerator.saveGeneratedFiles```控制，启动参数```-Djdk.proxy.ProxyGenerator.saveGeneratedFiles=true```即为生成  
反编译后的代码如下：
```
package com.sun.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import org.dracula.techshare1.to_tdg.bo.CustomReqBO;
import org.dracula.techshare1.to_tdg.bo.CustomRespBO;
import org.dracula.techshare1.to_tdg.bo.TC0016ReqBO;
import org.dracula.techshare1.to_tdg.bo.TC0016RespBO;
import org.dracula.techshare1.to_tdg.interaction.TDG;

public final class $Proxy7
  extends Proxy
  implements TDG
{
  private static Method m1;
  private static Method m2;
  private static Method m4;
  private static Method m3;
  private static Method m0;
  
  public $Proxy7(InvocationHandler paramInvocationHandler)
    throws 
  {
    super(paramInvocationHandler);
  }
  
  public final boolean equals(Object paramObject)
    throws 
  {
    try
    {
      return ((Boolean)this.h.invoke(this, m1, new Object[] { paramObject })).booleanValue();
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  public final String toString()
    throws 
  {
    try
    {
      return (String)this.h.invoke(this, m2, null);
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  public final TC0016RespBO tc0016(TC0016ReqBO paramTC0016ReqBO)
    throws 
  {
    try
    {
      return (TC0016RespBO)this.h.invoke(this, m4, new Object[] { paramTC0016ReqBO });
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  public final CustomRespBO customInteraction(CustomReqBO paramCustomReqBO)
    throws 
  {
    try
    {
      return (CustomRespBO)this.h.invoke(this, m3, new Object[] { paramCustomReqBO });
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  public final int hashCode()
    throws 
  {
    try
    {
      return ((Integer)this.h.invoke(this, m0, null)).intValue();
    }
    catch (Error|RuntimeException localError)
    {
      throw localError;
    }
    catch (Throwable localThrowable)
    {
      throw new UndeclaredThrowableException(localThrowable);
    }
  }
  
  static
  {
    try
    {
      m1 = Class.forName("java.lang.Object").getMethod("equals", new Class[] { Class.forName("java.lang.Object") });
      m2 = Class.forName("java.lang.Object").getMethod("toString", new Class[0]);
      m4 = Class.forName("org.dracula.techshare1.to_tdg.interaction.TDG").getMethod("tc0016", new Class[] { Class.forName("org.dracula.techshare1.to_tdg.bo.TC0016ReqBO") });
      m3 = Class.forName("org.dracula.techshare1.to_tdg.interaction.TDG").getMethod("customInteraction", new Class[] { Class.forName("org.dracula.techshare1.to_tdg.bo.CustomReqBO") });
      m0 = Class.forName("java.lang.Object").getMethod("hashCode", new Class[0]);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new NoSuchMethodError(localNoSuchMethodException.getMessage());
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new NoClassDefFoundError(localClassNotFoundException.getMessage());
    }
  }
}

```
几个重点：
1. 生成的类继承了Proxy，其中有属性InvocationHandler h
1. 生成的类实现了指定接口
1. 把所有的对指定接口的调用转发给了父类中的h
1. 把对Object中equals(...)，toString()，hashCode()的调用也转发给了h
1. Object中其他几个函数：getClass()，notify()，notifyAll()，3个wait(...)，对这几个函数的调用不转发给h

## cglib动态代理

测试```ProxyCglibGeneratorTest.test()```试验了cglib动态代理  
反编译后的代码如下：
```
package org.dracula.techshare1;

import java.lang.reflect.Method;
import net.sf.cglib.core.ReflectUtils;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.dracula.techshare1.to_tdg.bo.CustomReqBO;
import org.dracula.techshare1.to_tdg.bo.CustomRespBO;
import org.dracula.techshare1.to_tdg.bo.TC0016ReqBO;
import org.dracula.techshare1.to_tdg.bo.TC0016RespBO;
import org.dracula.techshare1.to_tdg.interaction.TDG;

public class ProxyCglibGeneratorTest$SomeSuper$$EnhancerByCGLIB$$a008e3eb
  extends ProxyCglibGeneratorTest.SomeSuper
  implements TDG, Factory
{
  private boolean CGLIB$BOUND;
  public static Object CGLIB$FACTORY_DATA;
  private static final ThreadLocal CGLIB$THREAD_CALLBACKS;
  private static final Callback[] CGLIB$STATIC_CALLBACKS;
  private MethodInterceptor CGLIB$CALLBACK_0;
  private static Object CGLIB$CALLBACK_FILTER;
  private static final Method CGLIB$someFunc$0$Method;
  private static final MethodProxy CGLIB$someFunc$0$Proxy;
  private static final Object[] CGLIB$emptyArgs;
  private static final Method CGLIB$equals$1$Method;
  private static final MethodProxy CGLIB$equals$1$Proxy;
  private static final Method CGLIB$toString$2$Method;
  private static final MethodProxy CGLIB$toString$2$Proxy;
  private static final Method CGLIB$hashCode$3$Method;
  private static final MethodProxy CGLIB$hashCode$3$Proxy;
  private static final Method CGLIB$clone$4$Method;
  private static final MethodProxy CGLIB$clone$4$Proxy;
  private static final Method CGLIB$tc0016$5$Method;
  private static final MethodProxy CGLIB$tc0016$5$Proxy;
  private static final Method CGLIB$customInteraction$6$Method;
  private static final MethodProxy CGLIB$customInteraction$6$Proxy;
  
  static void CGLIB$STATICHOOK2()
  {
    CGLIB$THREAD_CALLBACKS = new ThreadLocal();
    CGLIB$emptyArgs = new Object[0];
    Class localClass1 = Class.forName("org.dracula.techshare1.ProxyCglibGeneratorTest$SomeSuper$$EnhancerByCGLIB$$a008e3eb");
    Class localClass2;
    Method[] tmp83_80 = ReflectUtils.findMethods(new String[] { "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", "hashCode", "()I", "clone", "()Ljava/lang/Object;" }, (localClass2 = Class.forName("java.lang.Object")).getDeclaredMethods());
    CGLIB$equals$1$Method = tmp83_80[0];
    CGLIB$equals$1$Proxy = MethodProxy.create(localClass2, localClass1, "(Ljava/lang/Object;)Z", "equals", "CGLIB$equals$1");
    Method[] tmp103_83 = tmp83_80;
    CGLIB$toString$2$Method = tmp103_83[1];
    CGLIB$toString$2$Proxy = MethodProxy.create(localClass2, localClass1, "()Ljava/lang/String;", "toString", "CGLIB$toString$2");
    Method[] tmp123_103 = tmp103_83;
    CGLIB$hashCode$3$Method = tmp123_103[2];
    CGLIB$hashCode$3$Proxy = MethodProxy.create(localClass2, localClass1, "()I", "hashCode", "CGLIB$hashCode$3");
    Method[] tmp143_123 = tmp123_103;
    CGLIB$clone$4$Method = tmp143_123[3];
    CGLIB$clone$4$Proxy = MethodProxy.create(localClass2, localClass1, "()Ljava/lang/Object;", "clone", "CGLIB$clone$4");
    tmp143_123;
    Method[] tmp191_188 = ReflectUtils.findMethods(new String[] { "someFunc", "()Ljava/lang/String;" }, (localClass2 = Class.forName("org.dracula.techshare1.ProxyCglibGeneratorTest$SomeSuper")).getDeclaredMethods());
    CGLIB$someFunc$0$Method = tmp191_188[0];
    CGLIB$someFunc$0$Proxy = MethodProxy.create(localClass2, localClass1, "()Ljava/lang/String;", "someFunc", "CGLIB$someFunc$0");
    tmp191_188;
    Method[] tmp249_246 = ReflectUtils.findMethods(new String[] { "tc0016", "(Lorg/dracula/techshare1/to_tdg/bo/TC0016ReqBO;)Lorg/dracula/techshare1/to_tdg/bo/TC0016RespBO;", "customInteraction", "(Lorg/dracula/techshare1/to_tdg/bo/CustomReqBO;)Lorg/dracula/techshare1/to_tdg/bo/CustomRespBO;" }, (localClass2 = Class.forName("org.dracula.techshare1.to_tdg.interaction.TDG")).getDeclaredMethods());
    CGLIB$tc0016$5$Method = tmp249_246[0];
    CGLIB$tc0016$5$Proxy = MethodProxy.create(localClass2, localClass1, "(Lorg/dracula/techshare1/to_tdg/bo/TC0016ReqBO;)Lorg/dracula/techshare1/to_tdg/bo/TC0016RespBO;", "tc0016", "CGLIB$tc0016$5");
    Method[] tmp269_249 = tmp249_246;
    CGLIB$customInteraction$6$Method = tmp269_249[1];
    CGLIB$customInteraction$6$Proxy = MethodProxy.create(localClass2, localClass1, "(Lorg/dracula/techshare1/to_tdg/bo/CustomReqBO;)Lorg/dracula/techshare1/to_tdg/bo/CustomRespBO;", "customInteraction", "CGLIB$customInteraction$6");
    tmp269_249;
  }
  
  final String CGLIB$someFunc$0()
  {
    return super.someFunc();
  }
  
  public final String someFunc()
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null) {
      return (String)tmp17_14.intercept(this, CGLIB$someFunc$0$Method, CGLIB$emptyArgs, CGLIB$someFunc$0$Proxy);
    }
    return super.someFunc();
  }
  
  final boolean CGLIB$equals$1(Object paramObject)
  {
    return super.equals(paramObject);
  }
  
  public final boolean equals(Object paramObject)
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null)
    {
      Object tmp41_36 = tmp17_14.intercept(this, CGLIB$equals$1$Method, new Object[] { paramObject }, CGLIB$equals$1$Proxy);
      tmp41_36;
      return tmp41_36 == null ? false : ((Boolean)tmp41_36).booleanValue();
    }
    return super.equals(paramObject);
  }
  
  final String CGLIB$toString$2()
  {
    return super.toString();
  }
  
  public final String toString()
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null) {
      return (String)tmp17_14.intercept(this, CGLIB$toString$2$Method, CGLIB$emptyArgs, CGLIB$toString$2$Proxy);
    }
    return super.toString();
  }
  
  final int CGLIB$hashCode$3()
  {
    return super.hashCode();
  }
  
  public final int hashCode()
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null)
    {
      Object tmp36_31 = tmp17_14.intercept(this, CGLIB$hashCode$3$Method, CGLIB$emptyArgs, CGLIB$hashCode$3$Proxy);
      tmp36_31;
      return tmp36_31 == null ? 0 : ((Number)tmp36_31).intValue();
    }
    return super.hashCode();
  }
  
  final Object CGLIB$clone$4()
    throws CloneNotSupportedException
  {
    return super.clone();
  }
  
  protected final Object clone()
    throws CloneNotSupportedException
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null) {
      return tmp17_14.intercept(this, CGLIB$clone$4$Method, CGLIB$emptyArgs, CGLIB$clone$4$Proxy);
    }
    return super.clone();
  }
  
  final TC0016RespBO CGLIB$tc0016$5(TC0016ReqBO paramTC0016ReqBO)
  {
    return super.tc0016(paramTC0016ReqBO);
  }
  
  public final TC0016RespBO tc0016(TC0016ReqBO paramTC0016ReqBO)
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null) {
      return (TC0016RespBO)tmp17_14.intercept(this, CGLIB$tc0016$5$Method, new Object[] { paramTC0016ReqBO }, CGLIB$tc0016$5$Proxy);
    }
    return super.tc0016(paramTC0016ReqBO);
  }
  
  final CustomRespBO CGLIB$customInteraction$6(CustomReqBO paramCustomReqBO)
  {
    return super.customInteraction(paramCustomReqBO);
  }
  
  public final CustomRespBO customInteraction(CustomReqBO paramCustomReqBO)
  {
    MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
    if (tmp4_1 == null)
    {
      tmp4_1;
      CGLIB$BIND_CALLBACKS(this);
    }
    MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
    if (tmp17_14 != null) {
      return (CustomRespBO)tmp17_14.intercept(this, CGLIB$customInteraction$6$Method, new Object[] { paramCustomReqBO }, CGLIB$customInteraction$6$Proxy);
    }
    return super.customInteraction(paramCustomReqBO);
  }
  
  public static MethodProxy CGLIB$findMethodProxy(Signature paramSignature)
  {
    String tmp4_1 = paramSignature.toString();
    switch (tmp4_1.hashCode())
    {
    case -1733750004: 
      if (tmp4_1.equals("customInteraction(Lorg/dracula/techshare1/to_tdg/bo/CustomReqBO;)Lorg/dracula/techshare1/to_tdg/bo/CustomRespBO;")) {
        return CGLIB$customInteraction$6$Proxy;
      }
      break;
    }
  }
  
  public ProxyCglibGeneratorTest$SomeSuper$$EnhancerByCGLIB$$a008e3eb()
  {
    CGLIB$BIND_CALLBACKS(this);
  }
  
  public static void CGLIB$SET_THREAD_CALLBACKS(Callback[] paramArrayOfCallback)
  {
    CGLIB$THREAD_CALLBACKS.set(paramArrayOfCallback);
  }
  
  public static void CGLIB$SET_STATIC_CALLBACKS(Callback[] paramArrayOfCallback)
  {
    CGLIB$STATIC_CALLBACKS = paramArrayOfCallback;
  }
  
  private static final void CGLIB$BIND_CALLBACKS(Object paramObject)
  {
    a008e3eb locala008e3eb = (a008e3eb)paramObject;
    if (!locala008e3eb.CGLIB$BOUND)
    {
      locala008e3eb.CGLIB$BOUND = true;
      Object tmp23_20 = CGLIB$THREAD_CALLBACKS.get();
      if (tmp23_20 == null)
      {
        tmp23_20;
        CGLIB$STATIC_CALLBACKS;
      }
      locala008e3eb.CGLIB$CALLBACK_0 = (// INTERNAL ERROR //
```
几个重点：
1. 生成的类继承了指定类，实现了指定接口和net.sf.cglib.proxy.Factory接口