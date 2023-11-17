package m12.task_two;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FizzBuzzMultiThreadsTest {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println("Starting " + Thread.currentThread().getName());

        int totalDigits = 1_000_000;
        // threads = 4 * threadsGroupNum
        int threadsGroupNum = 1;
        String[] resultArr = new String[totalDigits + 1];

        FizzBuzzMultiThreads fizzBuzzMultiThread = new FizzBuzzMultiThreads();
        List<Runnable> threadsList = fizzBuzzMultiThread.getThreads(resultArr, totalDigits, threadsGroupNum);
            for (Runnable thread : threadsList) {
                new Thread(thread).start();
            }

        while (fizzBuzzMultiThread.getNumThreadsDone().get() != threadsList.size()) {
            // waiting threads...
        }

//        String resultString = Arrays.stream(resultArr)
//                .filter(Objects::nonNull)
//                .collect(Collectors.joining(", "));

        System.out.println("Expected:");
        System.out.println("1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz");

        System.out.println("Result:");
//        System.out.println(resultString);

        System.out.println("Total numbers: " + totalDigits);
        System.out.println("Total milliseconds: " + ((System.currentTimeMillis() - start)));
    }


}

        /*
         1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz, 16, 17, fizz, 19, buzz, fizz, 22, 23, fizz, buzz, 26, fizz, 28, 29, fizzbuzz, 31, 32, fizz, 34, buzz, fizz, 37, 38, fizz, buzz, 41, fizz, 43, 44, fizzbuzz, 46, 47, fizz, 49, buzz, fizz, 52, 53, fizz, buzz, 56, fizz, 58, 59, fizzbuzz, 61, 62, fizz, 64, buzz, fizz, 67, 68, fizz, buzz, 71, fizz, 73, 74, fizzbuzz, 76, 77, fizz, 79, buzz, fizz, 82, 83, fizz, buzz, 86, fizz, 88, 89, fizzbuzz, 91, 92, fizz, 94, buzz, fizz, 97, 98, fizz, buzz

          The results:  ! Without output to console.

          Total numbers: 100_000_000
          4 threads:
          Total milliseconds: 2839
          8 threads:
          Total milliseconds: 3345
          16 threads:
          Total milliseconds: 4662

          +++

          Total numbers: 1_000_000
          4 threads:
          Total milliseconds: 89
          8 threads:
          Total milliseconds: 102
          16 threads:
          Total milliseconds: 124
        */
