package se.kth.hugosa.schedule;

import org.jgap.Chromosome;
import org.jgap.IChromosome;
import org.jgap.Configuration;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.SwappingMutationOperator;
import org.jgap.FitnessFunction;
import java.util.ArrayList;
import org.jgap.Gene;
import org.jgap.impl.IntegerGene;
import org.jgap.Genotype;
import org.jgap.InvalidConfigurationException;

public class GeneticSchedule {
	Configuration conf;
	FitnessFunction func;
	Genotype population;
	
	public GeneticSchedule (Constraints constraints, int popSize) throws InvalidConfigurationException {
		conf = new DefaultConfiguration();
		conf.getGeneticOperators().clear();
		SwappingMutationOperator swapper = new SwappingMutationOperator(conf);
		conf.addGeneticOperator(swapper);
		
		func = new ScheduleFitnessFunction(constraints);
		
		int numSlots = constraints.getNumSlots();
		int numElements = constraints.getNumElements();
		
		Gene[] sampleGenes = new Gene[numSlots];
		
		for (int i = 0; i<sampleGenes.length; i++){
			sampleGenes[i] = new IntegerGene(conf, 0, numElements);
		}
		
		Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		
		conf.setSampleChromosome(sampleChromosome);
		conf.setPopulationSize(popSize);
		
		population = Genotype.randomInitialGenotype(conf);
	}
	
	public ArrayList<Schedule> evolve(int maxEvolutions){
		IChromosome bestSolution;
		for(int i = 0; i < maxEvolutions; i++){
			population.evolve();
		}
		bestSolution = population.getFittestChromosome();
		return generateSchedule(bestSolution);
	}
	
	public static ArrayList<Schedule> generateSchedule(IChromosome c){
		//TODO: Create a Schedule-object from IChromosome c and return it
		return null;
	}
	
}

