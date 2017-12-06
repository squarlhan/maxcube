/*
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */
package cn.edu.jlu.ccst.ga;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jgap.*;
import org.jgap.impl.*;

/**
 * Fitness function for our example. See evaluate(...) method for details.
 *
 * @author Neil Rotstan
 * @author Klaus Meffert
 * @since 2.0
 */
public class ConnectedMaxFunction extends FitnessFunction {
	/** String containing the CVS revision. Read out via reflection! */
	private final static String CVS_REVISION = "$Revision: 1.6 $";

	public static int counts = 0;
	public double[][][] pressure_table;
	public int[] window_cube;

	/**
	 * This example implementation calculates the fitness value of Chromosomes using
	 * BooleanAllele implementations. It simply returns a fitness value equal to the
	 * numeric binary value of the bits. In other words, it optimizes the numeric
	 * value of the genes interpreted as bits. It should be noted that, for clarity,
	 * this function literally returns the binary value of the Chromosome's genes
	 * interpreted as bits. However, it would be better to return the value raised
	 * to a fixed power to exaggerate the difference between the higher values. For
	 * example, the difference between 254 and 255 is only about .04%, which isn't
	 * much incentive for the selector to choose 255 over 254. However, if you
	 * square the values, you then get 64516 and 65025, which is a difference of
	 * 0.8% -- twice as much and, therefore, twice the incentive to select the
	 * higher value.
	 *
	 * @param a_subject
	 *            the Chromosome to be evaluated
	 * @return defect rate of our problem
	 *
	 * @author Neil Rotstan
	 * @author Klaus Meffert
	 * @since 2.0
	 */

	private static void dfs(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited) {
		visit(graph, visited, 'u');
	}

	private static void visit(HashMap<Character, LinkedList<Character>> graph, HashMap<Character, Boolean> visited,
			char start) {
		if (!visited.containsKey(start)) {
			// count++;
			// System.out.println("The time into element "+start+":"+count);
			visited.put(start, true);
			for (char c : graph.get(start)) {
				if (!visited.containsKey(c)) {
					visit(graph, visited, c);
				}
			}
			// count++;
			// System.out.println("The time out element "+start+":"+count);
		}
	}

	public double evaluate(IChromosome a_subject) {
		int x_size = pressure_table.length;
		int y_size = pressure_table[0].length;
		int z_size = pressure_table[0][0].length;
		int window_x = window_cube[0];
		int window_y = window_cube[1];
		int window_z = window_cube[2];

		double total = 0.0;
		int time_delay = 0;
		try {
			Thread.sleep(time_delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int x_0 = ((IntegerGene) a_subject.getGene(0)).intValue();
		int y_0 = ((IntegerGene) a_subject.getGene(1)).intValue();
		int z_0 = ((IntegerGene) a_subject.getGene(2)).intValue();

		x_0 = x_0 + window_x > x_size ? x_size - window_x : x_0;
		y_0 = y_0 + window_y > y_size ? y_size - window_y : y_0;
		z_0 = z_0 + window_z > z_size ? z_size - window_z : z_0;

		// double window_total = 0;
		for (int i = x_0; i <= x_0 + window_x - 1; i++) {
			for (int j = y_0; j <= y_0 + window_y - 1; j++) {
				for (int k = z_0; k <= z_0 + window_z - 1; k++) {

					total += pressure_table[i][j][k];
				}
			}
		}

		counts++;

		return total;
	}

	public ConnectedMaxFunction(double[][][] pressure_table, int[] window_cube) {
		super();
		this.pressure_table = pressure_table;
		this.window_cube = window_cube;
	}
}
