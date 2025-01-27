package us.lsi.clase.combinatorio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;

public class CombinatorioTest {

    @Test
    void binomialCoefficientValidInputs() {
        assertEquals(BigInteger.valueOf(10), Combinatorio.binomialCoefficient(5L, 2L));
        assertEquals(BigInteger.valueOf(1), Combinatorio.binomialCoefficient(5L, 0L));
        assertEquals(BigInteger.valueOf(1), Combinatorio.binomialCoefficient(5L, 5L));
        assertEquals(BigInteger.valueOf(20), Combinatorio.binomialCoefficient(6L, 3L));
    }

    @Test
    void binomialCoefficientInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> Combinatorio.binomialCoefficient(-1L, 2L));
        assertThrows(IllegalArgumentException.class, () -> Combinatorio.binomialCoefficient(5L, -1L));
        assertThrows(IllegalArgumentException.class, () -> Combinatorio.binomialCoefficient(2L, 5L));
    }

    @Test
    void binomialCoefficientEdgeCases() {
        assertEquals(BigInteger.ONE, Combinatorio.binomialCoefficient(0L, 0L));
        assertEquals(BigInteger.ONE, Combinatorio.binomialCoefficient(1L, 0L));
        assertEquals(BigInteger.ONE, Combinatorio.binomialCoefficient(1L, 1L));
    }
}
