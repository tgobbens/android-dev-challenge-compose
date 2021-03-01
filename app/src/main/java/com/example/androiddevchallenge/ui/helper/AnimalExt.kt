package com.example.androiddevchallenge.ui.helper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.model.Gender
import java.time.Duration
import java.time.LocalDate
import java.time.Month
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

