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
//	public HashMap<Integer, LinkedList<Integer>> non_null_point;
	public HashMap<Integer, Double> non_null_point;
	public HashMap<Integer, LinkedList<Integer>> graph;

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
    private void buildGraph(IChromosome a_subject){
    	    
    	    
    	    int x_size = pressure_table.length;
    		int y_size = pressure_table[0].length;
    		int z_size = pressure_table[0][0].length;
    		int window_x = window_cube[0];
    		int window_y = window_cube[1];
    		int window_z = window_cube[2];
    		
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
    					if(pressure_table[i][j][k]>0) {
    						int index = (i-x_0)*window_y*window_z+(j-y_0)*window_z+(k-z_0);
    						LinkedList<Integer> temp = new LinkedList<Integer>();
    						this.non_null_point.put(index, pressure_table[i][j][k]);
    						
    						int x[] = new int[26];
    						int y[] = new int[26];
    						int z[] = new int[26];
    						
    						x[0] = i-1;
    						x[1] = i;
    						x[2] = i+1;
    						x[3] = i-1;
    						x[4] = i;
    						x[5] = i+1;
    						x[6] = i-1;
    						x[7] = i;
    						x[8] = i+1;
    						x[9] = i-1;
    						x[10] = i;
    						x[11] = i+1;
    						x[12] = i-1;
    						x[13] = i+1;
    						x[14] = i-1;
    						x[15] = i;
    						x[16] = i+1;
    						x[17] = i-1;
    						x[18] = i;
    						x[19] = i+1;
    						x[20] = i-1;
    						x[21] = i;
    						x[22] = i+1;
    						x[23] = i-1;
    						x[24] = i;
    						x[25] = i+1;
    						
    						y[0] = j-1;
    						y[1] = j-1;
    						y[2] = j-1;
    						y[3] = j;
    						y[4] = j;
    						y[5] = j;
    						y[6] = j+1;
    						y[7] = j+1;
    						y[8] = j+1;
    						y[9] = j-1;
    						y[10] = j-1;
    						y[11] = j-1;
    						y[12] = j;
    						y[13] = j;
    						y[14] = j+1;
    						y[15] = j+1;
    						y[16] = j+1;
    						y[17] = j-1;
    						y[18] = j-1;
    						y[19] = j-1;
    						y[20] = j;
    						y[21] = j;
    						y[22] = j;
    						y[23] = j+1;
    						y[24] = j+1;
    						y[25] = j+1;
    						
    						z[0] = k-1;
    						z[1] = k-1;
    						z[2] = k-1;
    						z[3] = k-1;
    						z[4] = k-1;
    						z[5] = k-1;
    						z[6] = k-1;
    						z[7] = k-1;
    						z[8] = k-1;
    						z[9] = k;
    						z[10] = k;
    						z[11] = k;
    						z[12] = k;
    						z[13] = k;
    						z[14] = k;
    						z[15] = k;
    						z[16] = k;
    						z[17] = k+1;
    						z[18] = k+1;
    						z[19] = k+1;
    						z[20] = k+1;
    						z[21] = k+1;
    						z[22] = k+1;
    						z[23] = k+1;
    						z[24] = k+1;
    						z[25] = k+1;
    						
    						for(int a = 0;a<=25;a++) {
    							x[a] = x[a] > x_0+window_x-1 ? x[a]-1 : x[a];
    							y[a] = y[a] > y_0+window_y-1 ? y[a]-1 : y[a];
    							z[a] = z[a] > z_0+window_z-1 ? z[a]-1 : z[a];

    							x[a] = x[a] < x_0 ? x[a]+1 : x[a];
    							y[a] = y[a] < y_0 ? y[a]+1 : y[a];
    							z[a] = z[a] < z_0 ? z[a]+1 : z[a];
    							
    							if(pressure_table[x[a]][y[a]][z[a]]>0) {
    								Integer index_temp = (x[a]-x_0)*window_y*window_z+(y[a]-y_0)*window_z+(z[a]-z_0);
    								if(!temp.contains(index_temp)) {
    									temp.add(index_temp);
    								}
    							}
    						}
    						
    						if(temp.size()>0) {
    							graph.put(index, temp);
    						}
    					
    					};
    				}
    			}
    		}
//    		System.out.println("done!");
//    	    return graph;
    }
	
	private HashMap<Integer, Boolean> dfs() {
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		int s = non_null_point.size();
		int r = (int) (Math.random()*s);
		int k = (int) non_null_point.keySet().toArray()[r];
		
		visit(visited, k);
		return visited;
	}

	private void visit(HashMap<Integer, Boolean> visited,
			int start) {
		if (!visited.containsKey(start)) {
			// count++;
			// System.out.println("The time into element "+start+":"+count);
			visited.put(start, true);
			for (int c : graph.get(start)) {
				if (!visited.containsKey(c)) {
					visit(visited, c);
				}
			}
			// count++;
			// System.out.println("The time out element "+start+":"+count);
		}
	}

	public double evaluate(IChromosome a_subject) {

		double total = 0.0;
		int time_delay = 0;
		try {
			Thread.sleep(time_delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.buildGraph(a_subject);
		if(non_null_point.size()==0)return 0;
		HashMap<Integer, Boolean> visited = this.dfs();
		
		for(int key:visited.keySet()) {
			total+=non_null_point.get(key);
		}

		counts++;

		return total;
	}

	public ConnectedMaxFunction(double[][][] pressure_table, int[] window_cube) {
		super();
		this.pressure_table = pressure_table;
		this.window_cube = window_cube;
		non_null_point = new HashMap<Integer, Double>();
		graph = new HashMap<Integer, LinkedList<Integer>>();
	}
}
