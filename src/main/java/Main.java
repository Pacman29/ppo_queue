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
                    System.out.println("\n----------------------------------");
                    Integer value = random.nextInt() % 100;
                    System.out.printf("вставка в очередь числа %d\n", queue.push(value));

                    long sleep = ThreadLocalRandom.current().nextLong(0,3000);
                    System.out.printf("Поток %s уснул на %d\n",Thread.currentThread().getName(),sleep);
                    System.out.println("----------------------------------\n");
                    this.sleep(sleep);
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
                    System.out.println("\n----------------------------------");
                    try {
                        System.out.printf("изъятие числа %d\n", queue.pop());
                    } catch (EmptyQueueExeption emptyQueueExeption) {
                        System.out.printf("Поток %s\n",Thread.currentThread().getName());
                        System.out.println(emptyQueueExeption.getMessage());
                    }
                    long sleep = ThreadLocalRandom.current().nextLong(1000,3000);
                    System.out.printf("Поток %s уснул на %d\n",Thread.currentThread().getName(),sleep);
                    System.out.println("----------------------------------\n");
                    this.sleep(sleep);

                }
            } catch (InterruptedException e) {
                System.out.printf("Поток %s прерван\n", Thread.currentThread().getName());
            }
        }
    }

    public static class ThreadStatus extends Thread {
        private final Random random = new Random();
        public ThreadStatus(){
            super("pop thread");
        }

        public void run(){
            try {
                while (true) {
                    System.out.println("\n----------------------------------");
                    try {
                        System.out.printf("максимальное число в очереди %d\n", queue.max());
                        System.out.printf("минимальное число в очереди %d\n", queue.min());
                        System.out.printf("очередь: %s\n", queue.toString());

                    } catch (EmptyQueueExeption emptyQueueExeption) {
                        System.out.printf("Поток %s\n",Thread.currentThread().getName());
                        System.out.println(emptyQueueExeption.getMessage());
                    }

                    long sleep = ThreadLocalRandom.current().nextLong(0,2000);
                    System.out.printf("Поток %s уснул на %d\n",Thread.currentThread().getName(),sleep);
                    System.out.println("----------------------------------\n");
                    this.sleep(sleep);
                }
            } catch (InterruptedException e) {
                System.out.printf("Поток %s прерван\n", Thread.currentThread().getName());
            }
        }
    }



    public static void main(String[] args) throws Exception{
        new ThreadInsert().start();
        new ThreadPop().start();
        new ThreadStatus().start();
    }
}
