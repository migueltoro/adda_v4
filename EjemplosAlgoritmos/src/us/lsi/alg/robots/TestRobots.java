package us.lsi.alg.robots;


import java.util.List;


public class TestRobots {
	
	
	
//1___-1_____________[1, 3, 0, 0],[17, 53, 0, 0],23______[-1]

	public static void main(String[] args) {
		List<Integer> ls = List.of(-1, -1, 1, -1, 1, -1, 1, -1, 1, -1, -1, 2, -1, -1, 2, -1, -1, 3, -1, -1, 2, -1, -1, 3);
		System.out.println(ls.size());
//		Integer[] r = {1, 3, 0, 0};
//		Integer[] x = {17, 53, 0, 0};
		RobotVertex rv = RobotVertex.of();
		System.out.println(rv.mt(1, 0));
		System.out.println(rv);
		System.out.println(rv.actions());
		
		System.out.println(rv.actions());
		System.out.println(rv.t()+"_______"+rv+"-----"+rv.actions()+"-----"+rv.greedyAction());
		for (int i = 0; i < 4; i++) {
			rv = rv.neighbor(rv.greedyAction());
			System.out.println(rv.t()+"_______"+rv+"-----"+rv.actions()+"-----"+rv.greedyAction());
		}
	}
}
