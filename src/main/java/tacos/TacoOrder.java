package tacos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
@Data
@Document
public class TacoOrder implements Serializable {
	 private static final long serialVersionUID = 1L;
	 @Id
	 private String id;
	 private Date placedAt = new Date();

 @NotBlank(message="Delivery name is required")
 private String deliveryName;
 @NotBlank(message="Street is required")
 private String deliveryStreet;
 @NotBlank(message="City is required")
 private String deliveryCity;
 @NotBlank(message="State is required")
 private String deliveryState;
 @NotBlank(message="Zip code is required")
 private String deliveryZip;
 @CreditCardNumber(message="Not a valid credit card number")
 private String ccNumber;
 @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
 message="Must be formatted MM/YY")
 private String ccExpiration;
 @Digits(integer=3, fraction=0, message="Invalid CVV")
 private String ccCVV;
 
 
 
 private List<Taco> tacos = new ArrayList<>();
 public void addTaco(Taco taco) {
 this.tacos.add(taco);
 }
}
