import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test
{
    /* Maximum number of threads in thread pool */
    static final int MAX_THREADS = 32;

    public static void main(String[] args)
    {
        /* Create fixed size threadpool */
        ExecutorService pool = Executors.newFixedThreadPool(MAX_THREADS);

        /* Drop tasks in the pool for concurrent execution */
        for (int i = 1; i <= 5; i++) {
            pool.execute(new Task("task " + i));
        }

        pool.shutdown();
    }
}

/* Task: Runnable thread */
class Task implements Runnable {
    private String name;

    public Task(String s) {
        name = s;
    }

    /* Unit of work: Print thread name and sleep */
    public void run() {
        try {
            for (int i = 0; i <= 10; i++) {
                /* Create date and record iteration */
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
                System.out.println(name + ": Iteration: " + i + " at " + dateFormat.format(date));
                Thread.sleep(250);
            }

            System.out.println(name+" DONE");
        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

