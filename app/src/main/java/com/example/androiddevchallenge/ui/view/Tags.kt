/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import com.example.androiddevchallenge.ui.theme.black
import com.example.androiddevchallenge.ui.theme.blue
import com.example.androiddevchallenge.ui.theme.blueLight
import com.example.androiddevchallenge.ui.theme.pink
import com.example.androiddevchallenge.ui.theme.pinkLight
import com.example.androiddevchallenge.ui.theme.typography
import com.example.androiddevchallenge.ui.theme.white

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
