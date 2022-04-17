package tacos.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;

@Service
public class DomainExamples {

	private boolean print = false;

	@Bean
	public Map<String, Ingredient> initializeIngredients() {
		
		if (print)
			System.out.println("=== Domain Ingredients Start ===");

		Map<String, Ingredient> ingredients = new HashMap<>();
		ingredients.put("Flour Tortilla", new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
		ingredients.put("Corn Tortilla", new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
		ingredients.put("Ground Beef", new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
		ingredients.put("Carnitas", new Ingredient("CARN", "Carnitas", Type.PROTEIN));
		ingredients.put("Diced Tomatoes", new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
		ingredients.put("Lettuce", new Ingredient("LETC", "Lettuce", Type.VEGGIES));
		ingredients.put("Cheddar", new Ingredient("CHED", "Cheddar", Type.CHEESE));
		ingredients.put("Monterrey Jack", new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
		ingredients.put("Salsa", new Ingredient("SLSA", "Salsa", Type.SAUCE));
		ingredients.put("Sour Cream", new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

		if (print) {
			System.out.println("Ingredients:");
			for (Ingredient ingredient : ingredients.values())
				System.out.println("\t" + ingredient.toString());
			System.out.println("=== Domain Ingredients End ===");
			System.out.println("");
		}
		return ingredients;
	}

	@Bean
	public List<Taco> initializeTacoDesigns(Map<String, Ingredient> menu) {

		if (print)
			System.out.println("=== Domain Taco Start ===");

		Taco taco1 = new Taco(Long.valueOf(0), "Taco 1", new Date(), generateIngredients(menu));
		Taco taco2 = new Taco(Long.valueOf(13), "Taco 2", new Date(), generateIngredients(menu));
		Taco taco3 = new Taco(Long.valueOf(22), "Taco 3", new Date(), generateIngredients(menu));
		Taco taco4 = new Taco(Long.valueOf(31), "Taco 4", new Date(), generateIngredients(menu));
		Taco taco5 = new Taco(Long.valueOf(4), "Taco 5", new Date(), generateIngredients(menu));
		Taco taco6 = new Taco(Long.valueOf(53), "Taco 6", new Date(), generateIngredients(menu));
		Taco taco7 = new Taco(Long.valueOf(21), "Taco 7", new Date(), generateIngredients(menu));
		Taco taco8 = new Taco(Long.valueOf(6), "Taco 8", new Date(), generateIngredients(menu));
		Taco taco9 = new Taco(Long.valueOf(8), "Taco 9", new Date(), generateIngredients(menu));
		Taco taco10 = new Taco(Long.valueOf(0), "Taco 10", new Date(), generateIngredients(menu));

		List<Taco> tacos = Arrays.asList(taco1,taco2,taco3,taco4,taco5,
										 taco6,taco7,taco8,taco9,taco10);
		
		if (print) {
			System.out.println("Tacos:");
			for (Taco taco : tacos)
				System.out.println("\t" + taco.toString());

			System.out.println("=== Domain Taco End ===");
			System.out.println("");
		}
		return tacos;
	}

	@Bean
	private List<TacoOrder> initializeTacoOrder(List<Taco> tacos) {
		
		List<TacoOrder> orders = new ArrayList<>();
		List<Taco> tacoList;
		
		if (print)
			System.out.println("=== Domain Order Start ===");
		
		tacoList = Arrays.asList(tacos.get(0), tacos.get(1), tacos.get(2), tacos.get(3));
		TacoOrder order1 = new TacoOrder(Long.valueOf(12), new Date(), "Jon Jonson", "Barton road 143", "London", "UK",
				"PE124TF", "0123999988882323", "10/23", "333", tacoList);
		tacoList = Arrays.asList(tacos.get(4), tacos.get(5), tacos.get(6));
		TacoOrder order2 = new TacoOrder(Long.valueOf(2), new Date(), "Peter Smith", "Royal road 12", "London", "UK",
				"PE157AF", "0123999965454432", "09/25", "553", tacoList);
		tacoList = Arrays.asList(tacos.get(7), tacos.get(8));
		TacoOrder order3 = new TacoOrder(Long.valueOf(32), new Date(), "Ben Anderson", "Dawes street road 143",
				"London", "UK", "PE175TG", "0123999977653342", "06/24", "999", tacoList);
		
		List<TacoOrder> orderList = Arrays.asList(order1, order2, order3);
		
		if (print) {
			System.out.println("Orders:");
			for (TacoOrder tacoOrder : orderList)
				System.out.println("\t" + tacoOrder.toString());

			System.out.println("=== Domain Order End ===");
			System.out.println("");
		}
		return orderList;
	}
	
	
	private List<Ingredient> generateIngredients(Map<String, Ingredient> menu) {

		Random random = new Random();
		int minIngredientsInTaco = 2;
		int maxIngredientsInTaco = 4;
		String[] ingredientNames = menu.keySet().toArray(new String[menu.keySet().size()]);
		int ingredientAmountInMenu = ingredientNames.length;
		int ingredientAmountInTaco = minIngredientsInTaco
				+ random.nextInt(maxIngredientsInTaco - minIngredientsInTaco + 1);

		Set<Ingredient> generatedIngredients = new HashSet<>();

		while (ingredientAmountInTaco > generatedIngredients.size()) {
			generatedIngredients.add(menu.get(ingredientNames[random.nextInt(ingredientAmountInMenu)]));
		}

		return Arrays.asList(generatedIngredients.toArray(new Ingredient[generatedIngredients.size()]));
	}

}