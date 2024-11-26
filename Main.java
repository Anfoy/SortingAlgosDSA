import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
        int[] sizes = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000,
                110000, 120000, 130000, 140000, 150000, 160000,170000, 180000, 190000, 200000};


        String[][] results = new String[sizes.length + 1][10];
        results[0] = new String[]{"Array Size", "Bubble Sort Time (ms)",
                "Selection Sort Time (ms)",
                "Insertion Sort Time (ms)",
                "Merge Sort Time (ms)",
                "Shell Sort Time (ms)",
                "Quick Sort Time (ms)",
                "Heap Sort Time (ms)",
                "Linear Search Time (ms)",
                "Binary Search Time (ms)"
        };

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            int[] array = generateRandomArray(size);

            System.out.println("Testing sorting algorithms for array size: " + size);

            results[i + 1][0] = String.valueOf(size);
            results[i + 1][1] = String.valueOf(testSort(array.clone(), sortingAlgorithms, "Bubble Sort"));
            results[i + 1][2] = String.valueOf(testSort(array.clone(), sortingAlgorithms, "Selection Sort"));
            results[i + 1][3] = String.valueOf(testSort(array.clone(), sortingAlgorithms, "Insertion Sort"));
            results[i + 1][4] = String.valueOf(testSort(array.clone(), sortingAlgorithms, "Merge Sort"));
            results[i + 1][5] = String.valueOf(testSort(array.clone(), sortingAlgorithms, "Shell Sort"));
            results[i + 1][6] = String.valueOf(testSort(array.clone(), sortingAlgorithms, "Quick Sort"));
            results[i + 1][7] = String.valueOf(testSort(array.clone(), sortingAlgorithms, "Heap Sort"));
            results[i + 1][8] = String.valueOf(testSort(array.clone(), sortingAlgorithms, "Linear Search"));
            results[i+1][9] = String.valueOf(testSort(array.clone(), sortingAlgorithms, "Binary Search"));
        }

        exportResultsToCSV(results);

        System.out.println("Testing completed. Results exported to sorting_results.csv.");
    }

    /**
     * Generates a random array of integers.
     *
     * @param size the size of the array
     * @return the generated random array
     */
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        return random.ints(size, 0, 100000).toArray();
    }

    /**
     * Tests a sorting algorithm and returns the time taken in nanoseconds.
     *
     * @param array             the array to sort
     * @param sortingAlgorithms the SortingAlgorithms instance
     * @param algorithmName     the name of the sorting algorithm to test
     * @return the time taken to execute the sorting algorithm in nanoseconds
     */
    private static long testSort(int[] array, SortingAlgorithms sortingAlgorithms, String algorithmName) {
        long startTime = System.currentTimeMillis();

        sortAlgo(algorithmName, array, sortingAlgorithms);

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        System.out.printf("%s (Size: %d): Time = %d ms%n", algorithmName, array.length, timeTaken);
        return timeTaken;
    }

    /**
     * Exports the results to a CSV file.
     *
     * @param results the results array to export
     */
    private static void exportResultsToCSV(String[][] results) {
        try (FileWriter writer = new FileWriter("sorting_results.csv")) {
            for (String[] row : results) {
                writer.append(String.join(",", row)).append("\n");
            }

            System.out.println("Results successfully exported to sorting_results.csv");
        } catch (IOException e) {
            System.err.println("Error exporting results to CSV: " + e.getMessage());
        }
    }

    private static void sortAlgo(String algorithmName, int[] array, SortingAlgorithms sortingAlgorithms) {
        switch (algorithmName) {
            case "Bubble Sort":
                sortingAlgorithms.bubbleSort(array);
                break;
            case "Selection Sort":
                sortingAlgorithms.selectionSort(array);
                break;
            case "Insertion Sort":
                sortingAlgorithms.insertionSort(array);
                break;
            case "Merge Sort":
                sortingAlgorithms.mergeSort(array, 0, array.length - 1);
                break;
            case "Shell Sort":
                sortingAlgorithms.shellSort(array);
                break;
            case "Quick Sort":
                sortingAlgorithms.quickSort(array, 0, array.length - 1);
                break;
            case "Heap Sort":
                sortingAlgorithms.heapSort(array);
                break;
            case "Binary Search":
                sortingAlgorithms.binarySearch(array, 50000);
                break;
            case "Linear Search":
                sortingAlgorithms.linearSearch(array, 50000);
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithmName);
        }
    }
}