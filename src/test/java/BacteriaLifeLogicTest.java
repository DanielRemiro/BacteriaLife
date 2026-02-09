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
	@Test
	void testGenerateInitialGen() {
		int[][] gen = bacteriaLogic.generateInitialGen();

		for (int[] row : gen) {
			for (int cell : row) {
				assertTrue(cell == 0 || cell == 1, "La celula debe ser 0 o 1");
			}
		}
	}
	@Test
	void testInBounds() {
		int[][] grid = new int[5][5];

		// Casos validos
		assertTrue(BacteriaLifeLogic.inBounds(grid, 0, 0)); // Esquina superior izq
		assertTrue(BacteriaLifeLogic.inBounds(grid, 4, 4)); // Esquina inferior der
		assertTrue(BacteriaLifeLogic.inBounds(grid, 2, 2)); // Centro

		// Casos fuera de rango
		assertFalse(BacteriaLifeLogic.inBounds(grid, -1, 0)); // Fila negativa
		assertFalse(BacteriaLifeLogic.inBounds(grid, 0, -1)); // Columna negativa
		assertFalse(BacteriaLifeLogic.inBounds(grid, 5, 0));  // Fila excesiva
		assertFalse(BacteriaLifeLogic.inBounds(grid, 0, 5));  // Columna excesiva
	}
}