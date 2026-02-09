import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacteriaLifeLogicTest {
	private BacteriaLifeLogic bacteriaLogic;

	@BeforeEach
	void setUp() {
		bacteriaLogic = new BacteriaLifeLogic(5);
	}

	@Test
	void testDefaultConstructor() {
		BacteriaLifeLogic defaultLogic = new BacteriaLifeLogic();
		int[][] gen = defaultLogic.generateInitialGen();
		assertEquals(5, gen.length);
		assertEquals(5, gen[0].length);
	}

	@Test
	void testCustomConstructor() {
		BacteriaLifeLogic customLogic = new BacteriaLifeLogic(10);
		int[][] gen = customLogic.generateInitialGen();
		assertEquals(10, gen.length);
	}
}