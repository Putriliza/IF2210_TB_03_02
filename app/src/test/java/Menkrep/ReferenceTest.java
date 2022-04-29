package Menkrep;

import Menkrep.Model.Reference.Reference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReferenceTest {
    private static Reference ref;

    @BeforeAll
    static void initAll() {
        ref = Reference.getInstance();
    }

    @Test
    @DisplayName("Read CSV Check")
    void initReaderCheck() {
        assertNotNull(ref.getReferenceDeck());
        assertNotNull(ref.getKarakter());
        assertNotNull(ref.getLvl());
        assertNotNull(ref.getMorph());
        assertNotNull(ref.getPtn());
        assertNotNull(ref.getSwap());
    }
}
