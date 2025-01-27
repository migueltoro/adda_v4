package us.lsi.alg.puzzle;

public class Test {

	public static void main(String[] args) {
		VertexPuzzle v2 = VertexPuzzle.of(1, 2, 3, 4, 5, 0, 6, 7, 8);
		System.out.printf("_________\n%s\n",v2);
		VertexPuzzle v3 = v2.neighbor(ActionPuzzle.valueOf("Left"));
		System.out.printf("_________\n%s\n",v3);
		VertexPuzzle end = VertexPuzzleI.of(1,2,3,4,0,5,6,7,8);
		System.out.printf("_________\n%s\n",end);
		System.out.printf("_________\n%s\n",v2);
		System.out.printf("%s\n",end.equals(v2));
//		System.out.printf("_________\n1: %s\n",ActionPuzzle.of("Left"));
//		System.out.printf("_________\n%s\n",v2);
//		v2 = v2.neighbor(ActionPuzzle.of("Left"));
//		System.out.printf("_________\n2: %s\n",ActionPuzzle.of("Left"));
//		System.out.printf("_________\n%s\n",v2);
//		v2 = v2.neighbor(ActionPuzzle.of("Up"));
//		System.out.printf("_________\n3: %s\n",ActionPuzzle.of("Up"));
//		System.out.printf("_________\n%s\n",v2);
//		v2 = v2.neighbor(ActionPuzzle.of("Right"));
//		System.out.printf("_________\n4: %s\n",ActionPuzzle.of("Right"));
//		System.out.printf("_________\n%s\n",v2);
//		
	}

}
