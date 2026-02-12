import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
    @Test
    void testConstructorAndInitialization() {

        ui = new BacteriaLifeUI(mockLogic);

        verify(mockLogic).generateInitialGen();

        assertNotNull(ui.genPanel, "El panel de generación no debe ser null");
        assertNotNull(ui.bacteriaGen, "La matriz de bacterias no debe ser null");
        assertEquals(1, ui.bacteriaGen[0][0], "La matriz debe coincidir con la del mock");


        boolean frameFound = false;
        for (Frame f : JFrame.getFrames()) {
            if ("BacteriaLife".equals(f.getTitle()) && f.isVisible()) {
                frameFound = true;
                break;
            }
        }
        assertTrue(frameFound, "La ventana JFrame debería estar visible");
    }

    @Test
    void testDeepCopy() {
        ui = new BacteriaLifeUI(mockLogic);

        int[][] original = {{1, 0}, {0, 1}};
        int[][] copy = ui.deepCopy(original);

        assertArrayEquals(original[0], copy[0]);
        assertArrayEquals(original[1], copy[1]);

        assertNotSame(original, copy);
        assertNotSame(original[0], copy[0]);
    }

    @Test
    void testRefreshGenPanel() {

        ui = new BacteriaLifeUI(mockLogic);

        assertEquals(DIMENSION * DIMENSION, ui.genPanel.getComponentCount());

        ui.bacteriaGen = new int[DIMENSION][DIMENSION];

        ui.refreshGenPanel();

        assertEquals(DIMENSION * DIMENSION, ui.genPanel.getComponentCount());

        BacteriaLifeUI.Circle circle = (BacteriaLifeUI.Circle) ui.genPanel.getComponent(0);
        assertEquals(Color.WHITE, circle.color);
    }
    @Test
    void testStartButtonTriggersLogic() {
        ui = new BacteriaLifeUI(mockLogic);

        int[][] nextGen = new int[DIMENSION][DIMENSION];
        nextGen[5][5] = 1;
        when(mockLogic.generateNewGen(any(int[][].class))).thenReturn(nextGen);

        JLabel dummyLabel = new JLabel("Round: 0");
        JButton startButton = ui.getStartButton(dummyLabel);

        startButton.doClick();

        verify(mockLogic, timeout(1000).atLeastOnce()).generateNewGen(any(int[][].class));

        verify(mockLogic, atLeastOnce()).getRound();
    }

    @Test
    void testStartButtonStopsWaitStable() {
        ui = new BacteriaLifeUI(mockLogic);

        when(mockLogic.generateNewGen(any(int[][].class))).thenReturn(initialGen);

        JButton startButton = ui.getStartButton(new JLabel());
        startButton.doClick();

        verify(mockLogic, timeout(1000).atLeastOnce()).generateNewGen(any(int[][].class));
    }

    @Test
    void testCircleGraphics() {

        BacteriaLifeUI.Circle circle = new BacteriaLifeUI.Circle(Color.RED);

        assertEquals(new Dimension(10, 10), circle.getPreferredSize());

        BufferedImage img = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();

        assertDoesNotThrow(() -> circle.paint(g));

        g.dispose();
    }
}