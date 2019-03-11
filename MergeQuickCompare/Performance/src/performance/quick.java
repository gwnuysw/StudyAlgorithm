package performance;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class quick {
	private long sortTime;
	public quick()
	{

	}
	public void quicksort(int[] list, int low, int high, int threshold)
	{
		Instant startTime;
		Instant endTime;
		
		startTime = Instant.now();
		quicksort_DC(list,low,high-1, threshold);
		endTime = Instant.now();
		sortTime = ChronoUnit.MILLIS.between(startTime, endTime);		
	}
	public long getsortTime()
	{
		return sortTime;
	}
	
	private void quicksort_DC(int list[], int low, int high, int threshold)
	{
		int pivot_pos;
		/*threshold distinguish point quicksort will excute under threshold*/
		if(low+threshold <= high)
		{
			pivot_pos = partition(list,low,high);
			quicksort_DC(list,low,pivot_pos-1, threshold);
			quicksort_DC(list,pivot_pos+1,high, threshold);
		}
		/*insert sort excute over threshold*/
		else
		{
			/*insert sort Algorithm*/
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
	}
		
	private int partition(int list[],int low, int high)
	{
		int i, j = low;
		int temp;
		for(i=low+1;i<=high;i++)
		{
			if(list[i]<list[low])
			{
				j++;
				temp=list[i];
				list[i] = list[j];
				list[j] = temp;
			}
			
		}
		temp = list[low];
		list[low] = list[j];
		list[j] = temp;
		return j;
	}
}