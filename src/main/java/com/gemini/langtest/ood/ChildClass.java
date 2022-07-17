package com.gemini.langtest.ood;

/**
 * @author 天何
 * @date 2022/7/15
 */
public class ChildClass extends ParentClass {

    static {
        System.out.println("ChildClass static block");
    }

    {
        System.out.println("ChildClass non-static block");
    }

    public ChildClass() {
        System.out.println("ChildClass Constructor");
    }
}
