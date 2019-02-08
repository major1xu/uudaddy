package com.uudaddy.CodeExercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.uudaddy.CodeExercise.Animation;

/*
Examples: (Note that in the examples below, the double quotes should not be considered part of the input or output strings.) 
*/

public class AnimationTest {
	
	private void printArrayListContent(ArrayList<String> expectedPath) {
		for (int i = 0; i < expectedPath.size(); i++) {
			System.out.println(expectedPath.get(i));
		}
	}
	@Test
	/*
	 * 0) 2, "..R...." Returns: 
	 * { "..X....", 
	 * "....X..", 
	 * "......X", 
	 * "......." } 
	 * The single particle starts at the 3rd position, moves to the 5th, then 7th, and
	 * then out of the chamber.
	 */
	public void testAnimationCase0() {
		// String []myPath = { };
		ArrayList<String> myPath = new ArrayList<>(Arrays.asList("..X....", 
				"....X..", "......X", "......."));
		ArrayList<String> expectedPath = new ArrayList<String>();
		Animation animation = new Animation();
		System.out.println("testAnimationCase0: ");

		expectedPath = animation.showMeThePath(2, "..R....");
		
		//printArrayListContent(expectedPath);
		assert (myPath.equals(expectedPath));
	}

	/*
	 * 1) 3, "RR..LRL" Returns: { "XX..XXX", ".X.XX..", "X.....X", "......." } Note
	 * that, at the first time step after init, there are actually 4 particles in
	 * the chamber, but two are passing through each other at the 4th position
	 */
	@Test
	public void testAnimationCase1() {
		// String []myPath = { };
		ArrayList<String> myPath = new ArrayList<>(Arrays.asList("XX..XXX", // RR..LRL
				".X.XX..", // .L.OR.. at the 4th place we have both R and L
				"X.....X", // L.....O at this step L and R both moved to the end
				".......") // current defect is L is overwriting R because L comes later
		);
		ArrayList<String> expectedPath = new ArrayList<String>();
		Animation animation = new Animation();
		System.out.println("testAnimationCase1: ");

		expectedPath = animation.showMeThePath(3, "RR..LRL");

		printArrayListContent(expectedPath);
		assert(myPath.equals(expectedPath));
	}

	/*
	 * 2) 2, "LRLR.LRLR" Returns: { "XXXX.XXXX", "X..X.X..X", ".X.X.X.X.",
	 * ".X.....X.", "........." } At time 0 (init) there are 8 particles. At time 1,
	 * there are still 6 particles, but only 4 positions are occupied since
	 * particles are passing through each other.
	 */
	@Test
	public void testAnimationCase2() {
		// String []myPath = { };
		ArrayList<String> myPath = new ArrayList<>(
				Arrays.asList("XXXX.XXXX", "X..X.X..X", ".X.X.X.X.", ".X.....X.", ".........") // current defect is L is
																								// overwriting R because
																								// L comes later
		);
		ArrayList<String> expectedPath = new ArrayList<String>();
		Animation animation = new Animation();
		System.out.println("testAnimationCase2: ");

		expectedPath = animation.showMeThePath(2, "LRLR.LRLR");

		printArrayListContent(expectedPath);
		 assert(myPath.equals(expectedPath));
	}

	/*
	 * 3) 10, "RLRLRLRLRL" Returns: { "XXXXXXXXXX, ".........." } These particles
	 * are moving so fast that they all exit the chamber by time 1.
	 * 
	 */
	@Test
	public void testAnimationCase3() {
		ArrayList<String> myPath = new ArrayList<>(Arrays.asList("XXXXXXXXXX", ".........."));
		ArrayList<String> expectedPath = new ArrayList<String>();
		Animation animation = new Animation();
		System.out.println("testAnimationCase3: ");

		expectedPath = animation.showMeThePath(10, "RLRLRLRLRL");

		//printArrayListContent(expectedPath);
		assert (myPath.get(0).equals(expectedPath.get(0)));
		assert (myPath.get(1).equals(expectedPath.get(1)));

	}
	/*
	 * 
	 * 4) 1, "..." Returns: { "..." }
	 */

	@Test
	public void testAnimationCase4() {
		String myPath = "...";
		ArrayList<String> expectedPath = new ArrayList<String>();
		Animation animation = new Animation();
		System.out.println("testAnimationCase4: ");

		expectedPath = animation.showMeThePath(1, "...");

		assert (myPath.equals(expectedPath.get(0)));
	}
	/*
	 * 5) 1, "LRRL.LR.LRR.R.LRRL." Returns: { "XXXX.XX.XXX.X.XXXX.",
	 * "..XXX..X..XX.X..XX.", ".X.XX.X.X..XX.XX.XX", "X.X.XX...X.XXXXX..X",
	 * ".X..XXX...X..XX.X..", "X..X..XX.X.XX.XX.X.", "..X....XX..XX..XX.X",
	 * ".X.....XXXX..X..XX.", "X.....X..XX...X..XX", ".....X..X.XX...X..X",
	 * "....X..X...XX...X..", "...X..X.....XX...X.", "..X..X.......XX...X",
	 * ".X..X.........XX...", "X..X...........XX..", "..X.............XX.",
	 * ".X...............XX", "X.................X", "..................." }
	 */

	@Test
	public void testAnimationCase5() {
		// String []myPath = { };
		ArrayList<String> myPath = new ArrayList<>(
				Arrays.asList("XXXX.XX.XXX.X.XXXX.", "..XXX..X..XX.X..XX.", ".X.XX.X.X..XX.XX.XX",
						"X.X.XX...X.XXXXX..X", ".X..XXX...X..XX.X..", "X..X..XX.X.XX.XX.X.", "..X....XX..XX..XX.X",
						".X.....XXXX..X..XX.", "X.....X..XX...X..XX", ".....X..X.XX...X..X", "....X..X...XX...X..",
						"...X..X.....XX...X.", "..X..X.......XX...X", ".X..X.........XX...", "X..X...........XX..",
						"..X.............XX.", ".X...............XX", "X.................X", "..................."));
		ArrayList<String> expectedPath = new ArrayList<String>();
		Animation animation = new Animation();
		System.out.println("testAnimationCase5: ");

		expectedPath = animation.showMeThePath(1, "LRRL.LR.LRR.R.LRRL.");

		//printArrayListContent(expectedPath);
		 assert(myPath.equals(expectedPath));
	}
}
