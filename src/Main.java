import java.net.URISyntaxException;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.FutureTask;

public class Main
{
    static final String SALES="data/sales.csv";
    static double furniture=0;
    static double technology=0;
    static double supplies=0;
    static double average=0;
    public static void main(String[] args)
    {
        try
        {
            Path path=Paths.get(Thread.currentThread().getContextClassLoader().getResources(SALES).toURI());
        /*    FutureTask<Double> future = new FutureTask<>(()-> average(path,"Furniture"));
            Thread thread2=new Thread(future);
            Thread thread3=new Thread(() -> technology=average(path,"Techology"));
            Thread thread4=new Thread(() -> supplies=average(path,"Office Supplies"));
            Thread thread5=new Thread(() -> average=totalAverage(path));


         */
            FutureTask<Double> future = new FutureTask<>(()-> average(path,"Furniture"));
            Thread thread2=new Thread(future);
            FutureTask<Double> future2 = new FutureTask<>(()-> average(path,"Technology"));
            Thread thread3=new Thread(future2);
            FutureTask<Double> future3 = new FutureTask<>(()-> average(path,"Office Supplies"));
            Thread thread4=new Thread(future3);
            FutureTask<Double> future4 = new FutureTask<>(()-> (totalAverage(path)));
            Thread thread5=new Thread(future4);

            thread2.start();
            System.out.println(thread2.getState());
            thread2.interrupt();
            thread3.start();
            thread4.start();
            thread5.start();

            Scanner sc=new Scanner(System.in);
            System.out.println("Please enter a name to generate a number");
            String name=sc.nextLine();
                try
                {
                    // thread2.join();
                    // thread3.join();
                    // thread4.join();
                    // thread5.join();

                    furniture =future.get();
                    technology=future2.get();
                    supplies=future3.get();
                    average=future4.get();

                    System.out.println("\nThank you " + name + ". The average sales for Global Superstore are:\n");
                    System.out.println("Average Furniture Sales: " + furniture);
                    System.out.println("Average Technology Sales: " + technology);
                    System.out.println("Average Office Supplies Sales: " + supplies);
                    System.out.println("Total Average: " + average);
                };
                catch(IOException e)
                {
                    System.out.println(thread2.getState());
                }

            Thread.sleep(10);
            System.out.println(thread2.getState());

            /*
            average(path,"Furniture");
            average(path,"Technology");
            average(path,"Office Supplies");
            totalAverage(path);

             */

            Scanner sc=new Scanner(System.in);
            System.out.print("Please enter your name to access the lobal Superstore data ");
            String name=sc.nextLine();
            System.out.println("Access Denined.We apologize for the inconvience.Have a good Day");
            sc.close();
        }
        catch(URISyntaxException e)
        {
            System.out.println(e.getMessage());
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }



    }
    public static Double average(Path path,String category)
    {
        if(Thread.currentThread().isInterrupted())
        {
            return 0.0;
        }
        try
        {
            return Files.lines(path)
                    .skip(1)
                    .map((line)-> line.split(","))
                    .filter((values) -> values[0].equals(category))
                    .mapToDouble((value) -> Double.valueOf( value[1] )*Double.valueOf( value[2]))
                    .average().getAsDouble();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            return 0.0;
        }
    }
    public static Double totalAverage(Path path)
    {
        try
        {
            return Files.lines(path)
                    .skip(1)
                    .map((line) -> line.split(","))
                    .mapToDouble((values) -> Double.parseDouble(values[1]) * Double.parseDouble(values[2]))
                    .average().getAsDouble();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            return 0.0;
        }
    }
}
