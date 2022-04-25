package tacos.examples;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import tacos.Ingredient;

@Service
@Profile("test")
public class TacoCloudClient {
	
	private RestTemplate rest;
	private final String domain = "http://localhost:8080";
	private final String restBasePath = "/data-api";
	private final String ingredientsRestEndpoint = "/ingredients";
	private final String ingredientsUrl = domain + restBasePath + 
										  ingredientsRestEndpoint; 
	
	public TacoCloudClient(RestTemplate rest) {
		this.rest = rest;
	}
	
	/**
	 *  GET examples
	 */
	
	public Ingredient getIngredientById(String ingredientId) {
		return rest.getForObject(ingredientsUrl + "/{id}", 
								 Ingredient.class, 
								 ingredientId);
//		return rest.exchange(ingredientsUrl + "/{id}", 
//				HttpMethod.GET, null, new ParameterizedTypeReference<Ingredient>() {}, ingredientId)
//			.getBody();
	}
	
	/*public Ingredient getIngredientById(String ingredientId) {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		return rest.getForObject("http://localhost:8080/data-api/ingredients/{id}",
				Ingredient.class, urlVariables);
	}*/
	
	public Ingredient getIngredientByIdURL(String ingredientId) {
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		URI url = UriComponentsBuilder
					.fromHttpUrl(ingredientsUrl + "/{id}")
					.build(urlVariables);
		return rest.getForObject(url, Ingredient.class);
	}
	
	/**
	 * getForEntity()
	 */
	
	public Ingredient getIngredientByIdResponseEntity(String ingredientId) {
		ResponseEntity<Ingredient> responseEntity = rest.getForEntity(
				ingredientsUrl + "/{id}",
				Ingredient.class, ingredientId);
		return responseEntity.getBody();
		
	}
	
	public List<Ingredient> getAllIngredients() {
		return rest.exchange("http://localhost:8080/api/ingredients", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Ingredient>>() {})
				.getBody();
	}
	
	/**
	 *  PUT examples
	 */
	
	public void updateIngredient(Ingredient ingredient) {
		rest.put(ingredientsUrl + "/{id}",
				ingredient, ingredient.getId());
	}
	
	/**
	 *  POST examples
	 */
	
	public Ingredient createIngredient(Ingredient ingredient) {
		return rest.postForObject(ingredientsUrl,
				ingredient, Ingredient.class);
	}
	
	public URI createIngredientURI(Ingredient ingredient) {
		return rest.postForLocation(ingredientsUrl, ingredient);
	}
	
	public Ingredient createIngredientResponseEntity(Ingredient ingredient) {
		ResponseEntity<Ingredient> responseEntity = 
				rest.postForEntity(ingredientsUrl, ingredient, null);
		System.out.println("responseEntity: " + responseEntity);
		return responseEntity.getBody();
	}
	
	public void deleteIngredient(Ingredient ingredient) {
		rest.delete(ingredientsUrl + "/{id}", ingredient.getId());
		
	}
	
	
}
