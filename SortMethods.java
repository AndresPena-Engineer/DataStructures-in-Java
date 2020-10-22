/*
Description: 
            Priority queue is an abstract data type which is like a regular queue or stack
            data structure, but where additionally each element has a "priority" 
            associated with it. ... While priority queues are often implemented 
            with heaps, they are conceptually distinct from heaps.
            What types of sorts it supports are all the ones in this code and 
            a lot more like bubble sorting and others.
*/ 

package sortmethods;

import java.io.*;
import java.util.*;
 
//Method for the quicksort
public class SortMethods {
    
    private int array[];
    private int length;
 
    public void sort(int[] inputArr) {
         
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
    }
 
    private void quickSort(int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which 
             * is greater then the pivot value, and also we will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j){
            quickSort(lowerIndex, j);
        }   
        if (i < higherIndex){
           quickSort(i, higherIndex); 
        }
    }
    
    //This part calculates the timer for the time on each sorting
    private static long getTime(){
        long time;
        return time = System.nanoTime(); 
    }
    
    private static long timer(long startTime, long endTime){
        long duration;
        return duration= endTime - startTime; 
    }
 
    private void exchangeNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
     
    //Main method that does the sorting for all 3 of the sortings given on the lab
    public static void main(String a[]){
         
        //QUICK SORT
        SortMethods sorter = new SortMethods();
         System.out.println("QUICK SORT: ");
        int[] input = {129, 24, 15, 135, 87, 275, 99, 120, 32, 44, 152, 127, 23, 105, 89, 134, 69, 42, 140, 217, 85, 46};
        long startTime = getTime(); 
        sorter.sort(input);  
        long endTime = getTime();
        System.out.println("Time of Quick sort: " + timer(startTime, endTime)); 
        for(int i:input){
            System.out.print(i);
            System.out.print(" ");
        }
        //INSERT SORT
        System.out.println(" ");
        System.out.println("INSERT SORT: ");
         int[] arr1 = {129, 24, 15, 135, 87, 275, 99, 120, 32, 44, 152, 127, 23, 105, 89, 134, 69, 42, 140, 217, 85, 46};
         int[] arr2 = doInsertionSort(arr1);
         for(int i:arr2){
            System.out.print(i);
            System.out.print(" ");
        }
         //RADIX SORT
        System.out.println(" ");
        System.out.println("RADIX SORT: ");
         int arr[] = {129, 24, 15, 135, 87, 275, 99, 120, 32, 44, 152, 127, 23, 105, 89, 134, 69, 42, 140, 217, 85, 46};
        int n = arr.length;
        radixsort(arr, n);
        print(arr, n);
    }
    //HEAP SORT
    static {
        int arr[] = {129, 24, 15, 135, 87, 275, 99, 120, 32, 44, 152, 127, 23, 105, 89, 134, 69, 42, 140, 217, 85, 46};
        int n = arr.length;
        long startTime = getTime();
        SortMethods ob = new SortMethods();
        ob.sort(arr);
        long endTime = getTime(); 
        System.out.println("HEAP SORT: ");
        System.out.println("Time of Heap sort: " + timer(startTime, endTime));
        printArray(arr);
     
    }
      //MERGE SORT
      static {
       int arr[] = {129, 24, 15, 135, 87, 275, 99, 120, 32, 44, 152, 127, 23, 105, 89, 134, 69, 42, 140, 217, 85, 46};
        long startTime = getTime();
       SortMethods ob = new SortMethods();
        ob.sort(arr);
        long endTime = getTime(); 
        System.out.println("MERGE SORT: ");
        System.out.println("Time of Merge sort: " + timer(startTime, endTime));
        printArray(arr);
    }
      //This part inserts and orders the numbers and also calculates the time
    public static int[] doInsertionSort(int[] input){
        long startTime = getTime(); 
        int temp;
        for (int i = 1; i < input.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(input[j] < input[j-1]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        long endTime = getTime(); 
        System.out.println("Time of Insert sort: " + timer(startTime, endTime));
        return input;
        
     }
 
    // A utility function to get maximum value in arr[]
    static int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }
 
    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    static void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);
 
        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[ (arr[i]/exp)%10 ]++;
 
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];
 
        // Build the output array
        for (i = n - 1; i >= 0; i--)
        {
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }
 
        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }
 
    // The main function to that sorts arr[] of size n using
    // Radix Sort
    static void radixsort(int arr[], int n)
    {
        long startTime = getTime(); 
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);
 
        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(arr, n, exp);
        long endTime = getTime(); 
        
        System.out.println("Time of Radix Sort: " + timer(startTime, endTime));
    }
 
    // A utility function to print an array
    static void print(int arr[], int n)
    {
        for (int i=0; i<n; i++)
            System.out.print(arr[i]+" ");
    }
 public void sorted(int arr[])
    {
        int n = arr.length;
 
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
 
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
 
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        // If largest is not root
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
 
    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
    /* Java program for Merge Sort */
class MergeSort
{
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
 
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    void sorting(int arr[], int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;
 
            // Sort first and second halves
            sorting(arr, l, m);
            sorting(arr , m+1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
 
    /* A utility function to print array of size n */
    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
 }
}

