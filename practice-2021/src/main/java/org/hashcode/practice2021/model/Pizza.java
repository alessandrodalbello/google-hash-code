package org.hashcode.practice2021.model;

import java.util.Objects;
import java.util.Set;

public class Pizza {

    private final int id;
    private final Set<String> ingredients;

    public Pizza(int id, Set<String> ingredients) {
        this.id = id;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public int getNumberOfIngredients() {
        return ingredients.size();
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pizza other = (Pizza) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return Pizza.class.getSimpleName() + "{" +
                "id=" + id +
                ", ingredients=" + ingredients.size() +
                "}";
    }

}
