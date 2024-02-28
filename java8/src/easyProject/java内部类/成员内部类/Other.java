package easyProject.java内部类.成员内部类;

/*
 *	其他类使用成员内部类
 */
public class Other {

    public static void main(String[] args) {
        //外部类对象
        Outer outer = new Outer();
        Outer.Inner inner2 = new Outer().new Inner();
        //创造内部类对象
        Outer.Inner inner =  outer.new Inner();
        inner.innerShow();
        /*
         * 可在Outer中定义get方法，获得Inner对象,那么使用时，只需outer.getInnerInstance()即可。
         * public Inner getInnerInstance(Inner类的构造方法参数){
         *   return new Inner(参数);
         * }
         */
    }
}

