package com.dreamfish.javacode.patterns;

/**
 * @author: create by syb
 * @version: v1.0
 * @description: com.dreamfish.javacode.patterns
 * @date:19-7-25 下午3:43
 */
public class SingletonByInnerClass {

    private SingletonByInnerClass() {}

    private static class SingletonHolder {
        private static final SingletonByInnerClass INSTANCE = new SingletonByInnerClass();
    }

    private static SingletonByInnerClass getUniqueInstance() {
        return SingletonHolder.INSTANCE;
    }
}
