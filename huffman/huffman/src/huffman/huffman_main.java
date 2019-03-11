package huffman;

public class huffman_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		huffman compress = new huffman("java_scanner");
		compress.sort();
		compress.BinaryCoding();
		compress.compress();
	}

}
