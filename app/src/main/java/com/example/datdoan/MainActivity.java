package com.example.datdoan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodAdapter.OnFoodClickListener {

    private RecyclerView rvFoodList;
    private FoodAdapter foodAdapter;
    private List<Food> fullFoodList;
    private LinearLayout layoutCategoryChips;
    private String selectedCategory = "Tất cả";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupData();
        setupCategories();
    }

    private void initViews() {
        rvFoodList = findViewById(R.id.rvFoodList);
        layoutCategoryChips = findViewById(R.id.layoutCategoryChips);
        LinearLayout btnQuickSearch = findViewById(R.id.btnQuickSearch);

        rvFoodList.setLayoutManager(new LinearLayoutManager(this));

        btnQuickSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            startActivity(intent);
        });
    }

    private void setupData() {
        fullFoodList = FoodData.getFoodList();
        foodAdapter = new FoodAdapter(new ArrayList<>(fullFoodList), this);
        rvFoodList.setAdapter(foodAdapter);
    }

    private void setupCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Tất cả");
        categories.add("Đồ ăn nhanh");
        categories.add("Pizza");
        categories.add("Món Việt");
        categories.add("Món Á");
        categories.add("Đồ uống");

        layoutCategoryChips.removeAllViews();

        for (String category : categories) {
            TextView chip = new TextView(this);
            chip.setText(category);
            chip.setTextSize(13);
            chip.setPadding(36, 16, 36, 16);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(8, 0, 8, 0);
            chip.setLayoutParams(params);

            updateChipStyle(chip, category.equals(selectedCategory));

            chip.setOnClickListener(v -> {
                selectedCategory = category;
                for (int i = 0; i < layoutCategoryChips.getChildCount(); i++) {
                    TextView child = (TextView) layoutCategoryChips.getChildAt(i);
                    updateChipStyle(child, child.getText().toString().equals(selectedCategory));
                }
                filterByCategory(selectedCategory);
            });

            layoutCategoryChips.addView(chip);
        }
    }

    private void updateChipStyle(TextView chip, boolean isSelected) {
        if (isSelected) {
            chip.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_category_selected));
            chip.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            chip.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_category_unselected));
            chip.setTextColor(ContextCompat.getColor(this, R.color.text_primary));
        }
    }

    private void filterByCategory(String category) {
        if (category.equals("Tất cả")) {
            foodAdapter.updateList(new ArrayList<>(fullFoodList));
        } else {
            List<Food> filtered = new ArrayList<>();
            for (Food food : fullFoodList) {
                if (food.getCategory().equalsIgnoreCase(category)) {
                    filtered.add(food);
                }
            }
            foodAdapter.updateList(filtered);
        }
    }

    @Override
    public void onFoodClick(Food food) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("EXTRA_FOOD", food);
        startActivity(intent);
    }
}