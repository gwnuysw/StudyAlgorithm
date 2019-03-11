# 알고리즘
작성자 : 이석원

작성일 : 2018-05-07
수정일 : 2018-05-17 21:24
>1. 퀵정렬과 합병정렬의 성능을 비교분석하시오.
>2. 퀵정렬과 합병정렬 각각에 대해, 임계값을 변화시키면서 성능의 변화를 분석하시오. (단, 임계값이하의 입력은 삽입 정렬을 사용)
>
> 주의할점
>
>1) 업로드할 제출물은 소스코드+설명서이고  설명서에는 프로그램개요, 프로그램 구조, 프로그램설계, 프로그램실행결과, 결과분석 등의 내용이 자세하게 기술되어야 합니다.   당연히 소스코드에는 주석문이 포함되어야 합니다.
>
>2) 스스로 할 수 있는한까지 해서 제출할 것

## 파일 구성

* mergequickcompare
  * Performance
    * src
      * AlgorithmPerformance.java
      * TestTwoAlgorithmWithSameThreshold.java
      * merge.java
      * quick.java
    * bin
      * AlgorithmPerformance.class
      * TestTwoAlgorithmWithSameThreshold.class
      * merge.class
      * quick.class
  * capture
    * (캡쳐 사진들)
  * README.md
    * (보고서)

## 프로그램 개요
두 알고리즘의 비교점은 두 가지 입니다.

1. 일반적인 경우 병합정렬과 퀵정렬의 복잡도는 Θ(nlogn)으로 같습니다. 단, 정렬하고자 하는 데이터가 정렬되어 있는 경우 병합정렬의 복잡도는 Θ(nlogn)이지만 퀵정렬의 경우 최악의 상황이며 복잡도는 Θ(n^2)입니다.
2. 최악의 경우 이거나 일반적인 경우 각각 같은 알고리즘이라도 임계점이 다르면 실행시간이 다를 수 있습니다.

AlgorithmPerformance 프로그램의 주요 흐름(알고리즘)은 다음과 같습니다.
```
do{
branch  1: 정렬된 데이터를 사용할 때
          1.1 임계값을 설정한다.
          1.2 병합정렬을 100회 실행하여 평균값을 구한다.
          1.3 퀵정렬을 100회 실행하여 평균값을 구한다.
branch  2: 정렬되지 않은 데이터를 사용할 때
          2.1 임계값을 설정한다.
          2.2 병합정렬을 100회 실행하여 평균값을 구한다.
          2.3 퀵정렬을 100회 실행하여 평균값을 구한다.
}while(다른 임계값을 더 확인하고 싶다면 반복)
```

## 프로그램 구조
#### AlgorithmPerformance.java
```
do{

}while()
```
  - main

   프로그램을 계속 실행 할지 말지 유저에게 물어봅니다.
   TestTwoAlgorithmWithSameThreshold.ThresholdCompare()를 호출합니다.

#### TestTwoAlgorithmWithSameThreshold.java
  - ThresholdCompare

    알고리즘에서 1번을 실행할지 2번을 실행할지 사용자에게 물어봅니다. 사용자의 의사를 전달받은 프로그램은 단계별로 프로그램을 100회 실행 하여 벽시계 시간의 평균을 구합니다.

  - sortedData

    정렬된 데이터를 사용해서 각 정렬 알고리즘을 100번씩 반복 실행합니다.
  - notSortedData

    정렬되지 않은 데이터를 사용해서 각 정렬 알고리즘을 100번씩 반복 실행합니다.

### merge.java
  - merge_sort_DC

    임계점을 갖는 병합정렬을 실행합니다.
  - mergesort

    병합정렬 실행시 벽시계 시간을 저장하고 getsortTime메소드를 이용하여 다른 객체에 전달할수 있습니다.
  - getsortTime

    벽시계 시간을 반환 합니다.

### quick.java
  - quicksort_DC

    임계점을 갖는 퀵정렬을 실행합니다.
  - quicksort

    퀵정렬 실행시 벽시계 시간을 저장하고 getsortTime메소드를 이용하여 다른 객체에 전달할수 있습니다.
  - getsortTime

    벽시계 시간을 반환 합니다.

## 프로그램 설계

### AlgorithmPerformance.java
```
09  public static void main(String[] args) {
10    // TODO Auto-generated method stub
11    TestTwoAlgorithmWithSameThreshold t = new TestTwoAlgorithmWithSameThreshold();
12    Scanner sc = new Scanner(System.in);
13    String endFlag;
14    do {
15      t.ThresholdCompare();
16      System.out.println("Program continue if you enter 'y', the athers will terminate program");
17      endFlag = sc.next();
18      }while(endFlag.equals("y"));
19  }
```
15행에서 ThresholdCompare를 호출한후 사용자에게 더 진행 할지 질의 합니다.

### TestTwoAlgorithmWithSameThreshold.java

