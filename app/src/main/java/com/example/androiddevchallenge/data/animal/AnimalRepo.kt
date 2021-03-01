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
package com.example.androiddevchallenge.data.animal

import android.net.Uri
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.model.Gender
import java.time.LocalDate
import java.util.UUID
import kotlin.random.Random

interface AnimalRepo {

    fun getAnimals(): List<Animal>

    fun getAnimalById(id: String): Animal?
}

class StubAnimalRepo : AnimalRepo {
    override fun getAnimals(): List<Animal> {
        return stubData
    }

    override fun getAnimalById(id: String): Animal? {
        return stubData.find { it.id == id }
    }

    private val lorumIpsumShortDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
    private val lorumIpsumDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem."

    private val breeds = listOf(
        "norfolk",
        "scottish",
        "schipperke",
        "retriever",
        "kuvasz",
        "vizsla",
        "clumber",
        "patterdale",
        "terrier",
        "dalmatian",
        "weimaraner",
        "keeshond",
        "silky",
    )

    private val names = listOf(
        "Max",
        "Charlie",
        "Cooper",
        "Buddy",
        "Milo",
        "Bear",
        "Rocky",
        "Duke",
        "Tucker",
        "Jack",
        "Bella",
        "Luna",
        "Lucy",
        "Daisy",
        "Lola",
        "Sadie",
        "Molly",
        "Bailey",
        "Stella",
        "Maggie",
    )

    private val stubData by lazy {
        val dogList = mutableListOf<Animal>()

        repeat(12) {
            dogList.add(
                Animal(
                    UUID.randomUUID().toString(),
                    name = names.random(),
                    dob = LocalDate.now().minusDays(Random.nextLong(8, 100)),
                    gender = if (Random.nextBoolean()) Gender.FEMALE else Gender.MALE,
                    weightGrams = Random.nextInt(800, 2000),
                    breed = breeds.random(),
                    imageUri = Uri.parse("file:///android_asset/dog_$it.jpg"),
                    lorumIpsumShortDescription,
                    lorumIpsumDescription
                )
            )
        }
        dogList.toList()
    }
}
