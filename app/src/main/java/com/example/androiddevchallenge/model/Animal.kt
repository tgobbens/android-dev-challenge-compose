package com.example.androiddevchallenge.model

import android.net.Uri
import java.time.LocalDate

data class Animal(

    val id: String,

    val name: String,

    val dob: LocalDate,

    val gender: Gender,

    val weightGrams: Int,

    val breed: String,

    val imageUri: Uri,

    val shortDescription: String,

    val description: String,
)

enum class Gender {
    MALE,
    FEMALE,
}