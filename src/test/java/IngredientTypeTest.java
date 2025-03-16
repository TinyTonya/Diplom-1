import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final IngredientType type;

    public IngredientTypeTest(IngredientType type) {
        this.type = type;
    }
    @Parameterized.Parameters
    public static IngredientType[] data() {
        return IngredientType.values();
    }

    @Test
    public void testToStringReturnsName() {
        assertEquals(type.name(), type.toString());
    }
}