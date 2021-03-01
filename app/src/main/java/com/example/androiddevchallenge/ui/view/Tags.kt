package com.example.androiddevchallenge.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CalendarViewDay
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.model.Gender
import com.example.androiddevchallenge.ui.helper.SimpleFlowRow
import com.example.androiddevchallenge.ui.helper.getAgeMonths
import com.example.androiddevchallenge.ui.helper.getGender
import com.example.androiddevchallenge.ui.helper.getWeight
import com.example.androiddevchallenge.ui.theme.*

@Composable
fun AnimalTags(modifier: Modifier, animal: Animal) {
    Box(modifier = modifier) {
        SimpleFlowRow(modifier = modifier, verticalGap = 4.dp, horizontalGap = 4.dp) {
            AnimalTag(text = animal.breed, blue, Icons.Filled.Pets)
            AnimalTag(text = animal.name, blueLight, Icons.Outlined.Label)
            AnimalTag(text = animal.getGender(), pinkLight, if (animal.gender == Gender.FEMALE) Icons.Filled.Female else Icons.Filled.Male)
            AnimalTag(text = animal.getWeight(), pink, Icons.Outlined.MonitorWeight)
            AnimalTag(text = animal.getAgeMonths(), blueLight, Icons.Outlined.CalendarToday)
        }
    }
}

@Preview("tag", widthDp = 200, heightDp = 200)
@Composable
fun previewTags() {
    AnimalTag(text = "test", white, Icons.Outlined.CalendarViewDay)
}

@Composable
fun AnimalTag(
    text: String,
    backgroundColor: Color,
    icon: ImageVector
) {
    val backgroundShape = RoundedCornerShape(4.dp)

    Row(
        modifier = Modifier
            .wrapContentSize()
            .border(1.dp, color = black.copy(alpha = 0.15f), shape = backgroundShape)
            .background(color = backgroundColor.copy(0.8f), shape = backgroundShape)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = black.copy(0.6f)
        )

        Text(
            text = text,
            style = typography.subtitle2,
            color = black,
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 4.dp)
        )
    }
}