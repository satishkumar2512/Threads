public class MultiThread
{
    public static void main(String[] args)
    {

        Thread thread2=new Thread(() -> longTask());
        thread2.start();
        System.out.println("Oh no A Big Error Occured in the main thread lets interuppted ");
        thread2.interrupt();
        System.out.println("Doing other work on main thread");
        // System.out.println(thread2.getState());

        Runnable runnable=new Runnable()
        {
                @Override
                public void run()
                {
                    longTask();
                }
        };
        Thread thread22=new Thread(() -> longTask());
        thread22.start();
        System.out.println("Doing other work on main thread");
    }
    public static void longTask()
    {
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            System.out.println("Done Sleeping");
        }
        long t=System.currentTimeMillis();
        long end=t+3000;

        while(true)
        {
            if(System.currentTimeMillis() == end)
            {
                System.out.println("Finished long Task");break;
            }
            else if(Thread.currentThread().isInterrupted())
            {
                System.out.println("This task has been interupted prematurely");
                break;
            }
        }
    }
}
