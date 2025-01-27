package us.lsi.graphs.manual.heaps;

import java.util.List;

public class TestFibonacci {

	public static void main(String[] args) {
		List<Integer> ls = List.of(2,32,-4156,557,3,5,7,8,9,10,-11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29);
		HeapInfo.heapType = HeapInfo.HeapType.Fibonacci;
		Heap<Integer,Integer> f = Heap.of();
		f.addAll(ls, ls);
		f.decrease(557, -5);
		List<Integer> r = f.removeAll();
		System.out.println(r);
		f.clear();
		HeapInfo.heapType = HeapInfo.HeapType.BinaryHeap;
		f = Heap.of();
		f.addAll(ls, ls);
		f.decrease(557, -5);
		r = f.removeAll();
		System.out.println(r);
		f.clear();
		HeapInfo.heapType = HeapInfo.HeapType.OrderedList;
		f = Heap.of();
		f.addAll(ls, ls);
		f.decrease(557, -5);
		r = f.removeAll();
		System.out.println(r);
	}

}
