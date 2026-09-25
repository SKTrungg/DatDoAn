package com.example.datdoan;

import java.util.ArrayList;
import java.util.List;

public class FoodData {

    public static List<Food> getFoodList() {
        List<Food> list = new ArrayList<>();

        list.add(new Food(
                1,
                "Hamburger bò",
                "Bánh mì burger nhân thịt bò nướng thơm ngon kèm phô mai và rau tươi.",
                55000,
                R.drawable.ic_hamburger,
                "Bột mì, thịt bò nướng, phô mai Cheddar, xà lách, cà chua, sốt mayonnaise, bơ.",
                "Đồ ăn nhanh"
        ));

        list.add(new Food(
                2,
                "Pizza hải sản",
                "Pizza đế giòn thơm nức ngập tràn tôm, mực, sốt cà chua và phô mai Mozzarella.",
                120000,
                R.drawable.ic_pizza,
                "Tôm tươi, mực ống, phô mai Mozzarella, sốt cà chua, ớt chuông, hành tây, lá oregano.",
                "Pizza"
        ));

        list.add(new Food(
                3,
                "Pizza bò",
                "Pizza sốt bò băm phô mai béo ngậy cùng nấm và hành tây tươi ngon.",
                135000,
                R.drawable.ic_pizza,
                "Thịt bò băm, phô mai Mozzarella, sốt BBQ, nấm rơm, hành tây, bơ.",
                "Pizza"
        ));

        list.add(new Food(
                4,
                "Pizza phô mai",
                "Pizza 4 loại phô mai thơm phức béo ngậy dành riêng cho tín đồ phô mai.",
                110000,
                R.drawable.ic_pizza,
                "Phô mai Mozzarella, phô mai Cheddar, phô mai Parmesan, phô mai Gorgonzola, sốt bơ.",
                "Pizza"
        ));

        list.add(new Food(
                5,
                "Mì cay",
                "Mì cay Hàn Quốc chuẩn vị với nước dùng đậm đà, tôm tươi và chả cá.",
                65000,
                R.drawable.ic_noodle,
                "Mì ramen Hàn Quốc, tôm, xúc xích, chả cá Hàn Quốc, nấm kim针, bắp cải, ớt Kimchi.",
                "Món Á"
        ));

        list.add(new Food(
                6,
                "Gà rán",
                "Gà rán giòn rụm bên ngoài, mềm ngọt mọng nước bên trong.",
                75000,
                R.drawable.ic_chicken,
                "Đùi gà tươi, bột chiên giòn, bơ, gia vị truyền thống, kèm sốt tương cà / tương ớt.",
                "Đồ ăn nhanh"
        ));

        list.add(new Food(
                7,
                "Trà sữa",
                "Trà sữa trân châu đường đen thơm ngọt đậm vị trà, béo ngậy vị sữa.",
                35000,
                R.drawable.ic_boba,
                "Trà đen Hồng Trà, sữa tươi thanh trùng, trân châu đường đen, đá viên.",
                "Đồ uống"
        ));

        list.add(new Food(
                8,
                "Phở bò tái nạm",
                "Phở bò truyền thống Hà Nội với nước dùng ninh xương đậm đà thơm nức.",
                60000,
                R.drawable.ic_pho,
                "Bánh phở tươi, thịt bò tái, nạm bò, nước dùng ninh xương ống 12 tiếng, hành lá, rau thơm.",
                "Món Việt"
        ));

        list.add(new Food(
                9,
                "Khoai tây chiên",
                "Khoai tây chiên giòn tan lắc phô mai thơm ngon khó cưỡng.",
                30000,
                R.drawable.ic_fries,
                "Khoai tây tươi cắt sợi, bột phô mai lắc, bơ thực vật, bơ tỏi.",
                "Đồ ăn nhanh"
        ));

        return list;
    }
}