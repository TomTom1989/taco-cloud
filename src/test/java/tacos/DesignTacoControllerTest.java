package tacos;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import tacos.Ingredient;
import tacos.data.IngredientRepository;
import tacos.web.DesignTacoController;

@ExtendWith(MockitoExtension.class) // Use MockitoExtension for JUnit 5
public class DesignTacoControllerTest {

    @InjectMocks
    private DesignTacoController controller;

    @Mock
    private Model model;

    @Mock
    private IngredientRepository ingredientRepo;

    @Test
    public void testAddIngredientsToModel() {
        // Create a list of ingredients that you expect to be returned by the repository
        List<Ingredient> mockIngredients = Arrays.asList(
            new Ingredient("1", "Ingredient1", Ingredient.Type.PROTEIN),
            new Ingredient("2", "Ingredient2", Ingredient.Type.CHEESE)
            // Add more ingredients as needed
        );

        // Mock the behavior of the ingredientRepo to return the mockIngredients list
        when(ingredientRepo.findAll()).thenReturn(mockIngredients);

        // Call the addIngredientsToModel method
        controller.addIngredientsToModel(model);

        // Verify that the model contains the expected attributes
        verify(model).addAttribute(eq("protein"), anyList()); // Ensure that "protein" attribute is added with a list of ingredients
        verify(model).addAttribute(eq("cheese"), anyList());  // Ensure that "cheese" attribute is added with a list of ingredients
        // Verify for other ingredient types as well

        // You can further assert that the model attributes contain the expected data
    }
}
