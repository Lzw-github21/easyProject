package easyProject.java内部类.静态内部类;

/**
 * 外部类、内部类定义
 */
public class Outer {

    private int outerVariable = 1;

    /**
     * 外部类定义的属性(重名)
     */
    private int commonVariable = 2;

    private static int outerStaticVariable = 3;

    static {
        System.out.println("Outer的静态块被执行了……");
    }

    /**
     * 成员方法
     */
    public void outerMothod() {
        System.out.println("我是外部类的outerMethod方法");
    }

    /*
     *	静态方法
     */
    public static void outerStaticMethod() {
        System.out.println("我是外部类的outerStaticMethod静态方法");
    }


    /**
     * 静态内部类
     */
    public static class Inner {
        /**
         * 成员信息
         */
        private int innerVariable = 10;
        private int commonVariable = 20;

        static {
            System.out.println("Outer.Inner的静态块执行了……");
        }

        private static int innerStaticVariable = 30;

        /**
         * 成员方法
         */
        public void innerShow() {
            System.out.println("innerVariable:" + innerVariable);
            System.out.println("内部的commonVariable:" + commonVariable);
            System.out.println("outerStaticVariable:"+outerStaticVariable);
            outerStaticMethod();
        }

        /**
         * 静态方法
         */
        public static void innerStaticShow() {
            //被调用时会先加载Outer类
            outerStaticMethod();
            System.out.println("outerStaticVariable"+outerStaticVariable);
        }
    }

    /**
     * 外部类的内部如何和内部类打交道
     */
    public static void callInner() {
        System.out.println(Inner.innerStaticVariable);
        Inner.innerStaticShow();
    }
}

