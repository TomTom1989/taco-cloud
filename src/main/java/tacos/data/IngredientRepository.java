package tacos.data;
import org.springframework.data.mongodb.repository.MongoRepository;

import tacos.Ingredient;

public interface IngredientRepository extends MongoRepository<Ingredient, String> {
}