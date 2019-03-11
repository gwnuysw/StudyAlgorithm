package knapsap;

import java.util.LinkedList;

public class stepping_stone extends knapsap_solution{
	
	private	LinkedList<LinkedList<Integer>> Lookup = new LinkedList<LinkedList<Integer>>();
	
	public stepping_stone()
	{	}
	public void solution()
	{
		findIndex();
		
		LinkedList<Integer> item;
		int i, w;
		for(i = 1; i <= n; i++)
		{
			/*배열이 아니라 연결리스트의 정보를 하나씩 꺼내어 확인하면서 배열을 순회합니다.*/
			item = Lookup.pop();
			while(item.size() > 0)
			{
				w = item.pop();	//열의 정보를 가져옵니다.
				if(w < W[i]) 
				{
					K[i][w] = K[i-1][w];
				}
				else 
				{
					K[i][w] = max(K[i-1][w], K[i-1][w-W[i]]+P[i], i);
				}
			}
		}
	}
	
	/*순회해야할 배열의 인덱스를 찾아주는 메소드 입니다.					*/
	/*각 행에서 참조 해야할 열의 정보는 target연결 리스트에 저장합니다.	*/
	/*5행 열의 정보부터 찾으며, 1행 열의 정보 까지 찾으면 종료합니다.		*/
	/*각 열의 정보는 각 물건의 무게 W[]배열을 이용하여 찾습니다.			*/
	/*각 물건을 안넣는 경우  K[i][w] = K[i-1][w] 그대로 값이 아래행으로 가고*/
	/*넣는 경우 K[i][w] = K[i-1][w-W[i]]로 값이 변경된다는 점을 이용했습니다.*/
	/*따라서 아래 행의 정보 그대로 가져온 것과 아래행의 정보에서 각 열 의 값마다 W[i+1]만 빼면 참조해야할 열이 됩니다.*/
	/*target은 또한 Lookup 이중연결리스트에 저장합니다.				*/
	
	private void findIndex()
	{
		LinkedList<Integer> target = new LinkedList<Integer>();
		
		target.add(13);		
		Lookup.add(target);//배낭의  용량을 먼저 삽입합니다.
		
		int LookupCount = 1;		//다음번에 순회할 열의 수를 가지고 있습니다.
		int LookupDecount = 0;
		
		for(int i = 4; i > 0; i--, LookupCount = LookupCount * 2 - LookupDecount)
		{
			LinkedList<Integer> item = new LinkedList<Integer>();
			item.addAll(Lookup.getFirst());
			
			for(int j = 0; j < LookupCount; j++)
			{
				if(item.get(j) > W[i+1])
				{
					item.add(item.get(j) - W[i + 1]);
				}
				/*배열의 왼쪽벽에 다다른 경우*/
				else 
				{
					LookupDecount ++;
				}
			}
			Lookup.addFirst(item);
		}
		
		/*참조해야할 배열 인덱스 정보를 확인합니다.
		for(int i = 0; i < 5; i ++)
		{
			for(int j = 0; j < Lookup.get(i).size(); j++)
			{
				System.out.print(Lookup.get(i).get(j)+" ");
			}
			System.out.println();
		}*/
	}
}
