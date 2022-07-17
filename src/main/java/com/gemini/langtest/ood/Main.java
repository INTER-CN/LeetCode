package com.gemini.langtest.ood;

/**
 * @author 天何
 * @date 2022/7/15
 */
public class Main {

    public static void main(String[] args) {
        ParentClass obj1 = new ChildClass();
        System.out.println(obj1);
        ChildClass obj2 = new ChildClass();
        System.out.println(obj2);
    }
}
