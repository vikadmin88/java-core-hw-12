package m12.task_one;

public class SchedulerRawTest {

    public static void main(String[] args) {

        // running duration, every second
        final Runnable timeDurationTread = new Runnable() {
            public void run() {
                System.out.println("Starting " + Thread.currentThread().getName());
                long start = System.currentTimeMillis();
                while (true) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //throw new RuntimeException(e);
                    }

                    System.out.printf("%s running: %d sec.\n",Thread.currentThread().getName(), ((System.currentTimeMillis() - start)/1000));
                }
            }
        };

        // every 5 second
        final Runnable timeTikTread = new Runnable() {
            public void run() {
                System.out.println("Starting " + Thread.currentThread().getName());
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        //throw new RuntimeException(e);
                    }
                    System.out.printf("%s: -= Минуло 5 секунд =-\n", Thread.currentThread().getName());
                }
            }
        };

        System.out.println("Starting " + Thread.currentThread().getName());
        new Thread(timeDurationTread).start();
        new Thread(timeTikTread).start();
    }
}
