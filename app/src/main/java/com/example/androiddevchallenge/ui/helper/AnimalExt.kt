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
package com.example.androiddevchallenge.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.model.Gender
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun Animal.getGender(): String {
    return stringResource(id = if (gender == Gender.FEMALE) R.string.female else R.string.male)
}

@Composable
fun Animal.getGenderShort(): String {
    return stringResource(id = if (gender == Gender.FEMALE) R.string.female_short else R.string.male_short)
}

@Composable
fun Animal.getWeight(): String {
    return stringResource(R.string.weight_grams, weightGrams)
}

@Composable
fun Animal.getAgeMonths(): String {
    val months = ChronoUnit.MONTHS.between(dob, LocalDate.now())
    if (months <= 1L) {
        val weeks = ChronoUnit.WEEKS.between(dob, LocalDate.now())
        return stringResource(R.string.age_weeks, weeks)
    }

    return stringResource(R.string.age_months, months)
}

@Composable
fun Animal.getAgeMonthsShort(): String {
    val months = ChronoUnit.MONTHS.between(dob, LocalDate.now())
    if (months <= 1L) {
        val weeks = ChronoUnit.WEEKS.between(dob, LocalDate.now())
        return stringResource(R.string.age_weeks_short, weeks)
    }

    return stringResource(R.string.age_months_short, months)
}
