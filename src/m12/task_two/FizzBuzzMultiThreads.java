package m12.task_two;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzzMultiThreads {

    public AtomicInteger numThreadsDone = new AtomicInteger(0);
    public AtomicInteger getNumThreadsDone() {
        return numThreadsDone;
    }

    protected List<Runnable> getThreads(String[] resultArr, int totalDigits, int threadsGroupsNum) {

        int countRead = totalDigits;
        int indexStart = totalDigits;
        if (threadsGroupsNum > 1) {
            indexStart = totalDigits % threadsGroupsNum == 0 ?
                    totalDigits / threadsGroupsNum :
                    totalDigits / threadsGroupsNum + 1;
        }

        List<Runnable> threads = new ArrayList<>();

        for (int i = 1; i <= threadsGroupsNum; i++) {
            System.out.println("indexStart, countRead = " + indexStart +", "+ countRead);
            threads.add(getFizzbuzz(resultArr, countRead));
            threads.add(getFizz(resultArr, countRead));
            threads.add(getBuzz(resultArr, countRead));
            threads.add(getNumber(resultArr, countRead));

            countRead = totalDigits - indexStart * i;
        }
        return threads;
    }

    private Runnable getNumber(String[] resultArr, int countToRead) {
        return new Runnable() {
            public void run() {
                System.out.println("Starting " + Thread.currentThread().getName());
                for (int i = countToRead; i > 0; i--) {
                    if (i % 3 != 0 && i % 5 != 0) {
                        resultArr[i] = Integer.toString(i);
                    }
                }
                numThreadsDone.incrementAndGet();
            }
        };
    }

    private Runnable getBuzz(String[] resultArr, int countToRead) {
        return new Runnable() {
            public void run() {
                System.out.println("Starting " + Thread.currentThread().getName());
                for (int i = countToRead; i > 0; i--) {
                    if (i % 5 == 0 && i % 3 != 0) {
                        resultArr[i] = "buzz";
                    }
                }
                numThreadsDone.incrementAndGet();
            }
        };
    }

    private Runnable getFizz(String[] resultArr, int countToRead) {
        return new Runnable() {
            public void run() {
                System.out.println("Starting " + Thread.currentThread().getName());
                for (int i = countToRead; i > 0; i--) {
                    if (i % 3 == 0 && i % 5 != 0) {
                        resultArr[i] = "fizz";
                    }
                }
                numThreadsDone.incrementAndGet();
            }
        };
    }

    private Runnable getFizzbuzz(String[] resultArr, int countToRead) {
        return new Runnable() {
            public void run() {
                System.out.println("Starting " + Thread.currentThread().getName());
                for (int i = countToRead; i > 0; i--) {
                    if (i % 15 == 0) {
                        resultArr[i] = "fizzbuzz";
                    }
                }
                numThreadsDone.incrementAndGet();
            }
        };
    }
}
