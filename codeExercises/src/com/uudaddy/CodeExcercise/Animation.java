package com.uudaddy.CodeExcercise;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * A collection of particles is contained in a linear chamber. They all have the same speed, but some are headed toward the right and others are headed toward the left. These particles can pass through each other without disturbing the motion of the particles, so all the particles will leave the chamber relatively quickly. 
You will be given the initial conditions by a string 'init' containing at each position an 'L' for a leftward moving particle, an 'R' for a rightward moving particle, or a '.' for an empty location. 'init' shows all the positions in the chamber. Initially, no location in the chamber contains two particles passing through each other. 
We would like an animation of the particles as they move. At each unit of time, we want a string showing occupied locations with an 'X' and unoccupied locations with a '.'. Create a method 'animate' that takes a positive integer 'speed' and a string 'init' giving the initial conditions. The speed is the number of positions each particle moves in one unit of time. The method will return an array of strings in which each successive element shows the occupied locations at each time step. The first element of the return should 
show the occupied locations at the initial instant (at time = 0) in the 'X', '.' format. The last element in the return should show the empty chamber at the first time that it becomes empty. 
Again, imagine that the method you write will be called thousands of times for varying initial conditions with size ranging from 0 to 50, and also imagine the case when init is several hundred thousand locations in size (though with speed > size / 20 or so). Try to handle both of these cases efficiently in your implementation. 
*/

public class Animation {
	ArrayList<String> returnString = new ArrayList<String>();
	HashMap<Integer, String> particlesMap = new HashMap<Integer, String>();

	// convert from the init to the initial positions string
	// "..R...." => "..X...."
	// . => .
	// R or L => X
	//
	// if there is multiple particles in the same position, we only need to show one
	// X for the location
	//
	public String initToParticlesMap(String init) {
		String string1 = new String();

		for (int ii = 0; ii < init.length(); ii++) {
			if (init.substring(ii, ii + 1).equalsIgnoreCase(".")) {
				string1 = string1 + new String(".");
				// check if the value of particle map is empty or not, if not empty, add the .
				// to the end
				addToParticleMap(ii, ".");
			} else if (init.substring(ii, ii + 1).equalsIgnoreCase("R")) {
				string1 = string1 + new String("X");
				addToParticleMap(ii, "R");
			} else if (init.substring(ii, ii + 1).equalsIgnoreCase("L")) {
				string1 = string1 + new String("X");
				addToParticleMap(ii, "L");
			}
		}

		// printParticlesMapContent(particlesMap);
		return string1;
	}

//	private void printParticlesMapContent(HashMap<Integer, String> expectedPath) {
//		for (int i = 0; i < expectedPath.size(); i++) {
//			System.out.println("key= " + i + " ;value= " + expectedPath.get(i));
//		}
//	}

	private void addToParticleMap(int ii, String inputString) {
		if (particlesMap.get(new Integer(ii)) == null)
			particlesMap.put(new Integer(ii), inputString); // new
		else
			particlesMap.put(new Integer(ii), particlesMap.get(new Integer(ii)) + inputString);
	}

	public ArrayList<String> showMeThePath(int speed, String init) {
		// move until all the pieces are out of chamber
		String string1 = initToParticlesMap(init);

		returnString.add(string1);

		if (string1.indexOf("X") == -1) {
			return returnString;
		} else {
			return move(speed, init);
		}
	}

	private ArrayList<String> move(int speed, String init) {
		// "..R...." (init) or "..X...." (string1)
		// strictly speaking, the newPositions need to be handle the collision case, in
		// which
		// two or more particles occupies the same spot, e.g. test case 1
		ArrayList<String> newPositions = new ArrayList<String>(init.length());
		for (int ii = 0; ii < init.length(); ii++) {
			newPositions.add(".");
		}

		//System.out.println("inside move: init: " + init);

		moveLeftOrRight(speed, init, newPositions);

		// convert newPositions into init, aka, ArrayList to simple String
		String newPostionsString = new String();
		//System.out.println("inside move: newPositions.size(): " + newPositions.size());

		for (int i = 0; i < newPositions.size(); i++) {
			System.out.print(newPositions.get(i));
			newPostionsString = newPostionsString + newPositions.get(i);
		}
		System.out.println("");
		//System.out.println("inside move: after loop, newPostionsString: " + newPostionsString);

		return showMeThePath(speed, newPostionsString);
	}

	// see if I can replace with particlesMap
	// this also means particlesMap has to be dynamic, it changes after each move
	// (loop or sweep)
	//
	private void moveLeftOrRight(int speed, String init, ArrayList<String> newPositions) {
		//System.out.println("inside moveLeftOrRight,init.length(): " + init.length());

		for (int ii = 0; ii < init.length(); ii++) {
			if (init.substring(ii, ii + 1).equalsIgnoreCase("R")) {
				// check if the R moves out of chamber, if not, set new position
				if ((ii + speed) < init.length()) {
					if (newPositions.get(ii + speed).isEmpty() == false
							&& newPositions.get(ii + speed).toString() == ".") {
						// newPositions.remove(ii+speed);
						newPositions.set(ii + speed, "R");
					} else {
						newPositions.set(ii + speed, (newPositions.get(ii + speed).toString() + "R"));
					}
				}
			} else if (init.substring(ii, ii + 1).equalsIgnoreCase("L")) {
				// check if the L moves out of chamber, if not, set new position
				if ((ii - speed) >= 0) {
					if (newPositions.get(ii - speed).isEmpty() == false
							&& newPositions.get(ii - speed).toString() == ".") {
						newPositions.set(ii - speed, "L");
					} else {
						newPositions.set(ii - speed, (newPositions.get(ii - speed).toString() + "L"));
					}
				}
			}
		}
		// how to make sure the newPositions.size() does not increase???
		//System.out.println("inside moveLeftOrRight, before return, newPositions.size(): " + newPositions.size());
		//System.out.print("inside moveLeftOrRight, before return, newPositions: ");
		for (int i = 0; i < newPositions.size(); i++) {
			System.out.print(newPositions.get(i));
		}
		System.out.println("");
	}
}
