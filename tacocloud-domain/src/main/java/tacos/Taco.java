package tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import tacos.Ingredient.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Taco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Date createdAt;

	// FetchType.EAGER - to bypass no session error
	@ManyToMany(targetEntity = Ingredient.class, fetch = FetchType.EAGER)
	private List<Ingredient> ingredients = new ArrayList<>();

	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

}