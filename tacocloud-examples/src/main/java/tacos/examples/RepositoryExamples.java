package tacos.examples;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.data.IngredientRepository;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

@Service
public class RepositoryExamples {

	private boolean print = false;

	// @Autowired https://odrotbohm.de/2013/11/why-field-injection-is-evil/
	private IngredientRepository ingredietRepo;
	private TacoRepository tacoRepository;
	private OrderRepository orderRepository;

	@Autowired
	public RepositoryExamples(IngredientRepository ingredietRepo, TacoRepository tacoRepository,
			OrderRepository orderRepository) {
		this.ingredietRepo = ingredietRepo;
		this.tacoRepository = tacoRepository;
		this.orderRepository = orderRepository;
	}

	@Bean
	public ApplicationRunner ingredientRepoExamples(Map<String, Ingredient> menu) {
		return args -> {
			if (print)
				System.out.println("=== Repository Ingredient Start ===");
			ingredietRepo.save(menu.get("Flour Tortilla"));
			ingredietRepo.save(menu.get("Corn Tortilla"));
			ingredietRepo.save(menu.get("Ground Beef"));
			ingredietRepo.save(menu.get("Carnitas"));
			ingredietRepo.save(menu.get("Diced Tomatoes"));
			ingredietRepo.save(menu.get("Lettuce"));
			ingredietRepo.save(menu.get("Cheddar"));
			ingredietRepo.save(menu.get("Monterrey Jack"));
			ingredietRepo.save(menu.get("Salsa"));
			ingredietRepo.save(menu.get("Sour Cream"));

			if (print) {
				System.out.println("Ingredient amount: " + ingredietRepo.count());
				for (Ingredient ingredient : ingredietRepo.findAll())
					System.out.println("\t" + ingredient);

				System.out.println("=== Repository Ingredient End ===");
				System.out.println("");
			}
		};
	}

	@Bean
	public ApplicationRunner tacoRepoExamples(List<Taco> tacos) {
		return args -> {

			if (print)
				System.out.println("=== Repository Taco Start ===");

			for (Taco taco : tacos) {
				taco.setId(null);
				tacoRepository.save(taco);
			}

			if (print) {
				System.out.println("Taco amount: " + tacoRepository.count());
				for (Taco taco : tacoRepository.findAll())
					System.out.println("\t" + taco);

				System.out.println("=== Repository Taco End ===");
				System.out.println("");
			}
		};
	}

	@Bean
	public ApplicationRunner tacoOrderRepoExamples(List<TacoOrder> orders) {
		return args -> {

			if (print)
				System.out.println("=== Repository Taco Order Start ===");

			for (TacoOrder order : orders) {
				order.setId(null);
				orderRepository.save(order);
			}

			if (print) {
				System.out.println("Taco amount: " + orderRepository.count());
				for (TacoOrder order : orderRepository.findAll())
					System.out.println("\t" + order);

				System.out.println("=== Repository Taco Order End ===");
				System.out.println("");
			}
		};
	}

}
