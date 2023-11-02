package tacos;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tacos.data.IngredientRepository;

@SpringBootApplication

public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}
	
	/* @Bean
	    public CommandLineRunner initData(IngredientRepository ingredientRepository) {
	        return args -> {
	            if (ingredientRepository.count() == 0) {  // Only load if the repository is empty
	                Resource resource = new ClassPathResource("ingredients.json");
	                InputStream inputStream = resource.getInputStream();
	                List<Ingredient> ingredients = new ObjectMapper().readValue(inputStream, new TypeReference<List<Ingredient>>() {});
	                ingredientRepository.saveAll(ingredients);
	            }
	        };
	    }*/
	}	
	
	
	
	


