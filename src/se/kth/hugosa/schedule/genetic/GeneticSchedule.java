package se.kth.hugosa.schedule.genetic;

import org.jgap.Chromosome;
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
		for (int i = 0; i<sampleGenes.length; i++){
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
		
	}
	
	public ArrayList<Schedule> evolve(int maxEvolutions){
		IChromosome bestSolution;
		for(int i = 0; i < maxEvolutions; i++){
			population.evolve();
		}
		bestSolution = population.getFittestChromosome();
		return generateSchedule(bestSolution, constraints);
	}
	
	public static ArrayList<Schedule> generateSchedule(IChromosome c, Constraints constraints){
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		for(int i = 0; i < constraints.getNumPrograms(); i++){
			result.add(new Schedule(constraints.getPrograms().get(i), constraints.getScheduleWeeks()));
		}
		
		for(int days = 0; days < constraints.getScheduleWeeks()*5; days++){
			for(int slots = 0; slots < 4; slots++){
				for(int rooms = 0; rooms < constraints.getNumClassrooms(); rooms++){
					int index = (Integer) c.getGene(days + slots + rooms).getAllele();
					if(index > -1){
						ScheduleElement element = constraints.getScheduleElements().get(index);
						
						Schedule schedule = result.get(constraints.getPrograms().indexOf(element.getProgram()));
						
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

