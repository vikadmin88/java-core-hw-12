package m12.task_one;


public class SchedulerExecutorTest {

    public static void main(String[] args) {
        System.out.println("Starting " + Thread.currentThread().getName());

        SchedulerExecutor se = new SchedulerExecutor();
        se.startSchedulers();
    }

}
