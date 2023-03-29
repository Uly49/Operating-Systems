// ParentLaugh.java    			Working with Java threads
// Compile all .java files into bytecode then execute:
// javac *.java
// java ParentLaugh
import java.io.*;
import java.lang.Thread;
import java.util.Scanner;

public class ParentLaugh implements Runnable {
   private int common_=0;
   private int sleep_time_=0;
   private int thread_id_=0;
   private String text[];
   private String text_;

   public static void main(String args[]) {
      ParentLaugh mom = new ParentLaugh();
      mom.create_threads();
   }

   // stores 'ha', 'he', or 'ho' into private attribute text_
   public void set_text(String argtext) {
      text_ = argtext;
      System.out.println("C" + argtext);
   }

   public void create_threads() {
      text = new String[3];
      text[0] = "Ha!";
      text[1] = "Ho!";
      text[2] = "He!";
      String type;

      // get time to sleep
      System.out.print("sleep_time: ");
         
      // Get time to sleep (if any)
      Scanner cin = new Scanner(System.in);
      sleep_time_ = cin.nextInt();

      // Determine whether internal or external threads are to be used
      System.out.print("Shared or External (s/e): ");
      type = cin.next();

      // enter loop to create children
      for (int i=0; i<3; i++) {
               if (type.charAt(0) == 'e') {
		   // Create an external child Laugh thread
                   Laugh child = new Laugh();
                   child.set_sleep_time(sleep_time_);
                   child.set_text(text[i]);
                   child.start();
               } else {
		   // Create an internal shared ParentThread child thread
                   Thread child = new Thread(this,text[i]);
                   set_text(text[i]);
                   child.start();
               }
      }
      System.out.println("Parent exiting");
   }

   // This is what each child thread runs
   public void run()
   {
      // Create a thread id
      int thread_id = thread_id_++;
      Thread thr = Thread.currentThread();

      System.out.println( "Hi From: " + thr.getId() + "=" + thr.getName());

      // Run 5000 times printing text, 10 to each row
      for (int i=0; i<5000; i++) {
            if (sleep_time_ > 0) {
               try {
                   Thread.sleep (sleep_time_*1000);
               } catch (InterruptedException e) {}
            }
            System.out.print( text_ + " " );
            if (i%10==0) System.out.println(" ");
      }

      // Print when thread is done - then terminate
      System.out.println( "Completed: " + thr.getId() + "=" + thr.getName());
      // thread automatically terminates when returning from run() function
   }
};