```
18  public void ThresholdCompare()
19  {
20   String sort;
21   long sumMerge = 0;
22   long sumQuick = 0;
23   long everageMerge;
24   long everageQuick;
25
26   sc = new Scanner(System.in);
27
28   int threshold;
29
30   System.out.println("sorted data amount is 7000, not sorted data amount is 10000");
31   System.out.println("enter 'y' to excute merge and quick sort algorithm with sorted data, the others will excute with not sorted data");
32
33   sort = sc.next();
34
35   System.out.println("assign threshold point");
36   threshold = sc.nextInt();
```
21행은 정렬된 데이터를 사용할지 말지를 사용자에게 물어보기 위한 변수 입니다.

21~24행은 평균 실행시간을 구하기 위한 변수들 입니다.


30행에서 총 데이터 개수가 7000개인 이유는 데이터 개수가 7000대보다 많으면 정렬된 데이터를 퀵정렬 실행시 스택오버플로우 오류가 발생하기 때문에 적당히 7000개의 데이터 개수를 사용했습니다.

35~36행은 임계점을 사용자로부터 입력받습니다.

```
38  if(sort.equals("y"))
39  {
40    /*100times of merge and quick sort*/
41    sortedData(threshold);
42    /*everage*/
43    for(int i = 0; i<100; i++)
44    {
45      sumMerge += mergeSortTestTime[i];
46      sumQuick += quickSortTestTime[i];
47    }
48    everageMerge = sumMerge/100;
49    everageQuick = sumQuick/100;
50
51    System.out.println("everage of 100 MergeSort wall clock time is "+ everageMerge+" Mills with threshold "+threshold);
52    System.out.println("everage of 100 QuickSort wall clock time is "+everageQuick+" Mills with threshold "+threshold);
53  }
```
사용자가 정렬된 데이터를 사용하기 원할경우 실행되는 블록입니다.
41행에서 SortedData메소드를 호출하며

43~52행까지는 100번 실행된 정렬 알고리즘의 벽시계 시간의 평균을 구하고 결과를 출력합니다.

뒤이어 else문은 notSortedData를 호출한다는 사실만 다르고 모두 동일합니다.

```
71  private void sortedData(int threshold)
72  {
73    /*data input sorted data*/
74    int longSorted[] = new int [7000];
75    for(int i = 0; i < 7000; i++)
76    {
77      longSorted[i] = i;
78    }
79    System.out.println("extuce with sorted data");
80
81    /*excute 100 times for everage data*/
82    for(int i = 0; i < 100; i++)
83    {
84      mergeSort.mergesort(longSorted, 0, 7000,threshold);
85      mergeSortTestTime[i] = mergeSort.getsortTime();
86      quickSort.quicksort(longSorted, 0, 7000, threshold);
87      quickSortTestTime[i] = quickSort.getsortTime();
88    }
89  }
```
73~78행은 정렬할 데이터를 초기화 합니다.

82~88행은 각 정렬 알고리즘을 100번씩 실행하여 그 시간 값을 각각의 배열에 저장합니다.

```
90  private void notSortedData(int threshold)
91  {
91    /*use Random object to assign not sorted data*/
92    int randomData = 0;
93    Random random = new Random();
94    int longNotSortedQuick[] = new int [7000];
95    int longNotSortedMerge[] = new int [7000];
96
97    System.out.println("excute with not sorted data");
98    /*excute 100 times for everage data*/
99    for(int i = 0; i < 100; i++)
100   {
101     /*reassign non sorted data set in every excution because it will be sorted*/
102     for(int j = 0; j < 7000; j++)
103     {
104       randomData = random.nextInt();
105       longNotSortedMerge[j] = randomData;
106       longNotSortedQuick[j] = randomData;
107     }
108     mergeSort.mergesort(longNotSortedMerge, 0, 7000,threshold);
109     mergeSortTestTime[i] = mergeSort.getsortTime();
110     quickSort.quicksort(longNotSortedQuick, 0, 7000, threshold);
111     quickSortTestTime[i] = quickSort.getsortTime();
112   }
113 }
```
sortedData와 가장 큰 차이점은 랜덤 객체를 사용하고, 각 정렬알고리즘 마다 다른 데이터 세트를 사용한다는 점입니다.

또한 정렬할 데이터는 102\~107행에서 매번 변경되는데 그 이유는 108\~111행에서 데이터가 정렬되기 때문입니다.

### merge.java

```
13  public void mergesort(int[] list, int low, int high, int threshold)
14  {
15    sorted = new int[high];
16  
17    Instant startTime;
18    Instant endTime;
19  
20    startTime = Instant.now();	//wall-clock start point
21    merge_sort_DC(list, low,high-1, threshold);	//excution
22    endTime = Instant.now();//wall-clock end point
23    sortTime = ChronoUnit.MILLIS.between(startTime, endTime);	//wall-clock time
24  }
```
가장 중요한 벽시계 시간을 이용한 병합정렬의 실행시간이 계산되는 부분입니다.

