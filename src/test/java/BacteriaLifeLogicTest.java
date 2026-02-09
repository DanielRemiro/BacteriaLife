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
	@Test
	void testCheckNeighbours() {

		int[][] manualGen = {
				{1, 0, 1},
				{0, 1, 0},
				{0, 1, 0}
		};

		int vecinosCentral = BacteriaLifeLogic.checkNeighbours(manualGen, 1, 1);
		assertEquals(3, vecinosCentral, "La celula central deberia tener 3 vecinos");

		int vecinosEsquina = BacteriaLifeLogic.checkNeighbours(manualGen, 0, 0);
		assertEquals(1, vecinosEsquina, "La esquina 0,0 deberia tener 1 vecino ");
	}

	@Test
	void testGameRules() {

		int[][] currentGen = {
				{0, 1, 0},
				{0, 1, 0},
				{0, 1, 0}
		};


		BacteriaLifeLogic logic3x3 = new BacteriaLifeLogic(3);

		int[][] nextGen = logic3x3.generateNewGen(currentGen);

		//  Soledad (0 o 1 vecino) -> Muerte
		assertEquals(0, nextGen[0][1], "Célula (0,1) debe morir por soledad (1 vecino)");
		assertEquals(0, nextGen[2][1], "Célula (2,1) debe morir por soledad (1 vecino)");

		// Supervivencia (2 vecinos)
		assertEquals(1, nextGen[1][1], "Célula (1,1) debe sobrevivir (2 vecinos)");

		// Nacimiento (3 vecinos)
		assertEquals(1, nextGen[1][0], "Célula (1,0) debe nacer (3 vecinos)");
		assertEquals(1, nextGen[1][2], "Célula (1,2) debe nacer (3 vecinos)");
	}

	@Test
	@DisplayName("Debe matar por asfixia (más de 3 vecinos)")
	void testOvercrowding() {
		int[][] currentGen = {
				{1, 1, 1},
				{1, 1, 1}, // La central tiene 8 vecinos -> Muere
				{1, 1, 1}
		};

		BacteriaLifeLogic logic3x3 = new BacteriaLifeLogic(3);
		int[][] nextGen = logic3x3.generateNewGen(currentGen);

		assertEquals(0, nextGen[1][1], "La célula central debe morir por sobrepoblación (>3 vecinos)");
	}

	@Test
	void testCheckStableGen() {
		int[][] gen1 = {{0, 0}, {0, 0}};
		int[][] gen2 = {{0, 0}, {0, 0}};
		int[][] gen3 = {{0, 1}, {0, 0}};

		assertTrue(BacteriaLifeLogic.checkStableGen(gen1, gen2));
		assertFalse(BacteriaLifeLogic.checkStableGen(gen1, gen3));
	}

	@Test
	void testMaxRounds() {

		int[][] gen = new int[5][5];

		// Ejecutamos 300 veces
		for(int i = 0; i < 300; i++) {
			gen = bacteriaLogic.generateNewGen(gen);
		}

		assertEquals(300, bacteriaLogic.getRound());

		// La llamada 301 debería activar el "return gen"  y el mensaje de consola
		int[][] result = bacteriaLogic.generateNewGen(gen);

		// Verificamos que round ha subido a 301
		assertEquals(301, bacteriaLogic.getRound());

		assertSame(gen, result);
	}

}