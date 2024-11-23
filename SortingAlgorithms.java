import java.util.Arrays;

public class SortingAlgorithms {


    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int avoidAlreadyProcessed = arr.length - i -1;
            for (int j = 0; j < avoidAlreadyProcessed; j++) {
                if (arr[j] > arr[j + 1]) {
                    //    System.out.println(arr[j] + ", " + arr[j + 1]);
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    public void mergeSort(int[] arr, int start, int end) {
        if (start >= end){
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);

        merge(arr, start, mid, end);
    }

    public void merge(int[] arr, int start, int mid, int end) {
        //This section just creates the sub arrays that are to be sorted.
        int leftNum = mid - start + 1;
        int rightNum = end - mid;

        int[] leftArr = new int[leftNum];
        int[] rightArr = new int[rightNum];

        for (int i = 0; i < leftNum; i++) {
            leftArr[i] = arr[start + i];
        }

        for (int i = 0; i < rightNum; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        //sorting these lists
        int indexOne = 0;
        int indexTwo = 0;
        int updatedStart = start;
        while (indexOne < leftNum && indexTwo < rightNum) {
            if (leftArr[indexOne] < rightArr[indexTwo]) {
                arr[updatedStart] = leftArr[indexOne];
                indexOne++;
            }
            else {
                arr[updatedStart] = rightArr[indexTwo];
                indexTwo++;
            }
            updatedStart++;

        }
        while (indexOne < leftNum) {
            arr[updatedStart] = leftArr[indexOne];
            indexOne++;
            updatedStart++;
        }

        while (indexTwo < rightNum) {
            arr[updatedStart] = rightArr[indexTwo];
            indexTwo++;
            updatedStart++;
        }
    }


    public void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int testIndex = i - 1;
            while (testIndex >= 0 && array[testIndex] > key) {
                array[testIndex + 1] = array[testIndex];
                testIndex--;
            }
            array[testIndex + 1] = key;
        }
    }

    public void shellSort(int[] array) {
        int subcount = array.length / 2;
        while (subcount > 0) {
            for (int i = 0; i < subcount; i++) {
                gapInsert(array, i, subcount);
            }
            subcount = subcount / 2;
        }
    }

    private void gapInsert(int[] arr, int start, int gap){
        for (int i = start + gap; i < arr.length; i+= gap) {
            int currentVal = arr[i];
            int index = i;
            while (index >= gap && arr[index - gap] > currentVal) {
                arr[index] = arr[index - gap];
                index -= gap;
            }
            arr[index] = currentVal;
        }
    }

    public void quickSort(int[] arr, int start, int end) {
        if (start < end){
            int pivIndex = pivot(arr, start, end);
            quickSort(arr, start, pivIndex-1);
            quickSort(arr, pivIndex+1, end);
        }
    }

    private int pivot(int[] arr, int start, int end) {
        int pivot = arr[end];
        int i = start - 1;

        for (int j = start; j < end; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;
        return i + 1;
    }


    public void heapSort(int[] arr) {
        int length = arr.length;

        for (int i = length / 2 - 1; i >= 0; i--) {
            heapify(arr, length, i);
        }

        for (int i = length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    private void heapify(int[] arr, int size, int top) {
        int max = top;
        int left = 2 * top + 1;
        int right = 2 * top + 2;

        if (left < size && arr[left] > arr[max]) {
            max = left;
        }

        if (right < size && arr[right] > arr[max]) {
            max = right;
        }

        if (max != top) {
            int temp = arr[top];
            arr[top] = arr[max];
            arr[max] = temp;

            heapify(arr, size, max);
        }
    }


    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }


    public boolean validate(int[] array, String algorithm, SortingAlgorithms sortingAlgorithms) {
        int[] copy = array.clone();
        int[] sortedCopy = array.clone();
        switch (algorithm) {
            case "Bubble Sort":
                sortingAlgorithms.bubbleSort(copy);
                break;
            case "Selection Sort":
                sortingAlgorithms.selectionSort(copy);
                break;
            case "Insertion Sort":
                sortingAlgorithms.insertionSort(copy);
                break;
            case "Merge Sort":
                sortingAlgorithms.mergeSort(copy, 0, copy.length - 1);
                break;
            case "Shell Sort":
                sortingAlgorithms.shellSort(copy);
                break;
            case "Quick Sort":
                sortingAlgorithms.quickSort(copy, 0, copy.length - 1);
                break;
            case "Heap Sort":
                sortingAlgorithms.heapSort(copy);
                break;
            default:
                throw new IllegalArgumentException("Unknown sorting algorithm: " + algorithm);
        }

        Arrays.sort(sortedCopy);
        return Arrays.equals(copy, sortedCopy);
    }



    public long measureTime(Runnable sort) {
        long start = System.currentTimeMillis();
        sort.run();
        return System.currentTimeMillis() - start;
    }
}