import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BacteriaLifeUiTest {

    @Mock
    private BacteriaLifeLogic mockLogic;

    private BacteriaLifeUI ui;
    private final int DIMENSION = 30;
    private int[][] initialGen;

    @BeforeEach
    void setUp() {
        // Preparamos datos iniciales válidos
        initialGen = new int[DIMENSION][DIMENSION];
        initialGen[0][0] = 1;

        lenient().when(mockLogic.generateInitialGen()).thenReturn(initialGen);
        lenient().when(mockLogic.getRound()).thenReturn(0);
    }

    @AfterEach
    void tearDown() {
        for (Frame frame : JFrame.getFrames()) {
            frame.dispose();
        }
    }
    
}