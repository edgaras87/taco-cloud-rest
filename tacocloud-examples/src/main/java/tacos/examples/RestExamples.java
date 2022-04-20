package tacos.examples;

import java.net.URI;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tacos.Ingredient;

@Service
@Profile("test")
public class RestExamples {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ApplicationRunner fetchIngredients(TacoCloudClient tacoCloudClient) {
		return args -> {

			String ingredientId = "FLTO";
			
			System.out.println("=== GET API START ===");
			System.out.println(
					"\t - Ingredient by id(" + ingredientId + "): " + tacoCloudClient.getIngredientById(ingredientId));

			List<Ingredient> ingredients = tacoCloudClient.getAllIngredients();
			System.out.println("All ingredients:");
			for (Ingredient ingredient : ingredients)
				System.out.println("\t - " + ingredient);
			System.out.println("=== GET API END ===");
			System.out.println();
		};
	}

	@Bean
	public ApplicationRunner putIngredient(TacoCloudClient tacoCloudClient) {
		return args -> {
			System.out.println("=== PUT API START ===");
			String ingredientId = "LETC";
			Ingredient before = tacoCloudClient.getIngredientById(ingredientId);
			System.out.println("Before: " + before);
			tacoCloudClient.updateIngredient(new Ingredient(ingredientId, "Shredded Lettuce", Ingredient.Type.VEGGIES));
			Ingredient after = tacoCloudClient.getIngredientById(ingredientId);
			System.out.println("After: " + after);
			System.out.println("=== PUT API END ===");
			System.out.println();
		};
	}

	@Bean
	public ApplicationRunner postIngredient(TacoCloudClient tacoCloudClient) {
		return args -> {
			System.out.println("=== POST API START ===");

			Ingredient chix = new Ingredient("CHIX", "Shredded Chicken", Ingredient.Type.PROTEIN);
			Ingredient chixAfter = tacoCloudClient.createIngredient(chix);
			System.out.println("\tafter-1:" + chixAfter);

			Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
			URI uri = tacoCloudClient.createIngredientURI(beefFajita);
			System.out.println("\tafter-2:" + uri);

			Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
			Ingredient shrimpAfter = tacoCloudClient.createIngredientResponseEntity(shrimp);
			System.out.println("\tafter-3:" + shrimpAfter);

			List<Ingredient> ingredients = tacoCloudClient.getAllIngredients();
			System.out.println("All ingredients:");
			for (Ingredient ingredient : ingredients)
				System.out.println("\t - " + ingredient);
			System.out.println("=== POST API END ===");
			System.out.println();
		};
	}

	@Bean
	public ApplicationRunner deleteIngredient(TacoCloudClient tacoCloudClient) {
		return args -> {
			System.out.println("=== DELETE API START ===");
			// start by adding a few ingredients so that we can delete them later...
			Ingredient beefFajita = new Ingredient("BFFJ", "Beef Fajita", Ingredient.Type.PROTEIN);
			tacoCloudClient.createIngredient(beefFajita);
			Ingredient shrimp = new Ingredient("SHMP", "Shrimp", Ingredient.Type.PROTEIN);
			tacoCloudClient.createIngredient(shrimp);
			
			
			List<Ingredient> ingredients = tacoCloudClient.getAllIngredients();
			System.out.println("All ingredients:");
			for (Ingredient ingredient : ingredients)
				System.out.println("\t - " + ingredient);

			String id = "CHIX";
			Ingredient before = tacoCloudClient.getIngredientById(id);
			before.setId(id);
			System.out.println("deleted:  " + before);
			tacoCloudClient.deleteIngredient(before);
//			Ingredient after = tacoCloudClient.getIngredientById(id);
//			System.out.println("AFTER:  " + after);

			id = "BFFJ";
			before = tacoCloudClient.getIngredientById(id);
			before.setId(id);
			System.out.println("deleted:  " + before);
			tacoCloudClient.deleteIngredient(before);
//			after = tacoCloudClient.getIngredientById(id);
//			System.out.println("AFTER:  " + after);
			
			id = "SHMP";
			before = tacoCloudClient.getIngredientById(id);
			before.setId(id);
			System.out.println("deleted:  " + before);
			tacoCloudClient.deleteIngredient(before);
//			after = tacoCloudClient.getIngredientById(id);
//			System.out.println("AFTER:  " + after);

			ingredients = tacoCloudClient.getAllIngredients();
			System.out.println("All ingredients:");
			for (Ingredient ingredient : ingredients)
				System.out.println("\t - " + ingredient);
			
			System.out.println("=== DELETE API END ===");
			System.out.println();
		};
	}

}
