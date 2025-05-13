import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorServiceTest {

    private CalculatorService calc;

    @BeforeEach
    void setUp() {
        calc = new CalculatorService();
    }

    @Test
    void testAdd() {
        double result = calc.add(1.5, 2.5);
        assertEquals(4.0, result, 1e-9, "A soma de 1.5 e 2.5 deve ser 4.0");
    }

    @Test
    void testSubtract() {
        double result = calc.subtract(5.0, 2.0);
        assertEquals(3.0, result, 1e-9, "A subtração de 5.0 menos 2.0 deve ser 3.0");
    }

    @Test
    void testMultiply() {
        double result = calc.multiply(3.0, 4.0);
        assertEquals(12.0, result, 1e-9, "A multiplicação de 3.0 por 4.0 deve ser 12.0");
    }

    @Test
    void testDivide() {
        double result = calc.divide(10.0, 2.0);
        assertEquals(5.0, result, 1e-9, "A divisão de 10.0 por 2.0 deve ser 5.0");
    }

    @Test
    void testDivideByZero() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calc.divide(1.0, 0.0),
                "Dividir por zero deve lançar IllegalArgumentException"
        );
        assertEquals("Divisor não pode ser zero", exception.getMessage());
    }
}

