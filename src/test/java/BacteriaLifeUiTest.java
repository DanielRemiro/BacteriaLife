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

    @BeforeEach
    void setUp() {
        mockLogic = mock(BacteriaLifeLogic.class);

        when(mockLogic.generateInitialGen()).thenReturn(new int[30][30]);

        ui = new BacteriaLifeUI(mockLogic);
    }
    @Test
    void testGetBGColor(){
        Color c= ui.getBG_COLOR();
        assertEquals(c,ui.getBG_COLOR());
    }
}