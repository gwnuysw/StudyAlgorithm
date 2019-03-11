package knapsap;

public class brutal  extends knapsap_solution{
	
	public brutal()
	{
		
	}
	public void solution()
	{
		int i, w;
		
		for(w = 0; w <= M; w++) K[0][w] = 0;
		for(i = 0; i <= n; i++) K[i][0] = 0;
		
		/*배열 전체를 순회*/
		for(i = 1; i <= n; i++)
		{
			for(w = 1; w <= M; w++)
			{
				/*배낭의 용량보다 물건의 무게가 훨씬 크다면 그 물건은 배낭에 들어갈 수 없다.*/
				if(w < W[i])
				{ 
					K[i][w] = K[i-1][w];
				}
				else
				{
					/*배낭에 들어가는 경우(K[i-1][w]) 배낭에 들어가지 않는 경우 (w[i-1][w-W[i]]+P[i])*/
					K[i][w] = max(K[i-1][w], K[i-1][w-W[i]]+P[i], i);
				}
			}
		}
	}
}
