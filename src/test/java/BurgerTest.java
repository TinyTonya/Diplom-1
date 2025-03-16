import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    // Моки для зависимостей
    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    // Тесты для метода setBuns
    @Test
    public void testSetBuns() {
        Burger burger = new Burger();
        burger.setBuns(bunMock);

        assertNotNull(burger.bun);
        assertEquals(bunMock, burger.bun);
    }

    // Тесты для метода addIngredient
    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientMock);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock, burger.ingredients.get(0));
    }

    // Тесты для метода removeIngredient
    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    // Тесты для метода moveIngredient
    @Test
    public void testMoveIngredient() {
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE, "Соус", 50.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "Начинка", 100.0f);

        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        assertEquals(ingredient1, burger.ingredients.get(1));
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    // Тесты для метода getPrice
    @Test
    public void testGetPrice() {
        when(bunMock.getPrice()).thenReturn(100.0f);
        when(ingredientMock.getPrice()).thenReturn(50.0f);

        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        float expectedPrice = 100.0f * 2 + 50.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }

    @Test
    public void testGetReceipt() {
        // Настраиваем моки
        when(bunMock.getName()).thenReturn("Булочка с кунжутом");
        when(bunMock.getPrice()).thenReturn(100.0f); // Добавляем цену булочки
        when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock.getName()).thenReturn("Соус");
        when(ingredientMock.getPrice()).thenReturn(50.0f); // Добавляем цену ингредиента

        // Создаем бургер и добавляем ингредиенты
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        // Ожидаемый чек с использованием String.format и %n
        String expectedReceipt = String.format(
                "(==== Булочка с кунжутом ====)%n" +
                        "= sauce Соус =%n" +
                        "(==== Булочка с кунжутом ====)%n%n" +
                        "Price: 250,000000%n"
        );

        // Получаем фактический чек
        String actualReceipt = burger.getReceipt();

        // Сравниваем строки
        assertEquals(expectedReceipt, actualReceipt);
    }

    // Параметризованный тест для метода getPrice
    @RunWith(Parameterized.class)
    public static class BurgerParameterizedTest {

        private final Bun bun;
        private final List<Ingredient> ingredients;
        private final float expectedPrice;

        public BurgerParameterizedTest(Bun bun, List<Ingredient> ingredients, float expectedPrice) {
            this.bun = bun;
            this.ingredients = ingredients;
            this.expectedPrice = expectedPrice;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {new Bun("Булочка", 100.0f), Arrays.asList(new Ingredient(IngredientType.SAUCE, "Соус", 50.0f)), 250.0f},
                    {new Bun("Булочка", 200.0f), Arrays.asList(new Ingredient(IngredientType.FILLING, "Начинка", 100.0f)), 500.0f},
                    {new Bun("Булочка", 50.0f), Arrays.asList(), 100.0f}
            });
        }

        @Test
        public void testGetPrice() {
            Burger burger = new Burger();
            burger.setBuns(bun);
            for (Ingredient ingredient : ingredients) {
                burger.addIngredient(ingredient);
            }
            assertEquals(expectedPrice, burger.getPrice(), 0.01);
        }
    }
}