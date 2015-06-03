package fr.univnantes.lina.mlnlp.process.evaluation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class InformationRetrievalScoresTest {

	static Set<Integer> query1Corrects;
	static Set<Integer> query2Corrects;
	static Set<Integer> query3Corrects;

	/**
	 * Mind that the ranking should not start at 1 but 0
	 */
	@Before
	public  void setUp() {
		System.out.println("setting up");
		query1Corrects = new HashSet<>();
		query1Corrects.add(0);
		query1Corrects.add(2);
		query1Corrects.add(5);
		query1Corrects.add(9);
		query1Corrects.add(19);

		query2Corrects = new HashSet<>();
		query2Corrects.add(0);
		query2Corrects.add(2);
		query2Corrects.add(14);

		query3Corrects = new HashSet<>();
		query3Corrects.add(6);
	}

	@Test
	public void testAveragePrecision() {
		Double ap1 = InformationRetrievalScores.averagePrecision(query1Corrects, 20, 20);
		Double ap2 = InformationRetrievalScores.averagePrecision(query2Corrects, 15, 20);
		System.out.println("averagePrecision - correct "+query1Corrects+", retrieved 20, rank 20, ap "+ap1);
		System.out.println("averagePrecision - correct "+query2Corrects+", retrieved 15, rank 20, ap "+ap2);
		assertEquals(0.5633333333333332, ap1, 0.01);
		assertEquals(0.6222222222222221, ap2, 0.01);

	}

	@Test
	public void testAveragePrecisionNaN() {
		Double ap1 = InformationRetrievalScores.averagePrecision(query3Corrects, 5, 5);
		System.out.println("averagePrecision - correct "+query3Corrects+", retrieved 5, rank 5, ap "+ap1);

	}

	@Test
	public void testMap() {
		List<Double> queriesSet = new ArrayList<>();

		Double ap1 = InformationRetrievalScores.averagePrecision(query1Corrects, 20, 20);
		Double ap2 = InformationRetrievalScores.averagePrecision(query2Corrects, 15, 20);

		queriesSet.add(ap1);
		queriesSet.add(ap2);

		Double map = InformationRetrievalScores.map(queriesSet);
		System.out.println("map queriesSize"+queriesSet.size()+", map "+map);
		assertEquals(0.5927777777777776, map, 0.001);
		assertEquals(0.593, map, 0.001);

		Double ap3 = InformationRetrievalScores.averagePrecision(query3Corrects, 5, 5);
		queriesSet.add(ap3);
		map = InformationRetrievalScores.map(queriesSet);
		System.out.println("map queriesSize"+queriesSet.size()+", map "+map);
	}

}
