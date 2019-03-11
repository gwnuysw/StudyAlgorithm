package huffmanDecode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
//PrintWriter outputStream = new PrintWriter(new FileWriter("extracted"));
//outputStream.write(buffer);
public class extract {
	private ArrayList<wordPare> wordTable = new ArrayList<wordPare>();
	public extract(String fn)
	{
		int singleChar;
		String word;
		char pre = 0;
		char[] tobecomeString = new char[100];
		int i = 0;
		char labelDiv = 0;
		int code = 0;
		String[] sigend = new String[2];
		String outString;
		try (FileReader inputStream = new FileReader("compressed"+fn);
				PrintWriter out = new PrintWriter(new FileWriter("extracted"))) 
		{
			while ((singleChar = inputStream.read()) != '#') 
			{
				pre = (char) singleChar;
				if(pre == '`')//현재 파싱 상태를 확인한다.
				{
					labelDiv = '`';
				}
				else if(pre == '&')//단어, 코드를 모두 읽었으면 테이블에 저장해라!
				{
					tobecomeString[i] = '\0';
					word = new String(tobecomeString);
					sigend = word.split("\0");
					wordTable.add(new wordPare(sigend[0], code));
					i = 0;
					labelDiv = 0;
					continue;
				}
				else if(labelDiv == 0)	//단어 부분을 읽어라!
				{
					tobecomeString[i++] = (char)singleChar;
				}
				else if(labelDiv == '`')	//코드 부분을 읽어라!
				{
					code = singleChar;
				}
			}
			while((singleChar = inputStream.read()) != -1)
			{
				if(singleChar == '\n')
				{
					out.println();
				}
				else
				{
					outString = find(singleChar);
					System.out.println(outString);
					out.print(outString);
					out.print(" ");
				}
			}
		}
		catch (IOException ex) {
		        ex.printStackTrace();
		}
		for(int j = 0; i < wordTable.size(); i++)
		{
			System.out.println(wordTable.get(i).word+" : "+wordTable.get(i).code);
		}
	}
	private String find(int code)
	{
		for(int i = 0; i < wordTable.size();i++)
		{
			if(wordTable.get(i).code == code)
			{
				return wordTable.get(i).word;
			}
		}
		return "	";
	}
}
