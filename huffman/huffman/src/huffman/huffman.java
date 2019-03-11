package huffman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class huffman {
	private ArrayList<Table> wordTable = new ArrayList<Table>();
	private MinHeap sort;
	String filename;
	public huffman(String fn)
	{
		filename = fn;
		try(BufferedReader inputStream = new BufferedReader(	//2.줄단위로 읽을 버퍼리더 스트림을 연결합니다.
				new FileReader(filename)))	//1.먼저 파일스트림을 열고
		{
			String Line;	//가로 한줄을 읽습니다. 또한 노드정보 의미합니다.

			for(int i = 0; (Line = inputStream.readLine()) != null; i++)	//한줄 데이터를 가져옵니다.
			{
				String[] temp = new String[Line.split(" ").length];	//split을 사용하기 위한 임시 저장소 입니다
				temp =  Line.split(" ");		//문서에서 space bar로 단어를 구분 합니다.
				
				for(int j = 0; j < temp.length; j++)	//단어 하나 하나 분석 합니다.
				{
					int k;
					for(k = 0; k < wordTable.size(); k++)	//출현한 단어 Table 검색합니다.
					{
						if(wordTable.get(k).getWord().equals(temp[j]))
						{
							wordTable.get(k).incRate();
							break;	// 단어를 찾으면 빈도를 올리고 그 단어 검색을 종료합니다.
						}
					}
					if(k == wordTable.size())	//검색 실패의 경우 해당 단어를 리스트에 새로 추가 합니다.
					{
						wordTable.add(new Table(temp[j]));
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일 오류!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("입출력 오류!");
			e.printStackTrace();
		}
		
	}
	public void sort()
	{
		this.sort = new MinHeap(wordTable);
	}
	public void BinaryCoding()
	{
		while(!sort.isOne())
		{
			Table left = sort.extractMin();	//최소힙에서 최소빈도수 단어를 2개 추출합니다.
			Table right = sort.extractMin();
			Table parent = new Table();	// 이진 트리에 삽입될 부모를 생성합니다. 부모노드의 빈도수는 자식들의 합입니다.
			parent.setRight(right);
			parent.setLeft(left);	//부모 노드에 두 자식을 연결 합니다.			
			parent.setParentRate(left.getRate()+right.getRate());
			sort.insert(parent);			
		}
		sort.getMin().code = 1;
		traverseInOrder(sort.extractMin());
	}
	private void traverseInOrder(Table node) {	//이진 트리를 탐색 합니다.
		if (node != null) {
			traverseInOrder(node.getLeft(node.code));
	       traverseInOrder(node.getRight(node.code));
	       if(node.getWord() != null)
	       {
	    	   wordTable.add(node);
	       }
		}
	}
	
	public void compress()//본격적으로 압축파일을 생성합니다.
	{
		try(BufferedReader inputStream = new BufferedReader(	//2.줄단위로 읽을 버퍼리더 스트림을 연결합니다.
				new FileReader(filename));
				PrintWriter outputStream = new PrintWriter(
				new FileWriter("compressed"+filename)))	//1.먼저 파일스트림을 열고
		{
			String RdLine;	//가로 한줄을 읽습니다. 또한 노드정보 의미합니다.
				
			for(int i = 0; i < wordTable.size(); i++)
			{	//나중에 압축해제시 파싱할때 규칙입니다.
				outputStream.write(wordTable.get(i).getWord()+"`" );
				outputStream.write(wordTable.get(i).code);
				outputStream.write("&");
			}
			outputStream.write("#");//파일의 맨 앞쪽에 파일의 word,code테이블이 등장합니다.
			
			
			for(int i = 0; (RdLine = inputStream.readLine()) != null; i++)	//한줄 데이터를 가져옵니다.
			{
				String[] temp = new String[RdLine.split(" ").length];	//split을 사용하기 위한 임시 저장소 입니다
				temp =  RdLine.split(" ");		//문서에서 space bar로 단어를 구분 합니다.
				
				for(int j = 0; j < RdLine.split(" ").length; j++)	//단어 하나 하나 분석 합니다.
				{
					int k;
					for(k = 0; k < wordTable.size(); k++)	//출현한 단어 Table 검색합니다.
					{
						if(wordTable.get(k).getWord().equals(temp[j]))
						{
							outputStream.write(wordTable.get(k).code);
							break;
						}
					}
				}
				outputStream.println();
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일 오류!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("입출력 오류!");
			e.printStackTrace();
		}
	}
}