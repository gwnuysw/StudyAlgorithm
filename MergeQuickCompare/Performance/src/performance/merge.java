package performance;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class merge {

	private int[] sorted;	//sorted data buffer
	private long sortTime;
	public  merge()
	{

	}
	public void mergesort(int[] list, int low, int high, int threshold)
	{
		sorted = new int[high];

		Instant startTime;
		Instant endTime;

		startTime = Instant.now();	//wall-clock start point
		merge_sort_DC(list, low,high-1, threshold);	//excution
		endTime = Instant.now();//wall-clock end point
		sortTime = ChronoUnit.MILLIS.between(startTime, endTime);	//wall-clock time
	}
	public long getsortTime()
	{
		return sortTime;
	}
	/*merge sort algorithm methods with threshold*/
	private void merge_sort_DC(int[] list, int low, int high, int threshold)
	{
		int middle;
		if(low+threshold <= high)	//this is threshold distinguish point
		{
			middle = (low+high)/2;
			merge_sort_DC(list, low,middle, threshold);
			merge_sort_DC(list, middle+1,high, threshold);
			mergeDC(list, low,middle,high-1);
		}
		else
		{
			/*over threshold point will run insert sort Algorithm*/
		   for(int index = low ; index <= high ; index++){

			   int temp = list[index];
			   int aux = index - 1;

			   while( (aux >= 0) && ( list[aux] > temp ) ) {

				   list[aux+1] = list[aux];
			      aux--;
			   }
			      list[aux + 1] = temp;
		   }
		}
		/*insert sort Algorithm*/
	}
	private void mergeDC(int[] list, int low, int mid, int high)
	{
		int n1=low, n2=mid+1, s=low;
		int i;
		while(n1 <= mid && n2 <= high)
		{
			if(list[n1]<=list[n2])	sorted[s++] = list[n1++];

			else sorted[s++]=list[n2++];
		}
		if(n1>mid)	while(n2<=high)sorted[s++] = list[n2++];
		else while(n1<=mid)sorted[s++] = list[n1++];
		for(i=low;i<=high;i++)list[i] = sorted[i];
	}
	/*mergesort method with threshold*/
}