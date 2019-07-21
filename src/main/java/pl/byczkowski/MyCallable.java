package pl.byczkowski;

import java.util.List;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<int[]> {
    private int id;
    private List<Integer> myPart;

    public MyCallable(int id, List<Integer> myPart) {
        this.id = id;
        this.myPart = myPart;
    }

    @Override
    public int[] call() {
        System.out.println("Thread no. " + id + " is counting.");
        return bubbleSort(myPart);
    }

    private int[] bubbleSort(List<Integer> array) {
        int[] arr = array.stream().mapToInt(i -> i).toArray();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }

}
