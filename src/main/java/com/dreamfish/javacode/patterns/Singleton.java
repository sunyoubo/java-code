package com.dreamfish.javacode.patterns;

/**
 * @author: create by syb
 * @version: v1.0
 * @description: com.dreamfish.javacode.patterns
 * @date:19-7-25 下午3:38
 */
public class Singleton {

    // volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行
    private volatile static Singleton uniqueInstance;

    private Singleton() {
    }

    public static Singleton getUniqueInstance() {

        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }

        return uniqueInstance;
    }
}
