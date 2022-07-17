package com.gemini.langtest.ood;

/**
 * @author 天何
 * @date 2022/7/15
 */
public class ParentClass {

    static {
        System.out.println("ParentClass static block");
    }

    {
        System.out.println("ParentClass non-static block");
    }

    public ParentClass() {
        System.out.println("ParentClass Constructor");
    }

}
