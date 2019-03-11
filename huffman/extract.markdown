### extract.java
압축된 파일의 헤더를 파싱하여 허프만 테이블을 재구성하고 암호화된 문서를 해석합니다.

---
### extract생성자

압축파일의 헤더를 파싱하는 부분과 암호화된 문서를 해독하는 두부분으로 나뉩니다.
```
33  while ((singleChar = inputStream.read()) != '#')
34  {
35    pre = (char) singleChar;
36    if(pre == '`')
37    {
38  		labelDiv = '`';
39    }
40    else if(pre == '&')
41    {
42      tobecomeString[i] = '\0';
43      word = new String(tobecomeString);
44      sigend = word.split("\0");
45      wordTable.add(new wordPare(sigend[0], code));
46      i = 0;
47      labelDiv = 0;
48      continue;
49    }
50    else if(labelDiv == 0)
51    {
52      tobecomeString[i++] = (char)singleChar;
53    }
54    else if(labelDiv == '`')
55    {
56      code = singleChar;
57    }
58  }
```
헤더 파싱은 까다롭습니다. labelDiv는 현재 singleChar가 읽고 있는 정보를 어떻게 처리할지에 대한 정보를 저장합니다. 단어부분을 처리해야 한다면 값은 0(초기값도 0)이고 코드 부분을 처리하고 있다면 역따옴표가 됩니다. 단어와 코드쌍을 모두 읽었다면, 즉 &를 만났다면 처리한 정보를 table에 저장합니다.

```
59  while((singleChar = inputStream.read()) != -1)
60  {
61    if(singleChar == '\n')
62    {
63     out.println();
64    }
65    else
66    {
67      outString = find(singleChar);
68      System.out.println(outString);
69      out.print(outString);
70      out.print(" ");
71    }
72  }
```
61~64행은 압축파일 생성과정에서 입력한 개행문자를 따로 처리하는 부분입니다. 개행문자는 허프만 테이블에 없습니다.
outString은 해독된 문자열을 저장합니다. 70행의 공백문자역시 허프만 테이블에 있는 정보가 아니라서 따로 처리합니다.
