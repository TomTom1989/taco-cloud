package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.IngredientUDT;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.TacoUDT;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository; // Import TacoRepository
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository tacoRepository; // Inject TacoRepository
    private final OrderRepository orderRepository;
    
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            TacoRepository tacoRepository,
            OrderRepository orderRepository) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository; // Initialize OrderRepository
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("taco", new Taco());
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processTaco(@Valid @ModelAttribute Taco taco, Errors errors) {
        // Convert ingredientIds to IngredientUDT before validation
        List<IngredientUDT> selectedIngredients = taco.getIngredientIds().stream()
                .map(id -> ingredientRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Taco.TacoUDRUtils::toIngredientUDT)
                .collect(Collectors.toList());

        taco.setIngredients(selectedIngredients);

        // Now, check for validation errors
        if (errors.hasErrors()) {
            return "design";
        }

        // Save the Taco object into the Taco database
        Taco savedTaco = tacoRepository.save(taco);
        
     // Convert the saved Taco to a TacoUDT
        TacoUDT tacoUDT = new TacoUDT(savedTaco.getName(), savedTaco.getIngredients());
    
     // Create a TacoOrder and associate it with the TacoUDT
        TacoOrder tacoOrder = new TacoOrder(); 
        tacoOrder.addTaco(tacoUDT);

        orderRepository.save(tacoOrder);


        log.info("Processing taco: " + taco);
        return "redirect:/orders/current";
    }


}
