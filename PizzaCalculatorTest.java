import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PizzaCalculatorTest {

    @Test
    public void testXLWith3Toppings() {
        assertEquals(22.43, PizzaCalculator.calculateTotal("XL", 3));
    }

    @Test
    public void testLWith0Toppings() {
        assertEquals(13.8, PizzaCalculator.calculateTotal("L", 0));
    }

    @Test
    public void testSWith2Toppings() {
        assertEquals(13.23, PizzaCalculator.calculateTotal("S", 2));
    }

    @Test
    public void testInvalidSizeDefaultsToZero() {
        assertEquals(3.45, PizzaCalculator.calculateTotal("", 2)); // Only toppings + tax
    }

    @Test
    public void testZeroToppingsM() {
        assertEquals(11.5, PizzaCalculator.calculateTotal("M", 0));
        }
}