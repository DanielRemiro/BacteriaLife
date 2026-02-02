import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class HangmanLogicTest {
	
	BacteriaLifeLogic bacteriaLifeLogic;
	
	@Test
	void testBacteriaConstructor() {
		//given	//when
		var bacteriaLifeLogic = new BacteriaLifeLogic(0);
		//then
		assertNotNull(bacteriaLifeLogic);
	}
	
	
	@Test
	void testGenerateInitialGen() {
		//given	//when
		int dimension = 10;
		var bacteriaLifeLogic = new BacteriaLifeLogic(dimension);
		int[][] initialGen = bacteriaLifeLogic.generateInitialGen();
		//then
		assertNotNull(initialGen);
		assertEquals(dimension, initialGen.length);
		assertEquals(dimension, initialGen[0].length);
		//assertArrayEquals(new int[10][10], initialGen);
	}
	
	
	@Test
	void testGenerateNewGen() {
		//given	//when
		int dimension = 4;
		var bacteriaLifeLogic = new BacteriaLifeLogic(dimension);
		
		
		int[][] previousGen = new int[][] {
			{0, 1, 0, 0},
			{0, 0, 1, 0},
			{1, 1, 1, 0},
			{0, 0, 0, 0}
		};
		
		int[][] nextGen = bacteriaLifeLogic.generateNewGen(previousGen);
		//then
		assertArrayEquals(new int[4][4], nextGen);
	}
	
	

	
	@BeforeEach
	void setUp() throws Exception {
		bacteriaLifeLogic = new BacteriaLifeLogic();
	}



}
