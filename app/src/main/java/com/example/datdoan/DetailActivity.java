package com.example.datdoan;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_FOOD = "EXTRA_FOOD";

    private ImageView imgDetailFood;
    private TextView tvDetailName;
    private TextView tvDetailCategory;
    private TextView tvDetailPrice;
    private TextView tvDetailDescription;
    private TextView tvDetailIngredients;
    private TextView tvQuantity;
    private TextView tvTotalPrice;

    private Food food;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detailRoot), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        getIntentData();
        displayFoodDetails();
    }

    private void initViews() {
        ImageButton btnBackDetail = findViewById(R.id.btnBackDetail);
        imgDetailFood = findViewById(R.id.imgDetailFood);
        tvDetailName = findViewById(R.id.tvDetailName);
        tvDetailCategory = findViewById(R.id.tvDetailCategory);
        tvDetailPrice = findViewById(R.id.tvDetailPrice);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        tvDetailIngredients = findViewById(R.id.tvDetailIngredients);
        ImageButton btnMinus = findViewById(R.id.btnMinus);
        tvQuantity = findViewById(R.id.tvQuantity);
        ImageButton btnPlus = findViewById(R.id.btnPlus);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        Button btnOrder = findViewById(R.id.btnOrder);

        btnBackDetail.setOnClickListener(v -> finish());

        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateQuantityAndTotal();
            }
        });

        btnPlus.setOnClickListener(v -> {
            quantity++;
            updateQuantityAndTotal();
        });

        btnOrder.setOnClickListener(v -> showOrderConfirmationDialog());
    }

    private void getIntentData() {
        if (getIntent() != null && getIntent().hasExtra(EXTRA_FOOD)) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                food = getIntent().getSerializableExtra(EXTRA_FOOD, Food.class);
            } else {
                @SuppressWarnings("deprecation")
                Food f = (Food) getIntent().getSerializableExtra(EXTRA_FOOD);
                food = f;
            }
        }
    }

    private void displayFoodDetails() {
        if (food == null) {
            Toast.makeText(this, "Không thể tải thông tin món ăn!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        imgDetailFood.setImageResource(food.getImageResId());
        tvDetailName.setText(food.getName());
        tvDetailCategory.setText(food.getCategory());
        tvDetailPrice.setText(PriceUtils.formatPrice(food.getPrice()));
        tvDetailDescription.setText(food.getDescription());
        tvDetailIngredients.setText(food.getIngredients());

        updateQuantityAndTotal();
    }

    private void updateQuantityAndTotal() {
        tvQuantity.setText(String.valueOf(quantity));
        long totalPrice = food != null ? food.getPrice() * quantity : 0;
        tvTotalPrice.setText(PriceUtils.formatPrice(totalPrice));
    }

    private void showOrderConfirmationDialog() {
        if (food == null) return;

        long totalPrice = food.getPrice() * quantity;
        String message = "Bạn đã đặt " + quantity + "x " + food.getName()
                + "\nTổng tiền: " + PriceUtils.formatPrice(totalPrice);

        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.order_success))
                .setMessage(message)
                .setIcon(R.drawable.ic_hamburger)
                .setPositiveButton("Đồng ý", (dialog, which) -> {
                    Toast.makeText(this, getString(R.string.order_success), Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("Tiếp tục đặt", (dialog, which) -> dialog.dismiss())
                .show();
    }
}