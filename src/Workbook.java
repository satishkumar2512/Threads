import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Files;
public class Workbook
{
    static String[] files = new String[] {"data/sales1.csv","data/sales2.csv"};
    static int sampleSize = 0;
    static int quantitySold = 0;

    public static void main(String [] args) throws Exceception
    {
        ExcecutorService excecutor = Excecutors.newFixedThreadPool(3);
        for (String string : files)
        {
            excecutor.submit(() -> increment(string));
        }
        for (int i=0;i<files.length;i++)
        {
            excecutor.submit(() -> increment(files[i]));
        }
        Scanner sc=new Scanner(System.in);
        System.out.println("Please Enter Your Name to Access The Global data");
        String name = sc.nextLine();
        System.out.println("\nThank you "+ name + ".\n");
        sc.close();
    }
    public static void increment(String file)
    {
        try
        {
            Path path = Paths.get(Thread.currentThread().getContextClassLoader().getResources(Main.SALES).toURI());
            Files.lines(path)
                    .skip(1)
                    .mapToInt((line) -> Integer.ParseInt(line.split(",")[2]))
                    .forEach((quantity) -> {
                        sampleSize++;
                        quantity += quantity;
                    });
        }
        catch(URISyntaxException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static class Excecption extends Exception {
    }

    private static class Exceception extends Exception {
    }

    private static class ExcecutorService {
    }
}
