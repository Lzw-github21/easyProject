package easy.project.note.设计模式.单例模式.懒汉式线程不安全;

public class Singleton_02 {

    //1. 私有构造方法
    private Singleton_02(){

    }

    //2. 在本类中创建私有静态的全局对象
    private static Singleton_02 instance;


    //3. 通过判断对象是否被初始化,来选择是否创建对象
    public static  Singleton_02 getInstance(){

        if(instance == null){

            instance = new Singleton_02();
        }
        return instance;
    }

}