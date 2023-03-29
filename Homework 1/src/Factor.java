import java.util.*;

public class Factor  {

    public static void main(String[] args) {

        ArrayList<Thread> threads = new ArrayList<Thread>();
        long timeStart = System.currentTimeMillis();
        int limit = 20000;
        int numChild = 100;
        int n = 1;

        int section = limit/numChild;


        for(int i = 0; i < numChild; i++){

            int lowerlimit = i * section;
            int higherlimit = section * n;

            n = n + 1 ;

            if (i > limit / 2) {
                break;
            }

            FactorThread thd = new FactorThread(i, lowerlimit ,higherlimit);
            thd.start();
            threads.add(thd);
        }

        for (Thread thds : threads) {
            try {
                thds.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("Time: " + (timeEnd - timeStart));
    }
}