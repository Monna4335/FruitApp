package com.example.fruitapp.domain.usecase

import androidx.compose.ui.graphics.Color
import com.example.fruitapp.R
import com.example.fruitapp.data.model.Fruit
import com.example.fruitapp.ui.theme.Apple
import com.example.fruitapp.ui.theme.BlueberryColor
import com.example.fruitapp.ui.theme.Cherry
import com.example.fruitapp.ui.theme.Gooseberry
import com.example.fruitapp.ui.theme.Lemon
import com.example.fruitapp.ui.theme.Lime
import com.example.fruitapp.ui.theme.Mango
import com.example.fruitapp.ui.theme.Pear
import com.example.fruitapp.ui.theme.Strawberry


class FruitUseCase {
    val fruits = mutableListOf(
        Fruit(
            name = "Blueberry",
            description = "Blueberries are small, sweet, and packed with antioxidants.",
            imageResId = R.drawable.blueberry,
            detailDescription = "Blueberries are rich in vitamins C and K, fiber, and antioxidants. They promote brain function, heart health, and help reduce inflammation. Blueberries are known for their ability to support vision, improve memory, and enhance overall well-being. They can be eaten fresh, blended into smoothies, or used in desserts. They also help regulate blood sugar levels and support healthy digestion. These little berries are incredibly versatile and can be added to salads, yogurt, or used as a topping for cereals.",
            fruitID = 1,
            color = BlueberryColor
        ),
        Fruit(
            name = "Lime",
            description = "Limes are small, green, and tangy fruits.",
            imageResId = R.drawable.lime,
            detailDescription = "Limes are packed with vitamin C, antioxidants, and flavonoids. They promote digestion, boost immunity, and can be used in a variety of culinary applications. The sourness of lime makes it a popular ingredient in both savory and sweet dishes. It also helps in detoxification and can freshen up the breath. Limes are frequently used in beverages like limeades, margaritas, and cocktails, as well as in marinades and salad dressings. Their zest can also be used for extra flavor in baking and cooking.",
            fruitID = 2,
            color = Lime
        ),
        Fruit(
            name = "Mango",
            description = "Mangoes are tropical, sweet fruits with a distinct flavor.",
            imageResId = R.drawable.mango,
            detailDescription = "Mangoes are rich in vitamins A, C, and E, making them excellent for eye health and skin care. They also support immune function and digestion, offering a refreshing taste when eaten fresh or blended into smoothies. Mangoes contain enzymes that aid digestion and can soothe an upset stomach. Their high fiber content supports digestive health and helps maintain cholesterol levels. Mangoes are also high in antioxidants, which can help fight free radicals and reduce inflammation.",
            fruitID = 3,
            color = Mango
        ),
        Fruit(
            name = "Apple",
            description = "Apples are sweet, crispy, and popular worldwide.",
            imageResId = R.drawable.apple,
            detailDescription = "Apples are an excellent source of fiber, vitamin C, and various antioxidants. Regular consumption of apples helps in maintaining heart health, weight management, and digestive wellness. Apples also provide hydration, as they have a high water content. They are known for their ability to keep you full, making them a great snack for weight management. The antioxidants in apples can help lower the risk of chronic diseases and promote overall health. Apples are highly versatile, enjoyed raw, as applesauce, or baked into pies and other desserts.",
            fruitID = 4,
            color = Apple
        ),
        Fruit(
            name = "Cherry",
            description = "Cherries are small, round, and red or black in color.",
            imageResId = R.drawable.cherry,
            detailDescription = "Cherries are rich in vitamins A and C, potassium, and antioxidants. They have anti-inflammatory properties and can help improve sleep quality and reduce muscle soreness. Cherries are known to be beneficial for heart health, as they can help reduce blood pressure and cholesterol levels. They are also effective in managing symptoms of arthritis and gout. Cherries are often eaten fresh, but they can also be used in jams, juices, or as an ingredient in pies and tarts.",
            fruitID = 5,
            color = Cherry
        ),
        Fruit(
            name = "Gooseberry",
            description = "Gooseberries are tart, small, and green in color.",
            imageResId = R.drawable.gooseberry,
            detailDescription = "Gooseberries are loaded with vitamin C, fiber, and antioxidants. They help boost immunity, improve skin health, and are often used in jams, jellies, and juices. The high fiber content in gooseberries promotes gut health, reducing the risk of digestive issues like constipation. Their antioxidant properties may protect against cell damage and support healthy aging. Gooseberries are also thought to help regulate blood sugar levels and may contribute to weight loss due to their low calorie and high fiber content.",
            fruitID = 6,
            color = Gooseberry
        ),
        Fruit(
            name = "Lemon",
            description = "Lemons are tart, yellow fruits that are rich in vitamin C.",
            imageResId = R.drawable.lemon,
            detailDescription = "Lemons are one of the best sources of vitamin C, essential for immune function and skin health. They are commonly used in beverages, salads, and for adding zest to various dishes. Lemons are also known for their detoxifying properties and can aid in the elimination of toxins from the body. They have antibacterial and antiviral properties, making them useful in boosting the immune system. Lemon juice can help improve digestion and prevent kidney stones. It also promotes hydration, especially when mixed with water as a refreshing drink.",
            fruitID = 7,
            color = Lemon
        ),
        Fruit(
            name = "Pear",
            description = "Pears are juicy, sweet fruits with a soft texture.",
            imageResId = R.drawable.pear,
            detailDescription = "Pears are an excellent source of dietary fiber, which supports digestive health. They also provide antioxidants and vitamins C and K, promoting heart health and reducing inflammation. Pears contain high amounts of water, making them great for hydration, especially during the warmer months. They are a low-calorie, filling snack that aids in weight management. The high fiber content also helps maintain healthy blood sugar levels, making pears a great option for people with diabetes or those looking to regulate their blood sugar.",
            fruitID = 8,
            color = Pear
        ),
        Fruit(
            name = "Strawberry",
            description = "Strawberries are red, juicy, and sweet berries.",
            imageResId = R.drawable.strawberry,
            detailDescription = "Strawberries are rich in vitamin C, manganese, and antioxidants. They promote heart health, improve skin quality, and can be enjoyed fresh, in smoothies, or as toppings for desserts. Strawberries are known to reduce oxidative stress and inflammation in the body. The high vitamin C content supports collagen production, which is vital for healthy skin and preventing signs of aging. Strawberries are also beneficial for blood sugar control and have been shown to reduce the risk of certain chronic diseases.",
            fruitID = 9,
            color = Strawberry
        )
    )
    fun getALlFruits(): List<Fruit> {
        return fruits
    }
    fun getFruitByID(id: Int?): Fruit? {
        return fruits.firstOrNull { it.fruitID == id }
    }
    fun addFruit(fruit: Fruit) {
        fruits.add(fruit)
    }
    fun updateFruit(updatedFruit: Fruit) {
        val index = fruits.indexOfFirst { it.fruitID == updatedFruit.fruitID }
        if (index != -1) {
            fruits[index] = updatedFruit
        }
    }
    fun deleteFruit(id: Int) {
        fruits.removeIf { it.fruitID == id }
    }
}
