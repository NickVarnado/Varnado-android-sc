package com.udacity.sandwichclub.model;

import java.util.List;

public class Sandwich {

    private String mainName;
    private List<String> alsoKnownAs = null;
    private String placeOfOrigin;
    private String description;
    private String image;
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public Sandwich(String mainName, List<String> alsoKnownAs, String placeOfOrigin, String description, String image, List<String> ingredients) {
        this.mainName = mainName;
        this.alsoKnownAs = alsoKnownAs;
        this.placeOfOrigin = placeOfOrigin;
        this.description = description;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Returns a comma delimited String for the ingredients List.
     * @return
     */
    public String getIngredientsString() {
        return convertListToString(ingredients);
    }

    /**
     * Returns a comma delimited String for the alsoKnownAs List.
     * @return
     */
    public String getAlsoKnownAsString() {
        return convertListToString(alsoKnownAs);
    }

    /**
     * Converts the given list of Strings into a comma delimited String.
     * @param list
     * @return
     */
    private String convertListToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        String separator = ", ";
        for (int i = 0 ; i < list.size(); i++) {
            if (i != list.size() - 1) {
                sb.append(list.get(i)).append(separator);
            } else {
                sb.append(list.get(i)).append(".");
            }
        }
        return sb.toString();
    }
}
