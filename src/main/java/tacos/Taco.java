package tacos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Document
@Data
public class Taco implements Serializable{
	private static final long serialVersionUID = 1L;
	
 @NotNull
 @Size(min=5, message="Name must be at least 5 characters long")
 private String name;
 
 private Date createdAt = new Date();
 
 @Size(min=1, message="You must choose at least 1 ingredient")
 private List<Ingredient> ingredients = new ArrayList<>();
 
 public void addIngredient(Ingredient ingredient) {
 this.ingredients.add(ingredient);
 }
}