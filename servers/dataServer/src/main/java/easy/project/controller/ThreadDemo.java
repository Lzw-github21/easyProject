package easy.project.controller;

/**
 * @author 李志威
 * @Description
 * @date 2023/6/29
 */
public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread() +"1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread() + "2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread() + "3");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");

        t1.start();
        t1.join();//该方法会等待t1执行完毕，再执行下边的内容

        t2.start();
        t2.join();

        t3.start();
        t3.join();
    }
}

