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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.jgap.*;
import org.jgap.impl.*;

/**
 * Simple class that demonstrates the basic usage of JGAP.
 * 
 * @author Neil Rotstan
 * @author Klaus Meffert
 * @since 2.0
 */
public class FindMaxCube {
	/** String containing the CVS revision. Read out via reflection! */
	private static final String CVS_REVISION = "$Revision: 1.9 $";
	
	public double[][][] readpressure(int i, int j, int k, String addr){
		double[][][] result = new double[i][j][k];
		
		List<Double> datalist = new ArrayList<Double>();

		try {
			InputStreamReader ir = new InputStreamReader(new FileInputStream(addr));
			BufferedReader reader = new BufferedReader(ir);
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				String[] lines = line.trim().split(" ");				
				for(String l: lines) {
					l = l.trim();
					if(l.length()>1) {
						datalist.add(Double.parseDouble(l));
//						if(Double.parseDouble(l)>0) {
//							System.out.println(Double.parseDouble(l));
//						}
					}
					
				}
			}
			ir.close();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size = datalist.size();
		System.out.println(size);
//		int d = 0;
		for(int s=0;s<=size-1;s++) {
			int c = s/(i*j);
			int b = (s - c*i*j)/i;
			int a = s - c*i*j - b*i;
			result[a][b][c] = datalist.get(s);
//			if(result[a][b][c]>0) {
//				d++;
//	          	System.out.println(result[a][b][c]);
//	        }
			
		}
//		System.out.println(d);
		return result;
	}

	public IChromosome runga(int ng, int chromeSize, int popsize, int[] left, int[] right, FitnessFunction fitnessfun) {
		long startTime = System.currentTimeMillis();
		int numEvolutions = ng;
		Configuration gaConf = new DefaultConfiguration();
		gaConf.reset();
		gaConf.setPreservFittestIndividual(true);
		gaConf.setKeepPopulationSizeConstant(false);
		Genotype genotype = null;

		try {
			// 构建基因(Gene)
			Gene[] sampleGenes = new Gene[chromeSize];// 基因长度2
			for (int i = 0; i < sampleGenes.length; i++) {
				sampleGenes[i] = new IntegerGene(gaConf, left[i], right[i]);
			}
			// 构建染色体(Chromosome)
			IChromosome sampleChromosome = new Chromosome(gaConf, sampleGenes);
			gaConf.setSampleChromosome(sampleChromosome);
			gaConf.setPopulationSize(popsize);
			gaConf.setFitnessFunction(fitnessfun);
			genotype = Genotype.randomInitialGenotype(gaConf);
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
			System.exit(-2);
		}
		int progress = 0;
		int percentEvolution = numEvolutions / 10;
		for (int i = 0; i < numEvolutions; i++) {
			genotype.evolve();
			// Print progress.
			// ---------------
			progress++;
			IChromosome fittest = genotype.getFittestChromosome();

			if (percentEvolution > 0 && i % percentEvolution == 0) {
				double fitness = fittest.getFitnessValue();
				System.out.println("Currently fittest Chromosome has fitness " + fitness);
			}
		}
		// Print summary.
		// --------------
		IChromosome fittest = genotype.getFittestChromosome();
		System.out.println("Fittest Chromosome has fitness " + (fittest.getFitnessValue()));

//		DecimalFormat myformat = new DecimalFormat("#0.00");
//		for (int i = 0; i < chromeSize; i++) {
//
//			// System.out.println(myformat.format(((DoubleGene)fittest.getGene(i)).doubleValue()));
//			System.out.print(myformat.format(fittest.getGene(i).getAllele()) + "	");
//		}

//		System.out.println();
		long endTime = System.currentTimeMillis();
		System.out.println("Running time: " + (endTime - startTime) + "ms");
		System.out.println("sum counts:  " + MaxFunction.counts);
		
		return fittest;
	}
	
	

	/**
	 * Starts the example.
	 * 
	 * @param args
	 *            if optional first argument provided, it represents the number
	 *            of bits to use, but no more than 32
	 * 
	 * @author Neil Rotstan
	 * @author Klaus Meffert
	 * @throws IOException
	 * @since 2.0
	 */
	public static void main(String[] args) throws IOException {

		FindMaxCube se = new FindMaxCube();

		int pop_size = 50;
		int max_ite = 5000;
		int chrom_size = 3;
		int i = 121;
		int j = 255;
		int k = 13;
		int[] window = {3,5,2};
		int[] left = {0,0,0};
		int[] right = {i-window[0], j-window[1], k-window[2]};
		String addr = "/Users/user/Desktop/daqing/121-255-13-dznet.dat";
		
		double[][][] pt = se.readpressure(i, j, k, addr);
		for (int a = 0; a <= 0; a++) {
			IChromosome fittest = se.runga(max_ite, chrom_size, pop_size, left, right, new ConnectedMaxFunction(pt, window));
			
			System.out.print((int)(fittest.getGene(0).getAllele()) + "	");
			System.out.print((int)(fittest.getGene(1).getAllele()) + "	");
			System.out.println((int)(fittest.getGene(2).getAllele()) + "	");
			
			System.out.print((int)(fittest.getGene(0).getAllele())+window[0]-1 + "	");
			System.out.print((int)(fittest.getGene(1).getAllele()) + "	");
			System.out.println((int)(fittest.getGene(2).getAllele()) + "	");
			
			System.out.print((int)(fittest.getGene(0).getAllele()) + "	");
			System.out.print((int)(fittest.getGene(1).getAllele())+window[1]-1 + "	");
			System.out.println((int)(fittest.getGene(2).getAllele()) + "	");
			
			System.out.print((int)(fittest.getGene(0).getAllele())+window[0]-1 + "	");
			System.out.print((int)(fittest.getGene(1).getAllele())+window[1]-1 + "	");
			System.out.println((int)(fittest.getGene(2).getAllele()) + "	");
			
			System.out.print((int)(fittest.getGene(0).getAllele()) + "	");
			System.out.print((int)(fittest.getGene(1).getAllele()) + "	");
			System.out.println((int)(fittest.getGene(2).getAllele())+window[2]-1 + "	");
			
			System.out.print((int)(fittest.getGene(0).getAllele())+window[0]-1 + "	");
			System.out.print((int)(fittest.getGene(1).getAllele()) + "	");
			System.out.println((int)(fittest.getGene(2).getAllele())+window[2]-1 + "	");
			
			System.out.print((int)(fittest.getGene(0).getAllele()) + "	");
			System.out.print((int)(fittest.getGene(1).getAllele())+window[1]-1 + "	");
			System.out.println((int)(fittest.getGene(2).getAllele())+window[2] -1+ "	");
			
			System.out.print((int)(fittest.getGene(0).getAllele())+window[0]-1 + "	");
			System.out.print((int)(fittest.getGene(1).getAllele())+window[1]-1 + "	");
			System.out.println((int)(fittest.getGene(2).getAllele())+window[2]-1 + "	");
		}

	}
}
