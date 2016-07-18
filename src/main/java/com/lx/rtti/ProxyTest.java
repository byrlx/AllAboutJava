package com.lx.rtti;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by douhua on 4/13/16.
 */
public class ProxyTest {
    @SuppressWarnings("unchecked")
    public static <T> T create(final Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String clsName = service.getSimpleName();
                String mthdName = method.getName();
                System.out.printf("ClassName:%s, MethodName:%s, Args:", clsName, mthdName);
                for (Object arg : args) {
                    System.out.printf("%s", arg);
                }
                System.out.println();
                return null;
            }
        });
    }
}
