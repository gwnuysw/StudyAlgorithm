### huffman.java
huffman 객체는 입력파일을 총 2번 읽습니다. 첫번째는 생성자 호출시 허프만 테이블과 코드를 생성하기 위해서, 두 번째는 compress호출시 허프만 테이블을 이용해서 단어를 변환 하는 작업입니다.

---
### 데이터 필드

```
14    private ArrayList<Table> wordTable = new ArrayList<Table>();
15    private MinHeap sort;
16    String filename;
```
* wordTable

  Table객체를 원소를 같는 리스트 입니다.

* sort

  리스트 wordTable의 각 원소가 빈도수에 맞춰서 최소힙 구조를 이룹니다.
* filename

  입력파일 이름

---

### 메소드들

* 생성자 huffman
* sort
* BinaryCoding
* traverseInOrder
* compress

---
### huffman

```
17  public huffman(String fn)
18	{
19		filename = fn;
20		try(BufferedReader inputStream = new BufferedReader(
21				new FileReader(filename)))
```

17행 파일 이름을 매개변수로 받아옵니다.
20행 21행 입력 스트림을 연결 합니다. 중요한 점은 byte단위 raw데이터를 입력하지 않고 formatted 형식의 문자열을 읽기 때문에 BufferedReader스트림을 개방합니다.

```
25  for(int i = 0; (Line = inputStream.readLine()) != 26  null; i++)
27  {
28    String[] temp = new String[Line.split(" ").length];
29    temp =  Line.split(" ");  
```
25행 데이터를 한줄씩 읽습니다. 이점은 나중에 압축해제할때 따로 고려할 사항입니다. 왜냐하면 개행문자는 허프만 테이블에 포함되지 않기 때문입니다.
28, 29행은 읽어온 단어들을 공백 문자를 기준으로 각각 나눕니다. 이점 역시 따로 고려할 사항입니다. 공백은 허프만 테이블에 포함되지 않습니다.
```
30  for(int j = 0; j < temp.length; j++)
31  {
32  	int k;
33  	for(k = 0; k < wordTable.size(); k++)
34  	{
35    	if(wordTable.get(k).getWord().equals(temp[j]))
36  		{
37  			wordTable.get(k).incRate();
38  			break;
39  		}
40	}
```
30행 허프만 테이블을 하나씩 검색합니다. 저장되어 있는 단어는 wordTable에 있기 때문에 35행에서 저장 되어있는 단어와 저장할 단어를 비교한 후 같다면 37행에서 빈도수를 하나 증가하고 33행 반복문을 탈출합니다.

```
41  if(k == wordTable.size())
42  {
43		wordTable.add(new Table(temp[j]));
44  }
```
만약 wordTable의 처음 부터 끝까지 탐색했는데도 저장 하려는 단어가 wordTable에 없다면 새로 추가합니다.

---
### sort
wordTable을 가지고 최소힙을 구성합니다.

---
### BinaryCoding
```
65  while(!sort.isOne())
66  {
67  	Table left = sort.extractMin();
68  	Table right = sort.extractMin();
69  	Table parent = new Table();
70  	parent.setRight(right);
71  	parent.setLeft(left);
72  	parent.setParentRate(left.getRate()+right.getRate());
73  	sort.insert(parent);			
74  }
75  sort.getMin().code = 1;
76  traverseInOrder(sort.extractMin());
```
본격적으로 허프만 트리를 구성하는 단계입니다. 65행에서 최소힙에 저장된 노드가 하나가 될때까지 반복합니다. 다시말하면, 리스트의 원소가 하나가 될때까지 반복한 후 75행에서 이 마지막 하나의 원소도 리스트에서 삭제합니다. 67,68행에서 최소 빈도수를 갖는 두 노드를 꺼내서 69, 70, 71행에서 새로운 부모 노드를 만든후 좌우에 삽입하고 72행에서 부모 노드의 빈도수를 초기화 합니다.73행은 이렇게 만들어진 새로운 노드를 다시 최소힙에 삽입합니다. 허프만 트리를 완성한 후 75행에서 트리를 탐색하며 각 노드마다 허프만 코드값을 설정 합니다. 75행에서 루트노드의 코드값을 1로 설정합니다.

---
### traverseInOrder
```
78  if (node != null) {
79    traverseInOrder(node.getLeft(node.code));
80    traverseInOrder(node.getRight(node.code));
81    if(node.getWord() != null)
82  	{
83  	   wordTable.add(node);
84  	}
85 }
```
노드를 중위탐색합니다. 81행에서 해당 노드가 단말노드인지 아닌지 확인 하기 위해서 word값이 null인지 아닌지 즉 단어정보가 있는지 없는지를 확인한후 83행에서 비어있던 wordTable에 다시 삽입합니다.

---
### compress

```
91  try(BufferedReader inputStream = new BufferedReader(
92      new FileReader(filename));
93  		PrintWriter outputStream = new PrintWriter(
94      new FileWriter("compressed"+filename)))
```
허프만 테이블을 생성파일의 헤더에 삽입 하기위해서 입력스트림과 함께 출력 스트림을 엽니다.

```
98  for(int i = 0; i < wordTable.size(); i++)
99  {
100   outputStream.write(wordTable.get(i).getWord()+"``" );
101   outputStream.write(wordTable.get(i).code);
102   outputStream.write("&");
103 }
104 outputStream.write("#");
```
100행에서 허프만 테이블의 단어 부분을 파일에 기록하고 단어와 코드의 구분자로 역따옴표을 사용합니다. 101행에서 코드를 기록하고 102행에서 코드와 단어쌍의 구분자로 &를 사용합니다. 모든 데이터를 입력한후 104행에서 허프만 테이블의 끝을 알리는 #를 입력합니다.
```
115   for(k = 0; k < wordTable.size(); k++)
116   {
117			if(wordTable.get(k).getWord().equals(temp[j]))
118			{
119				outputStream.write(wordTable.get(k).code);
120				break;
121			}
122		}
123	}
124	outputStream.println();
```
생성자에서와 같은 파싱 과정을 거친후 wordTable을 탐색하여 119행에서 단어를 코드로 대체하여 기록합니다. 124행에서 개행문자를 삽입하여 나중에 압축 해제할때 한줄 단위로 구분하여 데이터를 읽도록합니다. 이는 허프만테이블에 개행문자가 없기 때문입니다.
