package huffman;

public class Table {//빈도수 테이블 자료구조 입니다. 최소 힙을 사용할때 연결리스트 형태를 사용하며, 이진 트리 코딩시 트리구조를 이룹니다.
	private int rate;
	private String word;
	private Table left, right;
    int code = 0x0000;
	public Table( String word)
	{
		this.rate = 1;
		this.word = word;
       left = right = null;
	}
	
	public Table()
	{
		this.rate = 0;
		this.word = null;
		left = right = null;
	}
	public void setParentRate(int rate)
	{
		this.rate = rate;
	}
	public String getWord()
	{
		return this.word;
	}
	public int getRate()
	{
		return this.rate;
	}
	public void incRate()
	{
		this.rate++;
	}
	public void setLeft(Table left)
	{
		this.left = left;
	}
	public void setRight(Table Right)
	{
		this.right = Right;
	}
	public Table getLeft(int c)
	{
		if(left != null)
		{
			left.code = c << 1;
			left.code++;
		}
		return this.left;
	}
	public Table getRight(int c)
	{
		if(right != null)
		{
			right.code = c <<1;
		}
		return this.right;
	}

}
