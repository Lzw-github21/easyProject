package easyProject.java内部类.局部内部类;

/**
 *	外部类、内部类
 */
public class Outer {
    /**
     * 属性和方法
     */
    private int outerVariable = 1;
    /**
     * 外部类定义的属性
     */
    private int commonVariable = 2;
    /**
     * 静态的信息
     */
    private static int outerStaticVariable = 3;

    /**
     * 成员外部方法
     */
    public void outerMethod() {
        System.out.println("我是外部类的outerMethod方法");
    }

    /**
     * 静态外部方法
     */
    public static void outerStaticMethod() {
        System.out.println("我是外部类的outerStaticMethod静态方法");
    }

    /**
     * 程序的入口
     */
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.outerCreatMethod(100);
    }

    /**
     * 成员方法，内部定义局部内部类
     */
    public void outerCreatMethod(int value) {
        /**
         * 女性
         */
        boolean sex = false;

        /**
         * 局部内部类，类前不能有访问修饰符
         */
        class Inner {

            private int innerVariable = 10;
            private int commonVariable = 20;
            /**
             *	局部内部类方法
             */
            public void innerShow() {
                System.out.println("innerVariable:" + innerVariable);
                //局部变量
                System.out.println("是否男性:" + sex);
                System.out.println("参数value:" + value);
                //调用外部类的信息
                System.out.println("outerVariable:" + outerVariable);
                System.out.println("内部的commonVariable:" + commonVariable);
                System.out.println("外部的commonVariable:" + Outer.this.commonVariable);
                System.out.println("outerStaticVariable:" + outerStaticVariable);
                outerMethod();
                outerStaticMethod();
            }
        }
        //局部内部类只能在方法内使用
        Inner inner = new Inner();
        inner.innerShow();
    }
}

