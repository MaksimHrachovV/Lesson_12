package src.Lesson12.HomeWork1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeTest {
    static int count=1;
    public static void main(String[] args) throws InterruptedException {


        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(
                () -> System.out.println("Time +5 sec: " +count+" --------"+Thread.currentThread().getName()),

                5,
                5,
                TimeUnit.SECONDS
        );

        executorService.scheduleAtFixedRate(
                new MyTask(),
                1,
                1,
                TimeUnit.SECONDS
        );
        Thread.sleep(11000);
        executorService.shutdown();
        System.out.println("End");
    }
    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println("TimerTask  : " + count+" --------"+Thread.currentThread().getName());
            count++;
        }
    }

}



