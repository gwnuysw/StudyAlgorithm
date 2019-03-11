### Table.java

테이블 클래스의 역할

1. 단어, 빈도수, 허프만 코드를 저장하고
2. 연결리스트의 원소이며
3. 최소힙구조의 노드입니다.

---
### 데이터 필드

```
4 private int rate;
5 private String word;  
6 private Table left, right;
7 int code = 0x0000;
```
6행은 트리구조를 위한 포인터입니다.

---
### 허프만 코드생성을 위한 메소드
```
45  public Table getLeft(int c)
46  {
47    if(left != null)
48  	{
49  		left.code = c << 1;
50  		left.code++;
51  	}
52  	return this.left;
53  }
54  public Table getRight(int c)
55  {
56  	if(right != null)
57  	{
58  		right.code = c <<1;
59  	}
60  	return this.right;
61  }
```
트리 순차시 사용하는 메소드들인데 각각47, 56행에서 노드의 왼쪽 노드에는 1비트를 추가하고 오른쪽 노드에는 0비트를 추가합니다.
