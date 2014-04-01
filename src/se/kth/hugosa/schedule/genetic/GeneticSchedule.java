package se.kth.hugosa.schedule.genetic;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;
import se.kth.hugosa.schedule.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneticSchedule {
	private Configuration conf;
	private FitnessFunction func;
	private Genotype population;
	private Constraints constraints;
	private int time;
    private long startTime;

	public GeneticSchedule (Constraints constraints, int popSize, int time) throws InvalidConfigurationException {
		conf = new DefaultConfiguration();
		conf.resetProperty(Configuration.PROPERTY_FITEVAL_INST);
		conf.setFitnessEvaluator(new DeltaFitnessEvaluator());
		
		this.constraints = constraints;
		this.time = time;
        this.startTime = System.nanoTime();

		func = new ScheduleFitnessFunction(constraints);
		conf.setFitnessFunction(func);
		
		int numSlots = constraints.getNumSlots();
		int numElements = constraints.getNumElements();
		
		Gene[] sampleGenes = new Gene[numElements];

		for (int i = 0; i<sampleGenes.length; i++){
			sampleGenes[i] = new IntegerGene(conf, 0, numSlots-1);
		}
		
		Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);
		
		conf.setSampleChromosome(sampleChromosome);
		conf.setPopulationSize(popSize);
		
		population = Genotype.randomInitialGenotype(conf);
		
		/*List<IChromosome> chromosomes2 = population.getPopulation().getChromosomes();
		for (IChromosome chromosome : chromosomes2) {
			Schedule.printSchedule(generateSchedule(chromosome, constraints));
		}
		*/
		
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
            	System.out.print("" + evolutions + ", " + fitnessValue + "; ");
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
			//Schedule.printSchedule(generateSchedule(bestSolution, constraints));
		}
		//System.out.println("Best fitness value: " + func.getFitnessValue(bestSolution));
		return generateSchedule(bestSolution, constraints);
	}
	
	public static ArrayList<Schedule> generateSchedule(IChromosome c, Constraints constraints){
		/* //test
		for(Gene g : c.getGenes()){
			System.out.println(""+ (Integer) g.getAllele());
		}
		System.out.println("-----------------");
		*/
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		for(int i = 0; i < constraints.getNumPrograms(); i++){
			result.add(new Schedule(constraints.getPrograms().get(i), constraints.getScheduleWeeks()));
		}
		
		for (int i = 0; i < c.getGenes().length; i++) {
			IntegerGene gene = (IntegerGene) c.getGene(i);
			int index = gene.intValue();
			int rooms = index % constraints.getNumClassrooms();
			int slots = ((index - rooms) / 4) % 4;
			int days = (index - (slots * 4) - rooms) / (constraints.getNumClassrooms() * 4);
			
			ScheduleElement element = constraints.getScheduleElements().get(i);
			
			Schedule schedule = result.get(constraints.getPrograms().indexOf(element.getProgram()));
			
			Day day = schedule.days.get(days);
			if(day == null){
				day = new Day();
			}
			
			System.out.println("Element info: " + element.toString());
			System.out.println("in " + constraints.getClassrooms().get(rooms) + " on day " + days + " slot " + slots + ".");
			
			
			TimeSlot slot = day.timeSlots.get(slots);
			slot.elementsMap.put(constraints.getClassrooms().get(rooms), element);
			schedule.days.set(days, day);
			
		}
		/*
		for(int days = 0; days < constraints.getScheduleWeeks()*5; days++){
			for(int slots = 0; slots < 4; slots++){
				for(int rooms = 0; rooms < constraints.getNumClassrooms(); rooms++){
					int geneIndex = (days*constraints.getNumClassrooms()*4 + slots*constraints.getNumClassrooms() + rooms);
					int index = (Integer) c.getGene(geneIndex).getAllele();
					//System.out.println("Working on gene #" + geneIndex + ", index = " + index); //test
					if(index > -1){
						//System.out.println("Inserting element #" + index + ":"); //test
						ScheduleElement element = constraints.getScheduleElements().get(index);
						
						Schedule schedule = result.get(constraints.getPrograms().indexOf(element.getProgram()));
						
						//System.out.println(element.getProgram() + " should match " + schedule.getProgram() + "."); //test
						
						Day day = schedule.days.get(days);
						if(day == null){
							day = new Day();
						}
						///test
						System.out.println("Element info: " + element.toString());
						System.out.println("in " + constraints.getClassrooms().get(rooms) + " on day " + days + " slot " + slots + ".");
						//
						
						
						TimeSlot slot = day.timeSlots.get(slots);
						slot.elementsMap.put(constraints.getClassrooms().get(rooms), element);
						schedule.days.set(days, day);
					}
				}
			}
		}
		*/
		return result;
	}
	
}

