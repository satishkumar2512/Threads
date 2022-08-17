import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Workbook3
{
    static int counter=0;
    public static void main(String[] args)
    {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExcecutorService executor = Excecutors.newFixedThreadPool(numThreads);        CountDownLatch latch = new CountDownLatch(4);
        ReentrantLock lock =new ReentrantLock();

        Thread thread1=new Thread(() -> task(lock,latch));
        Thread thread2=new Thread(() -> task(lock,latch));
        Thread thread3=new Thread(() -> task(lock,latch));
        Thread thread4=new Thread(() -> task(lock,latch));

        thread1.start();
        thread2.start();

        try
        {
            // thread1.join();
            // thread2.join();
            // thread3.join();
            // thread4.join();
            latch.await();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            excecutor.shutdown();
        }

        System.out.println(counter);
    }
    public static void task(ReentrantLock lock,CountDownLatch)
    {
        for (int i=0;i<10000;i++)
        {
            lock.lock();
            counter++;
            counter=counter+1;
            lock.unlock();
        }
    }
}
