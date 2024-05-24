package fpoly.minhpt.kot104_assignment_ph32719.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cabin
import androidx.compose.material.icons.rounded.Chair
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.TableBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpoly.minhpt.kot104_assignment_ph32719.data.Categories

class CategorySection {
}

val items = listOf(
    Categories(1, "Popular", Icons.Rounded.Star, false),
    Categories(2, "Char", Icons.Rounded.Chair, false),
    Categories(3, "Cabin", Icons.Rounded.Cabin, false),
    Categories(4, "Table", Icons.Rounded.TableBar, false),


    )

@Preview
@Composable
fun CategoriesSection() {
    LazyRow {
        items(items.size) { index ->
            CategoryItem(index)
        }
    }
}


@Composable
fun CategoryItem(index: Int) {
    val category = items[index]
    val itemWidth = 120.dp // Set a fixed width for each item
    val padding = 16.dp

    Box(
        modifier = Modifier
            .width(itemWidth)
            .padding(
                start = padding,
                end = if (index == items.size - 1) padding else 0.dp,
                top = padding
            )
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xffF5F5F5))
            .clickable { },
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(8.dp), // Add padding inside the item for better spacing
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally

        ) {
            Icon(
                imageVector = category.image,
                contentDescription = "Category",
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(Modifier.height(8.dp))
            Text(text = category.name, style = MaterialTheme.typography.labelLarge)
        }
    }
}
