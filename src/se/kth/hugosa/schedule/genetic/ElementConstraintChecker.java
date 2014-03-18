package se.kth.hugosa.schedule.genetic;

import java.util.ArrayList;

import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.IGeneConstraintChecker;
import org.jgap.impl.IntegerGene;

import se.kth.hugosa.schedule.Constraints;

public class ElementConstraintChecker implements IGeneConstraintChecker {
	
	private Constraints c;
	
	public ElementConstraintChecker(Constraints c){
		this.c = c;
	}

	@Override
	public boolean verify(Gene a_gene, Object a_alleleValue,
			IChromosome a_chromosome, int a_geneIndex) {
		ArrayList<Gene> genes = new ArrayList<Gene>();
		String program = c.getElement((int) a_gene.getAllele()).getProgram();
		int indexLowerBound = a_geneIndex - (a_geneIndex%c.getNumClassrooms());
		int indexUpperBound = indexLowerBound + c.getNumClassrooms() - 1; //including
		for(int i = indexLowerBound; i <= indexUpperBound; i++){
			if(i != a_geneIndex){
				genes.add(a_chromosome.getGene(i));
			}
		}
		boolean collisionFound = false;
		for(Gene g : genes){
			IntegerGene gene = (IntegerGene) g;
			int elementIndex = gene.intValue();
			if(c.getElement(elementIndex).getProgram().equals(program)){
				collisionFound = true;
			}
			
		}
		return (!collisionFound);
	}
}
