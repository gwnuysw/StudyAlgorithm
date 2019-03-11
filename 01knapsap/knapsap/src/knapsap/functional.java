package knapsap;

import java.util.LinkedList;

public class functional extends knapsap_solution{
	
private	LinkedList<Tuple> S = new LinkedList<Tuple>();
private	LinkedList<Tuple> SP = new LinkedList<Tuple>();

	/*그래프를 초기화 합니다.*/
	public functional()
	{
		S.push(new Tuple());
	}
	
	public void graph()
	{
		System.out.println("배낭이 수용할 수 있는 범위 내의 그래프는 다음과 같습니다.");
		for(int i = 0; M > S.get(i).x; i++)
		{
			System.out.println("("+S.get(i).x+","+S.get(i).y+")");
		}
		System.out.println("맨 아래 줄에 있는 좌표값 y가 최대 이익입니다.");
	}
	
	@Override
	public void solution() {}
		
	/*실수를 사용하는 배낭문제 해법은 병합정렬 알고리즘과 모습이 유사합니다.*/
	public void solution(int i)
	{
		/*함수의 매개변수는 재귀호출의 탈출을 위해서 사용합니다.*/
		if(i-1 < n)
		{
			/*함수 S를 각 물건의 x,y좌표인 이익과 무게 별로 평행 이동하여 함수 SP를 구합니다.*/
			for(int index = 0; index < S.size(); index++)
			{
				Tuple node = new Tuple();
				node.x = S.get(index).x + W[i];
				node.y = S.get(index).y + P[i];		
				SP.addLast(node);
			}
			/*함수 S와 SP를 병합 하여 새로운 함수 S를 구합니다.*/
			merge();
			
			solution(i+1);
		}
	}
	private void merge()
	{
		LinkedList<Tuple> NextS = new LinkedList<Tuple>();	//임시 저장 버퍼

		while(S.size() > 0 && SP.size() > 0)
		{
			/*합병시 두 원소 (x1, y1),(x2,y2)가 x1 >= x2 && y1 <= y2이면 (x1,y1)을 제거할 뿐만 아니라*/
			if( S.getFirst().x < SP.getFirst().x )
			{
				if(S.getFirst().y >= SP.getFirst().y)
				{
					SP.pop();
				}
				else
				{
					NextS.addLast(S.pop());
				}
			}
			else /*x < x2 && y1 > y2 이면 (x2,y2)도 제거 합니다.*/
			{
				if(S.getFirst().y <= SP.getFirst().y)
				{
					S.pop();
				}
				else
				{
					NextS.addLast(SP.pop());
				}
			}
		}
		/*S 나 SP 남은 쪽을 모두 NextS에 삽입합니다.*/
		while(SP.size() > 0)
		{
			NextS.addLast(SP.pop());
		}
		
		while(S.size() > 0)
		{
			NextS.addLast(S.pop());
		}
		
		S.addAll(NextS);//S에 모두 복사
		
		/*병합 확인
		System.out.println("@@@@@@@@@@@@@@@merged@@@@@@@@@@@@@@@@@@");
		
		for(int i = 0; i < S.size(); i++)
		{
			System.out.println("("+S.get(i).x+","+S.get(i).y+")");
		}*/
	}
}
