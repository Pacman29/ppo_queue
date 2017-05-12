import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by pacman29 on 11.05.17.
 */
public class Main {

    private static Queue<Integer> queue = new Queue<Integer>();

    public static class ThreadInsert extends Thread {
        private final Random random = new Random();
        public ThreadInsert(){
            super("insert thread");
        }

        public void run(){
            try {
                while (true) {
                    System.out.printf("Поток %s\n",Thread.currentThread().getName());
                    Integer value = random.nextInt() % 100;
                    System.out.printf("вставка в очередь числа %d\n", value);
                    queue.push(value);
                    Thread.sleep(ThreadLocalRandom.current().nextLong(10,100));
                }
            } catch (InterruptedException e){
                System.out.printf("Поток %s прерван\n",Thread.currentThread().getName());
            }

        }
    }

    public static class ThreadPop extends Thread {
        private final Random random = new Random();
        public ThreadPop(){
            super("pop thread");
        }

        public void run(){
            try {
                while (true) {
                    System.out.printf("Поток %s\n",Thread.currentThread().getName());
                    try {
                        System.out.printf("изъятие числа %d\n", queue.pop());
                    } catch (EmptyQueueExeption emptyQueueExeption) {
                        System.out.println(emptyQueueExeption.getMessage());
                    }

                    Thread.sleep(ThreadLocalRandom.current().nextLong(3000,5000));
                }
            } catch (InterruptedException e) {
                System.out.printf("Поток %s прерван\n", Thread.currentThread().getName());
            }
        }
    }

    public static class ThreadMax extends Thread {
        private final Random random = new Random();
        public ThreadMax(){
            super("max thread");
        }

        public void run(){
            try {
                while (true) {
                    System.out.printf("Поток %s\n",Thread.currentThread().getName());
                    try {
                        System.out.printf("максимальное число в очереди %d\n", queue.max());
                    } catch (EmptyQueueExeption emptyQueueExeption) {
                        System.out.println(emptyQueueExeption.getMessage());
                    }

                    Thread.sleep(ThreadLocalRandom.current().nextLong(3000,5000));
                }
            } catch (InterruptedException e){
                System.out.printf("Поток %s прерван\n",Thread.currentThread().getName());
            }

        }
    }

    public static class ThreadMin extends Thread {
        private final Random random = new Random();
        public ThreadMin(){
            super("min thread");
        }

        public void run(){
            try {
                while (true) {
                    System.out.printf("Поток %s\n",Thread.currentThread().getName());
                    try {
                        System.out.printf("минимальное число в очереди %d\n", queue.min());
                    } catch (EmptyQueueExeption emptyQueueExeption) {
                        System.out.println(emptyQueueExeption.getMessage());;
                    }

                    Thread.sleep(ThreadLocalRandom.current().nextLong(3000,5000));
                }
            } catch (InterruptedException e){
                System.out.printf("Поток %s прерван\n",Thread.currentThread().getName());
            }

        }
    }

    public static class ThreadState extends Thread {
        private final Random random = new Random();

        public ThreadState() {
            super("state thread");
        }

        public void run() {
            try {
                while (true) {
                    System.out.printf("Поток %s\n",Thread.currentThread().getName());
                    System.out.printf("очередь: %s\n", queue.toString());
                    Thread.sleep(ThreadLocalRandom.current().nextLong(3000,5000));
                }
            } catch (InterruptedException e) {
                System.out.printf("Поток %s прерван\n", Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) throws Exception{
        new ThreadInsert().start();
        new ThreadInsert().start();
        new ThreadInsert().start();
        new ThreadInsert().start();
        new ThreadInsert().start();
        new ThreadInsert().start();
        new ThreadInsert().start();

        new ThreadMax().start();
        new ThreadMin().start();
        new ThreadPop().start();
        new ThreadState().start();
    }
}
