package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.Model;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("sessionTacos")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final OrderRepository orderRepository;
    private final TacoRepository tacoRepo;

    public DesignTacoController(IngredientRepository ingredientRepo, OrderRepository orderRepository,TacoRepository tacoRepo) {
        this.ingredientRepo = ingredientRepo;
        this.orderRepository = orderRepository;
        this.tacoRepo = tacoRepo;
        log.info("DesignTacoController initialized");
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        log.info("Fetched ingredients: {}", ingredients);
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        log.info("Showing taco design form");
        model.addAttribute("taco", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        List<Ingredient> ingredientsOfType = ((List<Ingredient>) ingredients).stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());

        log.info("Ingredients of type {}: {}", type, ingredientsOfType);

        return ingredientsOfType;
    }

    @PostMapping
    public String processTaco(@Valid @ModelAttribute Taco taco, Errors errors, Model model) {
        if (errors.hasErrors()) {
            log.warn("Validation errors while processing taco: {}", errors.getAllErrors());
            return "design";
        }

        // Save the taco to the MongoDB collection
        tacoRepo.save(taco);
        log.info("Saved taco to the database: {}", taco);

        // Check if sessionTacos attribute exists in the model
        if (!model.containsAttribute("sessionTacos")) {
            model.addAttribute("sessionTacos", new ArrayList<Taco>());
            log.info("Initialized sessionTacos in the session");
        }

        // Retrieve the sessionTacos attribute from the model
        List<Taco> sessionTacos = (List<Taco>) model.getAttribute("sessionTacos");
        sessionTacos.add(taco);
        log.info("Taco added to session: {}", taco);

        return "redirect:/orders/current";
    }


}