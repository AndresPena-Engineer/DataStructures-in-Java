/* 
***********QuickSort**************

It is a Divide and Conquer algorithm. It picks an element as pivot and partitions the given array around the picked pivot. 
There are many different versions of quickSort that pick pivot in different ways.
    1. Always pick first element as pivot.
    2. Always pick last element as pivot.
    3. Pick a random element as pivot.
    4. Pick median as pivot.
 The key process in quickSort is partition(). Target of partitions is, given an array and an element x of array as pivot, 
 put x at its correct position in sorted array and put all smaller elements (smaller than x) before x, and put all greater elements (greater than x) after x. 
 All this should be done in linear time.

 */

import java.io.*;
import java.util.*;
 
//Method for the quicksort
public class quickSort {
    
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
        quickSort sorter = new quickSort();
         System.out.println("QUICK SORT: ");
        int[] input = {129, 24, 15, 135, 87, 275, 99, 120, 32, 44, 152, 127, 23, 105, 89, 134, 69, 42, 140, 217, 85, 46};
        long startTime = getTime(); 
        sorter.sort(input);  
        long endTime = getTime();
        System.out.println("Time of quicksort: " + timer(startTime, endTime)); 
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
        System.out.println("Time of insert sort: " + timer(startTime, endTime));
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
        
        System.out.println("Time of radix: " + timer(startTime, endTime));
    }
 
    // A utility function to print an array
    static void print(int arr[], int n)
    {
        for (int i=0; i<n; i++)
            System.out.print(arr[i]+" ");
    }
 
}
