## 멀티미디어

 Huffman coding, decoding program

 20141640 이석원

### Documentation
---

### 파일 구성

* huffman
  * java_scanner
  * compressedjava_scanner
  * src
    * MinHeap.java
    * Table.java
    * huffman.java
    * huffman_main.java
* huffmanDecode
  * compressedjava_scanner
  * extracted
  * src
    * extract.java
    * huffmanDecode_main.java
    * wordpare.java

---
### huffman

문서를 압축하는데 필요한 문서(java_scanner)와, 압축된 문서(compressedjava_scanner), 허프만 코드를 실행하는 클래스들을 담아놨습니다. 프로그램은 huffman_main에서 실행됩니다.

### [java_scanner](https://github.com/gwnuysw/huffmanHomeWork/blob/master/huffman/java_scanner)
압축할 문서는 java document 항목 중 하나인 [scanner](https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html)에 대한 문서를 사용했습니다. 문서의 크기는 **59,136 bytes** 입니다.

### [compressedjava_scanner](https://github.com/gwnuysw/huffmanHomeWork/blob/master/huffman/compressedjava_scanner)

java_scanner를 압축한 결과물 입니다. 문서의 헤더에 허프만 테이블이 등장합니다. 단어와 코드는 콜론 기호로 구분, 각 단어, 코드쌍은 !로 구분 테이블과 코딩된 문서의 구분은 #입니다.
```
(단어):(코드)!(단어):(코드)!......#(코딩된 문서...)
```
결과물의 크기는 **40,380 bytes** 입니다.

### huffman_main
huffman 객체를 이용합니다.

### [huffman.java](https://github.com/gwnuysw/huffman/blob/master/huffmandoc.markdown)

압축할 문서를 파싱한후 Table객체를 허프만 테이블을 작성하고 MinHeap객체를 이용하여 허프만 트리와 허프만 코드를 작성합니다. 작성된 허프만 테이블을 compressedjava_scanner 헤더에 기록한 후 허프만 코드를 기록합니다.

### [Table.java](https://github.com/gwnuysw/huffman/blob/master/Tabledoc.markdown)

허프만 테이블 작성시 Linked List와 함께 이진 노드의 구조를 같는 Table 객체를 이용합니다. 각 Table 노드는 Linked List 인덱스에 하나씩 저장 됩니다.

### MinHeap.java

허프만 트리 작성시 최소힙 구조를 이용합니다. MinHeap클래스에 대한 설명은 생략했습니다.

---
### huffmanDecode

압축된 문서를 해제하는데 필요한 압축된 문서(compressedjava_scanner), 압축 해제된 문서(extract), 압축하는데 필요한 클래스들을 담아 놨습니다. 프로그램은 huffmanDecode_main에서 실행 됨니다.

### compressedjava_scanner
압축된 문서를 해제하기 위해 huffman_main에서 생성된 문서를 huffmanDecode 패키지에 복사합니다.

### [extracted](https://github.com/gwnuysw/huffman/blob/master/huffmanDecode/extracted)

huffmanDecode_main을 실행한후 압축 해제된 문서입니다.

### [extract](https://github.com/gwnuysw/huffman/blob/master/extract.markdown)

extracted의 헤더를 파싱하여 허프만 데이블을 다시 작성한후 암호화된 부분을 읽어서 압축해제 합니다.

### wordpare

단어와 코드쌍을 하나의 노드로 저장하는 구조를 가지는 객체입니다.

---
### 압축률

59,136 bytes에서 40,380 bytes로 압축율은 약 31%입니다.

### 의문점

java에서 raw데이터 1byte 입력 스트림은 FileInputStream인데, 반환 값은 byte가 아니라 int입니다. 그렇다면 출력할때 FileOutputStream을 사용하지 않아도 알아서 raw 변환이 되는 것인가 의문이 남습니다. 이 부분이 이해가 안돼서 압축률이 작은 것 같습니다.
