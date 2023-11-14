package m12.task_two;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FizzBuzzMultiThreadsTest {

    public static AtomicInteger numThreadsDone = new AtomicInteger(0);

    public static void main(String[] args) {

        /*
          The results:
          Starting main
          Starting Thread-2
          Starting Thread-3
          Starting Thread-1
          Starting Thread-0
          Total numbers: 100000000
          Total milliseconds: 2796
          +++
          1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz, 16, 17, fizz, 19, buzz, fizz, 22, 23, fizz, buzz, 26, fizz, 28, 29, fizzbuzz, 31, 32, fizz, 34, buzz, fizz, 37, 38, fizz, buzz, 41, fizz, 43, 44, fizzbuzz, 46, 47, fizz, 49, buzz, fizz, 52, 53, fizz, buzz, 56, fizz, 58, 59, fizzbuzz, 61, 62, fizz, 64, buzz, fizz, 67, 68, fizz, buzz, 71, fizz, 73, 74, fizzbuzz, 76, 77, fizz, 79, buzz, fizz, 82, 83, fizz, buzz, 86, fizz, 88, 89, fizzbuzz, 91, 92, fizz, 94, buzz, fizz, 97, 98, fizz, buzz
          Total numbers: 100
          Total milliseconds: 25
        */
        long start = System.currentTimeMillis();
        System.out.println("Starting " + Thread.currentThread().getName());

        int lastNumber = 100;
        String[] resultArr = new String[lastNumber + 1];

        List<Runnable> threads = getThreads(resultArr, lastNumber);
        for (Runnable thread: threads) {
            new Thread(thread).start();
        }

        while (numThreadsDone.get() != threads.size()) {
            // waiting...
        }

        String resultString = Arrays.stream(resultArr)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));

        System.out.println("Expected:");
        System.out.println("1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz");

        System.out.println("Result:");
        System.out.println(resultString);

        System.out.println("Total numbers: " + lastNumber);
        System.out.println("Total milliseconds: " + ((System.currentTimeMillis() - start)));
    }


    private static List<Runnable> getThreads(String[] resultArr, int lastNumber) {
        List<Runnable> threads = new ArrayList<>();
        Runnable fizzbuzz = new Runnable() {
            public void run() {
                System.out.println("Starting " + Thread.currentThread().getName());
                for (int i = lastNumber; i > 0; i--) {
                    if (i % 15 == 0) {
                        resultArr[i] = "fizzbuzz";
                    }
                }
                numThreadsDone.incrementAndGet();
            }
        };
        Runnable fizz = new Runnable() {
            public void run() {
                System.out.println("Starting " + Thread.currentThread().getName());
                for (int i = lastNumber; i > 0; i--) {
                    if (i % 3 == 0 && i % 5 != 0) {
                        resultArr[i] = "fizz";
                    }
                }
                numThreadsDone.incrementAndGet();
            }
        };
        Runnable buzz = new Runnable() {
            public void run() {
                System.out.println("Starting " + Thread.currentThread().getName());
                for (int i = lastNumber; i > 0; i--) {
                    if (i % 5 == 0 && i % 3 != 0) {
                        resultArr[i] = "buzz";
                    }
                }
                numThreadsDone.incrementAndGet();
            }
        };
        Runnable number = new Runnable() {
            public void run() {
                System.out.println("Starting " + Thread.currentThread().getName());
                for (int i = lastNumber; i > 0; i--) {
                    if (i % 3 != 0 && i % 5 != 0) {
                        resultArr[i] = Integer.toString(i);
                    }
                }
                numThreadsDone.incrementAndGet();
            }
        };

        threads.add(fizzbuzz);
        threads.add(fizz);
        threads.add(buzz);
        threads.add(number);
     return threads;
    }
}
