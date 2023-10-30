package tacos;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@UserDefinedType("ingredient")
public class IngredientUDT {
    private String name;
    private Ingredient.Type type;

    public IngredientUDT() {
        // Default constructor
    }

    public IngredientUDT(String name, Ingredient.Type type) {
        this.name = name;
        this.type = type;
    }

    public void setName(String id) {
        this.name = id;
    }
}
