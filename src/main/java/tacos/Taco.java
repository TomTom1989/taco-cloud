package tacos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Table("Taco")
public class Taco {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = Uuids.timeBased();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING)
    private Date createdAt = new Date();

    // This is to temporarily capture ingredient IDs from the form.
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<String> ingredientIds;

    // This is the list of IngredientUDT
    @Column("ingredients")
    private List<IngredientUDT> ingredients;

    public static class TacoUDRUtils {

        public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
            IngredientUDT udt = new IngredientUDT();
            udt.setName(ingredient.getName());
            udt.setType(ingredient.getType());
            return udt;
        }
    }
}
