import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    // private static void testConcurrent(String strPeriod, String strTimes)
    // throws NumberFormatException, InterruptedException {
    // int executeTimes = Integer.valueOf(strTimes);
    // ExecutorService threadExecutor = Executors
    // .newFixedThreadPool(executeTimes);
    // while (true) {
    // for (int i = 0; i < Integer.valueOf(strTimes).intValue(); i++) {
    // threadExecutor.execute(new HttpsRequestRunnable());
    // }
    // threadExecutor.awaitTermination(Integer.valueOf(strPeriod)
    // .intValue(), TimeUnit.SECONDS);
    // }
    // }

    private static void testEstimation(String downloadTimes)
            throws NumberFormatException, InterruptedException {
        int executeTimes = Integer.valueOf(downloadTimes);
        ExecutorService threadExecutor = Executors.newFixedThreadPool(10);
        int count = 0;
        while (count < executeTimes) {
            threadExecutor.execute(new HttpsRequestRunnable());
            Thread.sleep(10);
            ++count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testEstimation("1");
        System.exit(0);
        // Scanner input = new Scanner(System.in);
        // boolean flag = true;
        // while (flag) {
        // System.out.println("***************************");
        // System.out.println("Please choose test content:");
        // System.out.println("1. test estimation");
        // // System.out.println("2. test concurrent");
        // System.out.println("0. exit");
        // String choose = input.nextLine();
        // int chooseNum = 0;
        // try {
        // chooseNum = Integer.valueOf(choose);
        // } catch (Exception e) {
        // continue;
        // }
        // switch (chooseNum) {
        // case 1:
        // System.out.println("***************************");
        // System.out.println("Please input executing times:");
        // String downloadTimes = input.nextLine();
        // testEstimation(downloadTimes);
        // flag = false;
        // break;
        // // case 2:
        // // System.out.println("***************************");
        // // System.out.println("Please input concurrent period:");
        // // String strPeriod = input.nextLine();
        // // System.out.println("Please input concurrent amounts:");
        // // String strTimes = input.nextLine();
        // // testConcurrent(strPeriod, strTimes);
        // // flag = false;
        // // break;
        // case 0:
        // flag = false;
        // break;
        // default:
        // break;
        // }
        // }
    }
}
