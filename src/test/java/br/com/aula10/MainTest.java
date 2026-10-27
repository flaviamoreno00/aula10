package br.com.aula10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainTest {

    @Test
    void deveExecutarMetodoMainSemErros() {
        Main app = new Main();
        assertNotNull(app);
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
