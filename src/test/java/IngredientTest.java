import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.SAUCE, "Соус", 100.0f},
                {IngredientType.FILLING, "Начинка", 200.0f}
        };
    }

    @Test
    public void testIngredient() {
        // Создание стаба для имитации поведения класса Ingredient
        Ingredient ingredientStub = mock(Ingredient.class);
        when(ingredientStub.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientStub.getName()).thenReturn("Соус");
        when(ingredientStub.getPrice()).thenReturn(100.0f);

        // Тестирование поведения класса Ingredient
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Соус", 100.0f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
        assertEquals("Соус", ingredient.getName());
        assertEquals(100.0f, ingredient.getPrice(), 0.01);
    }
}