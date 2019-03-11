package knapsap;

abstract class knapsap_solution {
	
protected	int[] P = new int[6];
protected	int[] W = new int[6];
protected	int M = 13;
protected	int n = 5;
protected	int Max = 100;
protected	int[][] K = new int[Max][Max];
protected	int[] stuff = new int[5];	//어떤 물건을 배낭에 넣어야 하는지 알기 위한 변수
	
	public knapsap_solution()
	{
		/*배낭에 들어갈 각 물건의 이익 초기화*/
		P[0] = 0;
		P[1] = 4;
		P[2] = 6;
		P[3] = 8;
		P[4] = 9;
		P[5] = 6;
		/*배낭에 들어갈 각 물건의 무게 초기화*/
		W[0] = 0;
		W[1] = 2;
		W[2] = 4;
		W[3] = 5;
		W[4] = 8;
		W[5] = 3;
	}
	/*동적바인딩을 위한 추상 메소드*/
	public abstract void solution();
	/*배낭에 들어갈 물건과 최대 이익을 알려주는 메소드*/
	protected void WhatStuff()
	{
		for(int i = 0; i <= n; i++)
		{
			for(int j = 0; j <= M; j++)
			{
				System.out.print(K[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("배낭에 들어가는 물건은 다음과 같습니다.");
		for(int i = 0; i < 5; i++)
		{
			if(stuff[i] != 0)
			{
				System.out.println(stuff[i]+" 번째");
			}
		}
		System.out.println("최대이익은 "+K[n][M]+"입니다.");
	}
	/*두 입력값중 더 큰값을 반환하는 메소드 									 	*/
	/*매개변수 i와 stuff를 이용해서 배낭에 어떤 물건을 넣어야 하는지 기록합니다.	*/
	protected int max(int a, int b, int i)	
	{
		if(a > b)
		{
			
			return a;
		}
		else
		{
			stuff[i-1] = i;	//K[i-1][w-W[i]]+P[i]이 K[i-1][w]보다 더 크다면 i는 배낭에 들어갑니다.
			return b;
		}
	}
}
