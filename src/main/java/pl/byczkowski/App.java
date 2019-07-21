package pl.byczkowski;

import java.util.*;
import java.util.concurrent.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //CALLABLE
        List<Integer> ints = Arrays.asList(17, 23, 8, 1, 5, 3, 11, 10, 0, 4);
        runCallables(ints);

        //RUNNABLE
        //runRunnables();
        while (true) {
            System.out.println("Witamy!, wybierz 1 aby zwalidować pesele, wybierz 2 aby wyświetlić rymowanke, wybierz 3 aby zakonczyc.");

            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();

            switch (input) {
                case 1: {
                    Runnable runner = new Task();
                    Thread thread = new Thread(runner);
                    thread.start();
                    break;
                }
                case 2: {
                    System.out.println("Lubie gdy zające kicają po łące!");
                    break;
                }
                case 3: {
                    System.exit(200);
                }
            }
        }
    }

    private static void runCallables(List<Integer> arr) {
        //tworzymy egzekutor
        ExecutorService executor = Executors.newFixedThreadPool(10);

        //tworzymy nasze instancje callable
        Callable<int[]> callable = new MyCallable(1, arr.subList(0, 5));
        Callable<int[]> callable2 = new MyCallable(2, arr.subList(5, 10));

        //dodajemy je do wykonania egzegukutorowi i oczekujemy Future
        Future<int[]> future = executor.submit(callable);
        Future<int[]> future2 = executor.submit(callable2);

        //dodajemy nasze future żeby w pętli potem sobie sprawdzić czy są
        List<Future<int[]>> list = new ArrayList<>();
        list.add(future);
        list.add(future2);

        //czekamy na wartosc z naszego future i wypisujemy na ekran
        for (Future<int[]> fut : list) {
            try {
                System.out.println(new Date() + "::" + Arrays.toString(fut.get()));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }

  /*  private static void runRunnables() {
        Runnable[] runners = new Runnable[10];
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            runners[i] = new Task(i);
        }

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runners[i]);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }*/
}
