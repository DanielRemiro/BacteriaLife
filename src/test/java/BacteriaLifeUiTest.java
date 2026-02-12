import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BacteriaLifeUiTest {

    private BacteriaLifeLogic mockLogic;
    private BacteriaLifeUI ui;
    private final int DIMENSION = 30;
    private int[][] initialGen;

    @BeforeEach
    void setUp() {
        initialGen = new int[DIMENSION][DIMENSION];
        initialGen[0][0] = 1; // Un valor inicial

        // Configuración básica del mock
        lenient().when(mockLogic.generateInitialGen()).thenReturn(initialGen);
        lenient().when(mockLogic.getRound()).thenReturn(0);
    }
    
}