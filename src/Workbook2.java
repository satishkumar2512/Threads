import java.util.concurrent.Future;
public class Workbook2
{
    static double[] targets=new double[] { 0.3,0.5,0.7};
    static final double TARGET = 0.5;
    static final double PRECISION = 0.000001;
    static double result=0;
    public static void main(String[] args)
    {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExcecutorService executor = Excecutors.newFixedThreadPool(numThreads);
        Future<Double> future=executor.submit(() -> generateNumber(0));
        Future<Double> future2=executor.submit(() -> generateNumber(1));
        Future<Double> future3=executor.submit(() -> generateNumber(2));
        Future<Double> future4=executor.submit(() -> generateNumber(3));
        Future<Double> future5=executor.submit(() -> generateNumber(4));
     /*
         Callable<Double> callable=() -> generateNumber();
        FutureTask<Double> future=new FutureTask<>(() -> generateNumber(1));
        Thread thread=new Thread(future);
        FutureTask<Double> future2=new FutureTask<>(() -> generateNumber(2));
        Thread thread2=new Thread(future2);
        FutureTask<Double> future3=new FutureTask<>(() -> generateNumber(3));
        Thread thread3=new Thread(future3);


      */

        thread.start();
        thread2.start();
        thread3.start();
        double precision=difference(result);
        // Scanner sc=new Scanner(System.in);
        // System.out.println("Please enter a name to generate a number");
        // String name=sc.nextLine();
        // sc.close();
        try
        {
            //thread.join();
            // result=future.get();
            future.get();
            future2.get();
            future3.get();
            executor.shutdown();
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("Finished running background operations");
        // System.out.println("The Computer returned a value of :"+ result);
           // System.out.println("The value was Generated to a precision of :"+precision);
    }
    public static double generateNumber(int index)
    {
        double number=Math.random();
        double difference= difference(number,index);
                while(difference > PRECISION)
                {
                    number= Math.random();
                    difference=difference(number,index);
                }
                return number;
    }
    public static double difference(double number,int index)
    {
        return Math.abs(targets[index]- number);
    }
}
