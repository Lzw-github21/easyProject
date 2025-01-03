package easy.project.note.设计模式.单例模式.饿汉式;

public class Singleton_01 {

    String name;
    String age;

    //1. 私有构造方法
    private Singleton_01(){
        name = "张三";
        age = "18";
    }

    //2. 在本类中创建私有静态的全局对象
    private static Singleton_01 instance = new Singleton_01();


    //3. 提供一个全局访问点,供外部获取单例对象
    public static  Singleton_01 getInstance(){

        return instance;
    }

    public static void main(String[] args) {
        Singleton_01 s = Singleton_01.getInstance();
        System.out.println(s);
    }
}
