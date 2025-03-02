package easy.project.note.设计模式.单例模式.懒汉线程安全;

public class Singleton_03 {

    //1. 私有构造方法
    private Singleton_03(){

    }

    //2. 在本类中创建私有静态的全局对象
    private static Singleton_03 instance;


    //3. 通过添加synchronize,保证多线程模式下的单例对象的唯一性
    public static synchronized  Singleton_03 getInstance(){

        if(instance == null){
            instance = new Singleton_03();
        }
        return instance;
    }

}
