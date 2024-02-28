package easyProject.java内部类.匿名内部类;

/**
 *	外部内、内部类
 */
public class Outer {

    public static IAnimal getInnerInstance(String speak){
        return new IAnimal(){
            @Override
            public void speak(){
                System.out.println(speak);
            }};
        //注意上一行的分号必须有
    }

    public static void main(String[] args){
        //调用的speak()是重写后的speak方法。
        Outer.getInnerInstance("小狗汪汪汪！").speak();
    }
}

