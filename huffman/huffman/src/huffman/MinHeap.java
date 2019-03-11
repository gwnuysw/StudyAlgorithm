package huffman;
import java.util.ArrayList;

public class MinHeap {

    private ArrayList<Table> list;	//Table을 엮는 리스트입니다.

    public MinHeap() {

        this.list = new ArrayList<Table>();
    }

    public MinHeap(ArrayList<Table> items) {

        this.list = items;
        buildHeap();
    }

    public void insert(Table item) {	//삽입연산 

        list.add(item);
        int i = list.size() - 1;
        int parent = parent(i);

        while (parent != i && list.get(i).getRate() < list.get(parent).getRate()) {	// 빈도수를 비교합니다.

            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }

    private void buildHeap() {

        for (int i = list.size() / 2; i >= 0; i--) {
            minHeapify(i);
        }
    }

    public Table extractMin() {//최소값을 반환하며 제거합니다.

        if (list.size() == 0) {

            throw new IllegalStateException("MinHeap is EMPTY");
        } else if (list.size() == 1) {
            Table min = list.remove(0);
            return min;
        }

        // remove the last item ,and set it as new root
        Table min = list.get(0);
        Table lastItem = list.remove(list.size() - 1);
        list.set(0, lastItem);

        // bubble-down until heap property is maintained
        minHeapify(0);

        // return min key
        return min;
    }

    private void minHeapify(int i) {

        int left = left(i);
        int right = right(i);
        int smallest = -1;

        // find the smallest key between current node and its children.
        if (left <= list.size() - 1 && list.get(left).getRate() < list.get(i).getRate()) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= list.size() - 1 && list.get(right).getRate() < list.get(smallest).getRate()) {
            smallest = right;
        }

        // if the smallest key is not the current key then bubble-down it.
        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    public Table getMin() {

        return list.get(0);
    }
    
    public boolean isOne()
    {
    	return list.size() == 1;
    }
    public boolean isEmpty() {

        return list.size() == 0;
    }

    private int right(int i) {

        return 2 * i + 2;
    }

    private int left(int i) {

        return 2 * i + 1;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int i, int parent) {

        Table temp = list.get(parent);
        list.set(parent, list.get(i));
        list.set(i, temp);
    }

}