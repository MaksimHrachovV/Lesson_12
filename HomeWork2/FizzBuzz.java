package src.Lesson12.HomeWork2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class FizzBuzz {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        FizzBuzz fizzBuzzService = new FizzBuzz();


        service.submit(() -> fizzBuzzService.fizz());
        service.submit(() -> fizzBuzzService.buzz());
        service.submit(() -> fizzBuzzService.fizzbuzz());
        service.submit(() -> fizzBuzzService.number());
        service.submit(() -> fizzBuzzService.print());
        try {
            sleep(2000);
            service.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private final int n = 20;
    public static volatile AtomicInteger number = new AtomicInteger(1);
    public BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public synchronized void add(String element){
        queue.add(element);
    }
    public synchronized void fizz() {
        while (number.get() < n) {
            if (number.get() % 3 == 0 && number.get() % 5 != 0) {

                add("fizz"+"----"+Thread.currentThread().getName());
                number.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public synchronized void buzz() {
        while (number.get() < n) {
            if (number.get() % 3 != 0 && number.get() % 5 == 0) {

                add("buzz"+"----"+Thread.currentThread().getName());
                number.incrementAndGet();
                notifyAll();

            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
   public synchronized void fizzbuzz () {
        while (number.get() < n) {
            if (number.get() % 3 == 0 && number.get() % 5 == 0) {

                add("fizzbuzz"+"----"+Thread.currentThread().getName());
                number.incrementAndGet();
                notifyAll();

            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    public synchronized void number() {

        while (number.get() < n) {
            if (number.get() % 3 != 0 && number.get() % 5 != 0) {

                add(String.valueOf(number)+" number----"+Thread.currentThread().getName());
                number.incrementAndGet();
                notifyAll();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void print() {
        while (true) {
            try {
                sleep(1000);
             } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
                }
            if(n==20){
                return;
            }
        }
    }
}
