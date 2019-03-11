package performance;

import java.util.Random;
import java.util.Scanner;

public class TestTwoAlgorithmWithSameThreshold {

	private long[] mergeSortTestTime = new long[100];
	private long[] quickSortTestTime = new long[100];

	private merge mergeSort = new merge();
	private quick quickSort = new quick();
	private Scanner sc;

	public TestTwoAlgorithmWithSameThreshold()
	{

	}
	/*user communicate this method.*/
	public void ThresholdCompare()
	{
		String sort;
		long sumMerge = 0;
		long sumQuick = 0;
		long everageMerge;
		long everageQuick;

		sc = new Scanner(System.in);

		int threshold;

		System.out.println("Tatal amount of data is 7000");
		System.out.println("enter 'y' to excute merge and quick sort algorithm with sorted data, the others will excute with not sorted data");

		sort = sc.next();

		System.out.println("assign threshold point");
		threshold = sc.nextInt();

		if(sort.equals("y"))
		{
			/*100times of merge and quick sort*/
			sortedData(threshold);
			/*everage*/
			for(int i = 0; i<100; i++)
			{
				sumMerge += mergeSortTestTime[i];
				sumQuick += quickSortTestTime[i];
			}
			everageMerge = sumMerge/100;
			everageQuick = sumQuick/100;

			System.out.println("everage of 100 MergeSort wall clock time is "+ everageMerge+" Mills with threshold "+threshold);
			System.out.println("everage of 100 QuickSort wall clock time is "+everageQuick+" Mills with threshold "+threshold);
		}
		else
		{
			/*100times of merge and quick sort*/
			notSortedData(threshold);
			/*everage*/
			for(int i = 0; i<100; i++)
			{
				sumMerge += mergeSortTestTime[i];
				sumQuick += quickSortTestTime[i];
			}
			everageMerge = sumMerge/100;
			everageQuick = sumQuick/100;

			System.out.println("everage of 100 MergeSort wall clock time is "+ everageMerge+" Mills with threshold "+threshold);
			System.out.println("everage of 100 QuickSort wall clock time is "+everageQuick+" Mills with threshold "+threshold);
		}
	}
	private void sortedData(int threshold)
	{
		/*data input sorted data*/
		int longSorted[] = new int [7000];
		for(int i = 0; i < 7000; i++)
		{
			longSorted[i] = i;
		}
		System.out.println("excute with sorted data");

		/*excute 100 times for everage data*/
		for(int i = 0; i < 100; i++)
		{
			mergeSort.mergesort(longSorted, 0, 7000,threshold);
			mergeSortTestTime[i] = mergeSort.getsortTime();
			quickSort.quicksort(longSorted, 0, 7000, threshold);
			quickSortTestTime[i] = quickSort.getsortTime();
		}
	}
	private void notSortedData(int threshold)
	{
		/*use Random object to assign not sorted data*/
		int randomData = 0;
		Random random = new Random();
		int longNotSortedQuick[] = new int [7000];
		int longNotSortedMerge[] = new int [7000];

		System.out.println("excute with not sorted data");
		/*excute 100 times for everage data*/
		for(int i = 0; i < 100; i++)
		{
			/*reassign non sorted data set in every excution because it will be sorted*/
			for(int j = 0; j < 7000; j++)
			{
				randomData = random.nextInt();
				longNotSortedMerge[j] = randomData;
				longNotSortedQuick[j] = randomData;
			}
			mergeSort.mergesort(longNotSortedMerge, 0, 7000,threshold);
			mergeSortTestTime[i] = mergeSort.getsortTime();
			quickSort.quicksort(longNotSortedQuick, 0, 7000, threshold);
			quickSortTestTime[i] = quickSort.getsortTime();
		}
	}
}