package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        // TODO: Learn how to use drawables and create the placeholder and error images.
        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Populates the views in the activity with the details from the user selected sandwich.
     * @param sandwich with the details to update the UI components with.
     */
    private void populateUI(Sandwich sandwich) {
        TextView mOriginTextView = findViewById(R.id.origin_tv);
        TextView mDescriptionTextView = findViewById(R.id.description_tv);
        TextView mIngredientsTextView = findViewById(R.id.ingredients_tv);
        TextView mAlsoKnownAsTextView = findViewById(R.id.also_known_tv);

        // Check for empty strings and replace them with something more descriptive. This provides
        // a more uniform looking UI without empty fields.
        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        if (placeOfOrigin.equals("")) {
            placeOfOrigin = getString(R.string.unknown);
        }
        String description = sandwich.getDescription();
        if (description.equals("")) {
            description = getString(R.string.no_description_available);
        }
        String ingredients = sandwich.getIngredientsString();
        if (ingredients.equals("")) {
            ingredients = getString(R.string.no_ingredients_available);
        }
        String alsoKnownAs = sandwich.getAlsoKnownAsString();
        if (alsoKnownAs.equals("")) {
            alsoKnownAs = getString(R.string.not_applicable);
        }

        mOriginTextView.setText(placeOfOrigin);
        mDescriptionTextView.setText(description);
        mIngredientsTextView.setText(ingredients);
        mAlsoKnownAsTextView.setText(alsoKnownAs);
    }
}
