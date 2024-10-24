package lab_3a.src.test.java;
import lab_3a.src.main.java.MovieTicketPriceCalculator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;

public class MovieTicketPriceCalculatorTest {

    @Test
    public void testStandardPrice() {
        MovieTicketPriceCalculator calculator = new MovieTicketPriceCalculator(
                LocalTime.of(10, 0),  
                LocalTime.of(14, 0),  
                12,                  
                65                 
        );

        int price = calculator.computePrice(LocalTime.of(15, 0), 30);
        assertEquals(2700, price, "Standard price for 30-year-old should be 2700 cents.");
    }

    @Test
    public void testMatineePrice() {
        MovieTicketPriceCalculator calculator = new MovieTicketPriceCalculator(
                LocalTime.of(10, 0),
                LocalTime.of(14, 0),
                12,
                65
        );

        int price = calculator.computePrice(LocalTime.of(11, 0), 25);
        assertEquals(2400, price, "Matinee price for 25-year-old should be 2400 cents.");
    }

    @Test
    public void testSeniorDiscount() {
        MovieTicketPriceCalculator calculator = new MovieTicketPriceCalculator(
                LocalTime.of(10, 0),
                LocalTime.of(14, 0),
                12,
                65
        );

        int price = calculator.computePrice(LocalTime.of(15, 0), 70);
        assertEquals(2300, price, "Senior discount should apply for 70-year-old.");
    }

    @Test
    public void testChildDiscount() {
        MovieTicketPriceCalculator calculator = new MovieTicketPriceCalculator(
                LocalTime.of(10, 0),
                LocalTime.of(14, 0),
                12,
                65
        );

        int price = calculator.computePrice(LocalTime.of(15, 0), 10);
        assertEquals(2700 - 300, price, "Child discount should apply for 10-year-old on the standard price ticket.");
    }

    @Test
    public void testInvalidMatineeTimes() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new MovieTicketPriceCalculator(
                    LocalTime.of(15, 0),
                    LocalTime.of(10, 0),
                    12,
                    65
            );
        });
        assertEquals("matinee start time cannot come after end time", exception.getMessage());
    }
}
