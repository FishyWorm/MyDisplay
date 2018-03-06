package custom;

import java.util.HashMap;

public class Cocktail {

    private String id;
    private String name;
    private boolean alcoholic;
    private HashMap<String, String> recipe;
    private String photoUrl;

    public Cocktail() {
        id = "";
        name = "";
        alcoholic = false;
    }

    public Cocktail(String id, String name, HashMap<String, String> recipe) {
        this.id = id;
        this.name = name;
        this.recipe = recipe;
    }

    public Cocktail(String id, String name, HashMap recipe, boolean alcoholic) {
        this.id = id;
        this.name = name;
        this.recipe = recipe;
        this.alcoholic = alcoholic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlcoholic() {
        return alcoholic;
    }

    public HashMap<String, String> getRecipe() {
        return recipe;
    }

    public void setRecipe(HashMap<String, String> recipe) {
        this.recipe = recipe;
    }

    public void setAlcoholic(boolean alcoholic) {
        this.alcoholic = alcoholic;
    }


}
