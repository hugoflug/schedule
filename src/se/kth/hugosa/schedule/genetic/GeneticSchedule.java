package se.kth.hugosa.schedule.genetic;

import org.jgap.Chromosome;
import org.jgap.DeltaFitnessEvaluator;
import org.jgap.IChromosome;
import org.jgap.Configuration;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.SwappingMutationOperator;
import org.jgap.FitnessFunction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgap.Gene;
import org.jgap.impl.IntegerGene;
import org.jgap.Genotype;
import org.jgap.InvalidConfigurationException;
import se.kth.hugosa.schedule.*;

public class GeneticSchedule {
	Configuration conf;
	FitnessFunction func;
	Genotype population;
	Constraints constraints;
	
	public GeneticSchedule (Constraints constraints, int popSize) throws InvalidConfigurationException {
		conf = new DefaultConfiguration();
		conf.resetProperty(Configuration.PROPERTY_FITEVAL_INST);
		conf.setFitnessEvaluator(new DeltaFitnessEvaluator());
		
		conf.getGeneticOperators().clear();
		SwappingMutationOperator swapper = new SwappingMutationOperator(conf);
		conf.addGeneticOperator(swapper);
		
		this.constraints = constraints;
		
		func = new ScheduleFitnessFunction(constraints);
		conf.setFitnessFunction(func);
		
		int numSlots = constraints.getNumSlots();
		int numElements = constraints.getNumElements();
		
		Gene[] sampleGenes = new Gene[numSlots];
		
		for (int i = 0; i<sampleGenes.length; i++){
			sampleGenes[i] = new IntegerGene(conf, -1, numElements-1);
		}
		
		Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		
		conf.setSampleChromosome(sampleChromosome);
		conf.setPopulationSize(popSize);
		
		population = Genotype.randomInitialGenotype(conf);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int i = 0; i<numSlots; i++){
			if(i < numElements){
				numbers.add(i);
			}
			else{
				numbers.add(-1);
			}
		}
		
		List<IChromosome> chromosomes = population.getPopulation().getChromosomes();
		for (IChromosome chromosome : chromosomes) {
			Collections.shuffle(numbers);
			for (int i = 0; i < chromosome.size(); i++){
				Gene gene = chromosome.getGene(i);
				gene.setAllele(numbers.get(i));
			}
		}
		/*List<IChromosome> chromosomes2 = population.getPopulation().getChromosomes();
		for (IChromosome chromosome : chromosomes2) {
			Schedule.printSchedule(generateSchedule(chromosome, constraints));
		}
		*/
		
	}
	
	public ArrayList<Schedule> evolve(int maxEvolutions){
		IChromosome bestSolution;
		for(int i = 0; i < maxEvolutions; i++){
			bestSolution = population.getFittestChromosome();
			System.out.println("Best fitness value: " + func.getFitnessValue(bestSolution));
			//Schedule.printSchedule(generateSchedule(bestSolution, constraints));
			population.evolve();
		}
		bestSolution = population.getFittestChromosome();
		return generateSchedule(bestSolution, constraints);
	}
	
	public static ArrayList<Schedule> generateSchedule(IChromosome c, Constraints constraints){
		/*
		for(Gene g : c.getGenes()){
			System.out.println(""+ (Integer) g.getAllele());
		}
		System.out.println("-----------------");
		*/
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		for(int i = 0; i < constraints.getNumPrograms(); i++){
			result.add(new Schedule(constraints.getPrograms().get(i), constraints.getScheduleWeeks()));
		}
		
		for(int days = 0; days < constraints.getScheduleWeeks()*5; days++){
			for(int slots = 0; slots < 4; slots++){
				for(int rooms = 0; rooms < constraints.getNumClassrooms(); rooms++){
					int geneIndex = (days*constraints.getNumClassrooms()*4 + slots*constraints.getNumClassrooms() + rooms);
					int index = (Integer) c.getGene(geneIndex).getAllele();
					//System.out.println("Working on gene #" + geneIndex + ", index = " + index);
					if(index > -1){
						//System.out.println("Inserting element #" + index + ":");
						ScheduleElement element = constraints.getScheduleElements().get(index);
						
						Schedule schedule = result.get(constraints.getPrograms().indexOf(element.getProgram()));
						
						//System.out.println(element.getProgram() + " should match " + schedule.getProgram() + ".");
						
						Day day = schedule.days.get(days);
						if(day == null){
							day = new Day();
						}
						
						day.timeSlots.set(slots, new TimeSlot(constraints.getClassrooms().get(rooms), element));
						schedule.days.set(days, day);
					}
				}
			}
		}
		return result;
	}
	
}

