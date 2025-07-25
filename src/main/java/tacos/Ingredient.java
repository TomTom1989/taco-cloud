package tacos;
import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Document(collection = "ingredients")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
public class Ingredient implements Serializable{
	private static final long serialVersionUID = 1L;
	
 @Id
 private String id;
 private String name;
 private Type type;
 
 public static enum Type {
 WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
 }
}