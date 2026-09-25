package com.example.datdoan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SearchActivity extends AppCompatActivity implements FoodAdapter.OnFoodClickListener {

    private EditText edtSearch;
    private ImageButton btnClearText;
    private RecyclerView rvSearchResults;
    private LinearLayout layoutEmptyView;
    private FoodAdapter searchAdapter;
    private List<Food> allFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.searchRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupListeners();
        setupData();
    }

    private void initViews() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        edtSearch = findViewById(R.id.edtSearch);
        btnClearText = findViewById(R.id.btnClearText);
        TextView btnSearch = findViewById(R.id.btnSearch);
        rvSearchResults = findViewById(R.id.rvSearchResults);
        layoutEmptyView = findViewById(R.id.layoutEmptyView);

        rvSearchResults.setLayoutManager(new LinearLayoutManager(this));

        btnBack.setOnClickListener(v -> finish());

        btnClearText.setOnClickListener(v -> {
            edtSearch.setText("");
            performSearch("");
        });

        btnSearch.setOnClickListener(v -> {
            String query = edtSearch.getText().toString().trim();
            performSearch(query);
        });
    }

    private void setupListeners() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyword = s.toString();
                if (keyword.isEmpty()) {
                    btnClearText.setVisibility(View.GONE);
                } else {
                    btnClearText.setVisibility(View.VISIBLE);
                }
                performSearch(keyword);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch(edtSearch.getText().toString().trim());
                return true;
            }
            return false;
        });
    }

    private void setupData() {
        allFoods = FoodData.getFoodList();
        searchAdapter = new FoodAdapter(new ArrayList<>(allFoods), this);
        rvSearchResults.setAdapter(searchAdapter);
    }

    private void performSearch(String keyword) {
        if (allFoods == null) return;

        String query = keyword.trim();
        if (query.isEmpty()) {
            searchAdapter.updateList(new ArrayList<>(allFoods));
            rvSearchResults.setVisibility(View.VISIBLE);
            layoutEmptyView.setVisibility(View.GONE);
            return;
        }

        List<Food> matchedFoods = new ArrayList<>();
        String normalizedQuery = removeAccent(query.toLowerCase());

        for (Food food : allFoods) {
            String foodName = removeAccent(food.getName().toLowerCase());
            String foodDesc = removeAccent(food.getDescription().toLowerCase());
            String category = removeAccent(food.getCategory().toLowerCase());

            if (foodName.contains(normalizedQuery) || foodDesc.contains(normalizedQuery) || category.contains(normalizedQuery)) {
                matchedFoods.add(food);
            }
        }

        if (matchedFoods.isEmpty()) {
            rvSearchResults.setVisibility(View.GONE);
            layoutEmptyView.setVisibility(View.VISIBLE);
        } else {
            rvSearchResults.setVisibility(View.VISIBLE);
            layoutEmptyView.setVisibility(View.GONE);
            searchAdapter.updateList(matchedFoods);
        }
    }

    private static String removeAccent(String s) {
        if (s == null) return "";
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
    }

    @Override
    public void onFoodClick(Food food) {
        Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
        intent.putExtra("EXTRA_FOOD", food);
        startActivity(intent);
    }
}