20행과 22행의 Instant.now()메소드는 시스템 클락을 얻기위한 메소드입니다. [javaDocumentation](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html#now--)에 찾아보면 이렇게 나와있습니다.
>Obtains the current instant from the system clock.

23행의 ChronoUnit도 [javaDocumentation](https://docs.oracle.com/javase/8/docs/api/java/time/temporal/ChronoUnit.html#between-java.time.temporal.Temporal-java.time.temporal.Temporal-)에 찾아보면 이렇게 나와있습니다.
>Calculates the amount of time between two temporal objects.

```
...
33  if(low+threshold <= high)	//this is threshold distinguish point
...
40  else
41  {
42    /*over threshold point will run insert sort Algorithm*/
43      for(int index = low ; index <= high ; index++){
44
45       int temp = list[index];
46       int aux = index - 1;
47
48        while( (aux >= 0) && ( list[aux] > temp ) ) {
49
50          list[aux+1] = list[aux];
51          aux--;
52        }
53          list[aux + 1] = temp;
54     }
55  }
```
나머지 메소드의 부분은 병합정렬에 대한 내용입니다. 다른 점은 33행에서 low+threshold를 하여 임계점을 구분하고 최소 임계점은 1이기 때문에 <=기호를 사용합니다.
40~55행은 임계점을 벗어났을 때 실행하는 삽입정렬입니다.

### quick.java

퀵정렬의 내용도 큰 차이가 없습니다. quickSort메소드에서 벽 시계시간을 구하고 quicksort_DC에서 퀵정렬을 하되 임계치를 벗어나면 삽입정렬을 실행합니다.

## 퀵정렬과 합병절렬의 성능 비교분석
### 최악의 경우
![worst](https://github.com/gwnuysw/Merge-sort-and-Quick-sort-comaare/blob/master/capture/1.png?raw=true)

복잡도를 따르면 퀵정렬(`Θ(n^2)`)이 병합정렬(`Θ(nlogn)`)보다 더 느린데 결과 값도 그에 맞게 나왔습니다.
### 일반적인 경우
![general](https://github.com/gwnuysw/Merge-sort-and-Quick-sort-comaare/blob/master/capture/2.png?raw=true)

최악의 경우가 아니라면 두 알고리즘에 큰 차이는 없습니다.
## 서로 다른 임계값을 가질때 각 알고리즘의 성능 비교분석
### 정렬된 데이터를 이용할 때
![worst1](https://github.com/gwnuysw/Merge-sort-and-Quick-sort-comaare/blob/master/capture/1.png?raw=true)


![worst2](https://github.com/gwnuysw/Merge-sort-and-Quick-sort-comaare/blob/master/capture/3.png?raw=true)


![worst3](https://github.com/gwnuysw/Merge-sort-and-Quick-sort-comaare/blob/master/capture/4.png?raw=true)

![worst4](https://github.com/gwnuysw/Merge-sort-and-Quick-sort-comaare/blob/master/capture/9.png?raw=true)

**임계점을 늘려감에 따라 병합알고리즘 실행 시간에는 변화가 보이지 않습니다. 그러나 퀵정렬 알고리즘 실행시간은 점점 늘어납니다.** 심지어 임계값이 100일때가 임계값이 1일때 보다 10mills만큼 더 오래 걸렸습니다. 그러나 모든 데이터를 삽입정렬로 실행 한 경우 두 알고리즘의 실행시간은 0mills으로 차이가 없습니다. 삽입정렬은 원소의 개수가 적을 경우 성능이 가장 좋은것으로 알려져 있지만 정렬된 데이터의 경우 삽입정렬의 `while( (aux >= 0) && ( list[aux] > temp ) )`부분에서 조건을 충족시키지 못하여 반복문 자체를 실행하지 않기 때문입니다. 삽입정렬은 최선의 경우가 데이터가 이미 정렬되어 있는 경우이며 그때의 시간복잡도는 `Θ(n)` 입니다.

### 정렬되지 않은 데이터를 이용할때
![general1](https://github.com/gwnuysw/Merge-sort-and-Quick-sort-comaare/blob/master/capture/5.png?raw=true)

![general2](https://github.com/gwnuysw/Merge-sort-and-Quick-sort-comaare/blob/master/capture/6.png?raw=true)

![general2.5](https://github.com/gwnuysw/Merge-sort-and-Quick-sort-comaare/blob/master/capture/8.png?raw=true)

![general3](https://github.com/gwnuysw/Merge-sort-and-Quick-sort-comaare/blob/master/capture/7.png?raw=true)

정렬되지 않은 데이터를 사용할때 퀵정렬과 삽입정렬의 혼합은 놀라운 성능을 보입니다. 임계값이 1, 100, 1000일때 퀵정렬은 0mills의 실행시간을 보이며 병합정렬은 임계값이 커짐에 따라 실행시간이 조금씩 늘어납니다. 그러나 임계값이 5000이 되면서 퀵정렬도 실행시간이 늘어났고 임계값이 7000이 되면 두 알고리즘은 삽입 정렬만을 실행하기 때문에 실행시간 차이는 거의 나지 않습니다. 또한 정렬 시간도 가장 깁니다.(삽입정렬 시간 복잡도 : `Ο(n^2)`)
