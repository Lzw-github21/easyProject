package easy.project.note.lambad表达式;

public class Lambda创建线程 {

    public static void main(String[] args) {
        // 非lambda表达式开启一个新的线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新线程中执行的代码 : " + Thread.currentThread().getName());
            }
        }).start();
        System.out.println("主线程中的代码：" + Thread.currentThread().getName());

        //非lambda表达式创建线程
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable中执行的代码 : " + Thread.currentThread().getName());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        // lambda表达式开启一个新的线程
        new Thread(() -> System.out.println("新线程中执行的代码 : " + Thread.currentThread().getName())).start();
        System.out.println("主线程中的代码：" + Thread.currentThread().getName());
    }


}
