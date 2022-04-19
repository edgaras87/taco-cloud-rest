package tacos.web.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tacos.Ingredient;
import tacos.data.IngredientRepository;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class IngredientController {
	
	private IngredientRepository ingredientRepo;

	/**
	 * @Autowired - If your class has only single constructor then 
	 * there is no need to put @Autowired.
	 */
	public IngredientController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}
	
	@GetMapping
	public Iterable<Ingredient> allIngredients() {
		return ingredientRepo.findAll();
	}
	
}
