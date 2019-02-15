package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static final String TAG = JsonUtils.class.getSimpleName();

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    // Image fallback string
    private static final String NO_IMAGE_FOUND = "no image found";

    /**
     * Parses the JSON String and returns a Sandwich object.
     * @param json to parse.
     * @return The Sandwich object.
     */
    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich;
        try {
            JSONObject sandwichData = new JSONObject(json);
            JSONObject sandwichNameData = sandwichData.getJSONObject(NAME);
            String mainName = sandwichNameData.getString(MAIN_NAME);
            JSONArray alsoKnownAsArray = sandwichNameData.getJSONArray(ALSO_KNOWN_AS);
            ArrayList<String> alsoKnownAs = getSandwichDetailsList(alsoKnownAsArray);
            String placeOfOrigin = sandwichData.getString(PLACE_OF_ORIGIN);
            String description = sandwichData.getString(DESCRIPTION);
            String image = sandwichData.optString(IMAGE, NO_IMAGE_FOUND);
            JSONArray ingredientsArray = sandwichData.getJSONArray(INGREDIENTS);
            ArrayList<String> ingredients = getSandwichDetailsList(ingredientsArray);
            sandwich = new Sandwich(mainName,
                                    alsoKnownAs,
                                    placeOfOrigin,
                                    description,
                                    image,
                                    ingredients);
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return null;
        }
        return sandwich;
    }

    /**
     * Creates an ArrayList of Strings from the given JSONArray. Used for the alsoKnownAs array and
     * the ingredients array.
     * @param jArray to get the details for.
     * @return An ArrayList of Strings that represent the ingredients and also known as details.
     */
    private static ArrayList<String> getSandwichDetailsList(JSONArray jArray) {
        ArrayList<String> detailsList = new ArrayList<>();
        if (jArray != null) {
            for (int i=0;i<jArray.length();i++) {
                try {
                    detailsList.add(jArray.getString(i));
                } catch (JSONException e) {
                    Log.e(TAG, e.getMessage());
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return detailsList;
    }
}
