package se.kth.hugosa.schedule.genetic;

import org.jgap.*;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.DefaultCrossoverRateCalculator;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.MutationOperator;

import se.kth.hugosa.schedule.*;

import java.util.ArrayList;

public class GeneticSchedule {
	private Configuration conf;
	private FitnessFunction func;
	private Genotype population;
	private Constraints constraints;
	private int time;
    private long startTime;

	public GeneticSchedule (Constraints constraints, int popSize, double mRate, boolean mDynamic, double cRate, boolean cDynamic, int time) throws InvalidConfigurationException {
		this.constraints = constraints;
		int numSlots = constraints.getNumSlots();
		int numElements = constraints.getNumElements();
		
		conf = new DefaultConfiguration();
		conf.resetProperty(Configuration.PROPERTY_FITEVAL_INST);
		conf.setFitnessEvaluator(new DeltaFitnessEvaluator());
		
		conf.getGeneticOperators().clear();
		//CrossoverOperator crossover = new CrossoverOperator(conf, new DefaultCrossoverRateCalculator(conf));
		
		
		// Setting up mutationrate
		int mutationRate;
		if (mDynamic) {
			mutationRate = (int) (mRate * ((double) numElements));
		} else {
			mutationRate = (int) mRate;
		}
		if (mutationRate < 1){
			mutationRate = 1;
		}
		MutationOperator mutator = new MutationOperator(conf, mutationRate);
	    conf.addGeneticOperator(mutator);
		
		// Setting up crossoverrate
		double crossRate;
		if (cDynamic) {
			crossRate = (1.0) / (cRate * ((double) numElements));
		} else {
			crossRate = cRate;
		}
		if(crossRate > 1.0){
			crossRate = 1.0;
		}
		if(crossRate > 0.0){
			CrossoverOperator crossover = new CrossoverOperator(conf, crossRate);
			conf.addGeneticOperator(crossover);
		}
		
		this.time = time;
        this.startTime = System.nanoTime();

		func = new ScheduleFitnessFunction(constraints);
		conf.setFitnessFunction(func);
		
		Gene[] sampleGenes = new Gene[numElements];

		for (int i = 0; i<sampleGenes.length; i++){
			sampleGenes[i] = new IntegerGene(conf, 0, numSlots-1);
		}
		
		Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		
		conf.setSampleChromosome(sampleChromosome);
		conf.setPopulationSize(popSize);
		
		population = Genotype.randomInitialGenotype(conf);
	}
	
	public ArrayList<Schedule> evolve(int maxEvolutions, Mode mode){
		IChromosome bestSolution = null;
		boolean optimalFound = false;
		int evolutions = 0;
		while(!optimalFound && evolutions < maxEvolutions){
			population.evolve();
			bestSolution = population.getFittestChromosome();
            double fitnessValue = func.getFitnessValue(bestSolution);
            if(mode == Mode.PRINT_ITERS){
            	System.out.print("" + fitnessValue + " ");
            }
            if (time != -1) {
                if ((System.nanoTime() - startTime)/1000000 > time) {
                	if (mode == Mode.PRINT_ITERS) {
                		System.out.println("];");
                	}
                    if (mode == Mode.VERBOSE) {
                        System.out.println("Time is up (" + time + " ms, " + evolutions + " evolutions)");
                    }
                    optimalFound = true;
                }
            }
            if(fitnessValue <= 0){
				optimalFound = true;
				if (mode == Mode.PRINT_ITERS) {
					System.out.println("];");
				}
                if (mode == Mode.VERBOSE) {
				    System.out.println("Perfect solution found in " + evolutions + " evolutions");
                }
            }
            evolutions++;
		}
		return generateSchedule(bestSolution, constraints);
	}
	
	public static ArrayList<Schedule> generateSchedule(IChromosome c, Constraints constraints){
		
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		for(int i = 0; i < constraints.getNumPrograms(); i++){
			result.add(new Schedule(constraints.getPrograms().get(i), constraints.getScheduleWeeks()));
		}
		
		for (int i = 0; i < c.getGenes().length; i++) {
			IntegerGene gene = (IntegerGene) c.getGene(i);
			Position pos = new Position(gene.intValue(), constraints);
			
			ScheduleElement element = constraints.getScheduleElements().get(i);
			
			Schedule schedule = result.get(constraints.getPrograms().indexOf(element.getProgram()));
			
			Day day = schedule.days.get(pos.days);
			if(day == null){
				day = new Day();
			}
			
			TimeSlot slot = day.timeSlots.get(pos.slots);
			slot.elementsMap.put(constraints.getClassrooms().get(pos.rooms), element);
			schedule.days.set(pos.days, day);
			
		}
		
		return result;
	}
}


