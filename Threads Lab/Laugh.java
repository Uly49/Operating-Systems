// Child.java
//  Working with UNIX threads
import java.io.*;
import java.lang.Thread;

public class Laugh extends Thread {
   private int common_ = 0;
   private int sleep_time_ = 1;
   private int thread_id_ = 0;
   Thread thr = Thread.currentThread();
   private String text_;

   public void set_sleep_time(int time)
   {
      if (time>9 || time <0) time = 0;
      sleep_time_ = time;
      // System.out.println("Sleep Time = " + time);
   }

   public void set_text(String text)
   {
       text_ = text;
       thr.setName(text);
   }
   
   public void run()
   {
      // create a thread id for the thread
      int thread_id = thread_id_++;

      System.out.println( "Started: " + thr.getName() + thr.getId() + " with number:" + thread_id);

      // Print text 5000 times, 10 on each line
      for (int i=0; i<5000; i++) {
            try {
               if (sleep_time_ > 0) Thread.sleep (sleep_time_*1000);
            } catch (InterruptedException e) {}
            System.out.print(text_ + " ");
            if (i%10==0) System.out.println(" ");
            common_++;
      }

      // Print when thread is done, then self-terminate 
      System.out.println( "Completed: " + thr.getName() + thr.getId());
   }
};

