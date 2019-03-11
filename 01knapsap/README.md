# 알고리즘

작성자 : 이석원

작성일 : 2018-06-07

>0-1 배낭문제에 대한 동적 계획법 1,2,3 알고리즘을 구현하고 다음 예제에 적용하시오.
배낭의 크기는 13이고, 물건의 크기와 이익은 다음 표와 같다.

|i|Pi|Wi|
|---|---|---|
|1|4|2|
|2|6|4|
|3|8|5|
|4|9|8|
|5|6|3|

> 주의할점
>
>1) 업로드할 제출물은 소스코드+설명서이고  설명서에는 프로그램개요, 프로그램 구조, 프로그램설계, 프로그램실행결과, 결과분석 등의 내용이 자세하게 기술되어야 합니다.   당연히 소스코드에는 주석문이 포함되어야 합니다.
>
>2) 스스로 할 수 있는한까지 해서 제출할 것


## 파일 구성

* 01knapsap

  * knapsap

    * bin (compiled files)
    * src (source codes)

      * knapsap

        * knapsapmain.java (main 함수가 있다.)
        * knapsap_solution.java (추상 클래스)
        * brutal.java (동적 계획법 1)
        * stepping_stone.java (동적 계획법 2)
        * functional.java (동적 계획법 3)
        * Tuple.java (동적 계획법 3에서 사용하는 좌표쌍)
  * README.md (보고서)
  * capture (이미지 파일들)
## 프로그램 개요

01배낭 문제를 동적 계획법을 사용하여 해결합니다.

```
            |   0                                       if i = 0 or w = 0  
K[i][w] = --|   K[i-1][w]                               if w < W[i]
            |   maximum{K[i-1][w], K[i-1][w-W[i]]+P[i]} o.w
```
1. 주어진 물건이 없는 경우나 배낭 용량이 0인 경우 최적해는 0입니다.
2. 물건의 무게가 배낭의 용량을 넘어선다면 그 물건은 배낭에 포함되지 않습니다.
3. i 번째 물건이 배낭에 들어갈때의 최적해와 들어가지 않을때의 최적해 중 더 큰 값이 우리가 구할 해답입니다.

## 프로그램 구조

프로그램은 크게 4부분으로 나뉩니다.

**knapsap_solution**

추상 메소드 solution()을 가지는 추상 클래스이며 자식 클래스 brutal, stepping_stone, functional 클래스 모두 이 추상 메소드를 구현합니다.

**brutal**

- solution()

  동적 계획법 중 최대 이익을 나타내는 배열 K를 전체 순회 하며 배낭의 들어갈 물건의 무게와 이익은 모두 정수형입니다.

**stepping_stone**

- solution()

  배열 K를 전체 순회 하는 brutal 클래스에 findIndex 메소드를 이용하여 순회 해야할 배열의 인덱스를 찾고, 최대 이익을 구하는데 필요한 배열 요소만 띄엄띄엄 참조합니다.

- findIndex()

  인덱스를 찾는 원리는 아래의 표와 같습니다. 배낭의 최대 용량인 i = 5번째 행부터 시작하며, i번째 행의 정보는 i+1번째 행의 정보를 그대로 복사한 한후, 복사한 각 열과 W[i+1]차를 열의 정보에 추가하면(배열의 범위를 넘지 않아야 한다.) 탐색 해야할 배열의 원소가 됩니다.

| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10| 11|W[ i ]|
|---|---|---|---|---|---|---|---|---|---|---|---|
|1|4|1|6|9|**5**|**8**|**2**|**5**|**10**|**13**|W[2] = 4|
|-|-|-|-|-|5|8|**2**|**5**|**10**|**13**|W[3] = 5|
|-|-|-|-|-|-|-|2|5|**10**|**13**|W[4] = 8|
|-|-|-|-|-|-|-|-|-|10|**13**|W[5] = 3|
|-|-|-|-|-|-|-|-|-|-|13|배낭의 용량|

[표 1]

**functional**

- solution(i)

  solution메소드는 병합정렬과 유사합니다. 다만 (x,y) 좌표 정보를 사용한다는 점과, 병합정렬의 L 노드는 solution(i)에서 병합 정렬 되는 목표 S 함수 이고, R 노드 SP함수는 L 노드에서 S함수를 P[i],W[i]만큼 평행 이동하여 만듭니다.
  - i - 1 < n 이라면 재귀 호출을 탈출 합니다.
  - 먼저 S함수 에서 P[i], W[i]만큼 평행 이동하여 SP함수를 구합니다.
  - S함수의 좌표와 SP함수의 좌표를 병합 정렬한후 정렬된 좌표를 S에 저장합니다. (merge메소드)
  - solution(i+1)을 호출합니다.

- merge()

  합병시 두 원소 (x1, y1),(x2,y2)가 x1 >= x2 && y1 <= y2이면 (x1,y1)을 제거할 뿐만 아니라 x1 < x2 && y1 > y2 이면 (x2,y2)도 제거 합니다.

## 프로그램 설계

### 01knapsap_solution

![초기화, 변수](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/1.png?raw=true)

![max](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/2.png?raw=true)

### brutal

![brutal.solution](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/3.png?raw=true)

### stepping_stone

![findIndex_comments](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/6.png?raw=true)

![findIndex](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/4.png?raw=true)

![stepping_stone.solution](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/5.png?raw=true)

### functional

![functional.solution](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/7.png?raw=true)

![merge1](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/8.png?raw=true)

![merge](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/9.png?raw=true)

## 실행 결과

### main

![main](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/10.png?raw=true)

### knapsap_solution

![whatstuff](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/14.png?raw=true)

### brutal

![brutal.excute](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/11.png?raw=true)

### stepping_stone

![stepping.excute](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/12.png?raw=true)

### functional

![functional.excute](https://github.com/gwnuysw/StudyAlgorithm/blob/master/01knapsap/capture/13.png?raw=true)

## 결과 분석


### brutal

기준연산을 22행의 `if(w < W[i])` 라고 했을때 기준연산 실행 횟수는 `5 x 13 = 65` 65회 입니다.

### stepping_stone

기준연산을 25행의 `if(w < W[i]) ` 라고 했을때 기준연산 실행 횟수는 24회 입니다. (표 1 참고)

### functional

배낭에 들어갈 물건의 무게와 이익의 값에 실수를 허용하는 방법입니다. 그러나 과연 어떤 물건이 배낭에 들어갈지 결정하는 문제는 풀지 못한 의문입니다. `(x1, y1),(x2,y2)가 x1 >= x2 && y1 <= y2이면 (x1,y1)을 제거`할 때 1번 물건이 배낭에 안들어 간다고 생각 했지만 `x1 < x2 && y1 > y2 이면 (x2,y2)도 제거` 할 경우가 있습니다. 01 배낭문제의 "원칙에 충실하자"는 구호에 오히려 반대가 되는 상황이 아닌가 생각하게 됩니다.
