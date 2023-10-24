package tacos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;



@Data
@Table
public class Taco {
	
@Id
private Long id;
	
 @NotNull
 @Size(min=5, message="Name must be at least 5 characters long")
 private String name;
 @NotNull
 @Size(min=1, message="You must choose at least 1 ingredient")
 private List<Ingredient> ingredients;


 private Date date;
 public void setCreatedAt(Date date) {
	this.date=date;
	
}
public Date getCreatedAt() {
	return date;
}

public void setId(long id) {
	this.id=id;
}
}