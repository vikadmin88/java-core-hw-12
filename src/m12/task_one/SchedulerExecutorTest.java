package m12.task_one;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SchedulerExecutorTest {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {
        System.out.println("Starting " + Thread.currentThread().getName());
        new SchedulerExecutorTest().startSchedulers();
    }

    public void startSchedulers() {
        // every 1 second
        final Runnable timeCounterOneSec = new Runnable() {
            long i = 0;
            public void run() { System.out.printf("%s running: %d sec\n", Thread.currentThread().getName(), ++i); }
        };

        // every 5 seconds
        final Runnable timeCounterFiveSec = new Runnable() {
            public void run() {
                long start = System.currentTimeMillis();
                System.out.printf("%s: -= Минуло 5 секунд =-\n", Thread.currentThread().getName());
            }
        };

        final ScheduledFuture<?> taskEvery1sec = scheduler.scheduleAtFixedRate(timeCounterOneSec, 1, 1, SECONDS);
        System.out.println("Starting taskEvery1sec");
        final ScheduledFuture<?> taskEvery5sec = scheduler.scheduleAtFixedRate(timeCounterFiveSec, 5, 5, SECONDS);
        System.out.println("Starting taskEvery5sec");
    }
}
