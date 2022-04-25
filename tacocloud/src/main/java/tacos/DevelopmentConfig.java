package tacos;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.data.UserRepository;

@Configuration
public class DevelopmentConfig {
	
	@Bean
	public ApplicationRunner dataLoader(
			IngredientRepository ingredRepo, 
			UserRepository userRepo,
			PasswordEncoder encoder) {
		return args -> {
			Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
			Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
			Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
			Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
			Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
			Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
			Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
			Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
			Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
			Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
			ingredRepo.save(flourTortilla);
			ingredRepo.save(cornTortilla);
			ingredRepo.save(groundBeef);
			ingredRepo.save(carnitas);
			ingredRepo.save(tomatoes);
			ingredRepo.save(lettuce);
			ingredRepo.save(cheddar);
			ingredRepo.save(jack);
			ingredRepo.save(salsa);
			ingredRepo.save(sourCream);
			
			userRepo.save(new User("habuma", encoder.encode("password"), "Craig Walls", "123 North Street",
					"Cross Roads", "TX", "76227", "123-123-1234"));
			userRepo.save(new User("jon", encoder.encode("pass"), "Jon Smith", "666 West Street", "Cross Roads", "NY",
					"36211", "123-123-1234"));
			
		};
	}

}
