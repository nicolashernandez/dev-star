package fr.univnantes.lina.javautil;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;


public class CombiTest {


	/**
	 * https://code.google.com/p/combinatoricslib/
	 */
	@Test
	public void testCombinatorics() {

		// Create an instance of the partition generator to generate all
		// possible partitions of 5
		Generator<Integer> partitionGenerator = Factory.createPartitionGenerator(5);

		// Print the partitions
		for (ICombinatoricsVector<Integer> p : partitionGenerator) {
			System.out.println(p);
		}


		// Create the initial vector of 3 elements (apple, orange, cherry)
		ICombinatoricsVector<String> originalVector = Factory.createVector(new String[] { "apple", "orange", "cherry" });

		// Create the permutation generator by calling the appropriate method in the Factory class
		Generator<String> permutationGenerator = Factory.createPermutationGenerator(originalVector);

		// Print the result
		for (ICombinatoricsVector<String> perm : permutationGenerator)
			System.out.println(perm);
	}

	/**
	 * https://code.google.com/p/combinatoricslib/
	 */
	@Test
	public void testGeneratePartition() {

		String [] wstokens= new String[] { "1994", "[", "ref", "." };

		// Create an instance of the partition generator to generate all
		// possible partitions of 5
		Generator<Integer> partitionGenerator = Factory.createPartitionGenerator(wstokens.length);

		// Print the partitions
		for (ICombinatoricsVector<Integer> p : partitionGenerator) {
			//System.out.println(p);

			String [] partitionStringArray = new String[p.getSize()];
			for (int i=0; i< p.getSize() ; i++) {
				partitionStringArray[i] = String.valueOf(p.getValue(i));
			}

			// Create the initial vector of 3 elements (apple, orange, cherry)
			ICombinatoricsVector<String> originalVector = Factory.createVector(partitionStringArray);

			// Create the permutation generator by calling the appropriate method in the Factory class
			Generator<String> permutationGenerator = Factory.createPermutationGenerator(originalVector);

			// Print the result
			for (ICombinatoricsVector<String> perm : permutationGenerator) {
				//System.out.println(perm);
				int parsed = 0;
				for (String partSizeString : perm.getVector()) {
					int partSize = Integer.valueOf(partSizeString);
					System.out.printf("<t> ");
					for (int j = parsed ; j < parsed + partSize ; j++)
						System.out.printf("%s ",wstokens[j]);
					parsed += partSize;
				}
				System.out.printf("<t> ");
				System.out.println();
			}
		}


	}


	@Rule
	public TestName name = new TestName();

	@Before
	public void printSeparator()
	{
		System.out.println("\n=== " + name.getMethodName() + " =====================");
	}

}
