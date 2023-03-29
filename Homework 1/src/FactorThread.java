public class FactorThread extends Thread {

    private int threadNumber;
    private int hLimit;
    private int lLimit;
    public FactorThread( int threadNumber, int lowerLimit, int higherLimit){
        this.threadNumber = threadNumber;
        this.hLimit = higherLimit;
        this.lLimit = lowerLimit;
    }

    @Override
    public void run(){

        long start;
        long end;
        start = System.currentTimeMillis();

        for (int num = lLimit; num <= hLimit; num++) {
            if (isPrimeNumber(num)) {
                //System.out.println("f-> " + num + " from thread-> " + threadNumber);
            }

        }

        end = System.currentTimeMillis();
        //System.out.println("Finished Time for thread -> " + threadNumber + " : " + (end-start));

    }
    public static boolean isPrimeNumber ( int num){
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false; // if number is divisible then it's not a prime number
            }

            if (i > num / 2) {
                break;
            }

        }
        return true; // if no divisible found then the number is prime number
    }
}