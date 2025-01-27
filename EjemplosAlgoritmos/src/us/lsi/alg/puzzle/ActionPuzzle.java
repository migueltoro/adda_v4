package us.lsi.alg.puzzle;

import us.lsi.common.IntPair;

public enum ActionPuzzle {
	Up,Left,Down,Right;
	
	public static IntPair direction(ActionPuzzle a) {
		return switch(a) {
		case Up -> IntPair.of(-1,0);
		case Down -> IntPair.of(1,0);
		case Left -> IntPair.of(0,-1);
		case Right -> IntPair.of(0,1);
		};
	}
	
}
