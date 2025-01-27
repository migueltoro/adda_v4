package us.lsi.alg.puzzle;

import us.lsi.common.IntPair;

public class TestVertex {

	public static void main(String[] args) {
		VertexPuzzle v1 = VertexPuzzle.of(1, 2, 3, 4, 5, 0, 6, 7, 8);
		VertexPuzzle end = VertexPuzzle.of(1,2,3,4,0,5,6,7,8);
		System.out.printf("_________\n%s\n%s\n",v1,v1.blackPosition());
//		System.out.printf("_________\n%s\n%s\n",end,end.blackPosition());
		System.out.printf("_________\n%.0f\n",HeuristicaPuzzle.heuristicaManhattan(v1, null, end));
		System.out.printf("_________\n%s\n",v1.actions());
		VertexPuzzle v2 = v1.neighbor(ActionPuzzle.valueOf("Right"));
		System.out.printf("_________\n%s\n",v2);
		System.out.printf("_________\n%s\n",v2.actions());
		System.out.printf("_________\n%.0f\n",HeuristicaPuzzle.heuristicaManhattan(v2, null, end));
		VertexPuzzle v3 = v2.neighbor(ActionPuzzle.valueOf("Left"));
		System.out.printf("_________\n%s\n",v3);
		System.out.printf("_________\n%.0f\n",HeuristicaPuzzle.heuristicaManhattan(v3, null, end));
		System.out.printf("_________\n%s\n",end);
		System.out.printf("_________\n%.0f\n",HeuristicaPuzzle.heuristicaManhattan(end, null, end));
		VertexPuzzle goal = VertexPuzzle.of(1,2,3,4,5,6,7,8,0); //soluble con respecto a 
		VertexPuzzle v4 = VertexPuzzle.of(1,8,2,0,4,3,7,6,5); //soluble con respecto a goal
		VertexPuzzle v5 = VertexPuzzle.of(8,1,2,0,4,3,7,6,5); // no soluble con respecto a 
		VertexPuzzle v6 = VertexPuzzle.of(1,2,3,4,0,5,8,6,7); // no soluble con respecto a 
		Integer[][] puzzle = {{1, 8, 2},{0, 4, 3},{7, 6, 5}};
		VertexPuzzleI v42 = VertexPuzzleI.of(puzzle,IntPair.of(1, 0));
		System.out.println("______________");
		System.out.println(goal.getInvCount());
		System.out.println(v4.getInvCount());
		System.out.println(v42.getInvCount());
		System.out.println(v5.getInvCount());
		System.out.println(v6.getInvCount());
	}

}
