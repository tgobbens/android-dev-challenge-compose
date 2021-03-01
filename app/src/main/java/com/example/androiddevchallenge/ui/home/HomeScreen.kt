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
package com.example.androiddevchallenge.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.animal.StubAnimalRepo
import com.example.androiddevchallenge.di.AppContainer
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.model.Gender
import com.example.androiddevchallenge.ui.helper.getAgeMonthsShort
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.black
import com.example.androiddevchallenge.ui.theme.blueLight
import com.example.androiddevchallenge.ui.theme.pinkLight
import com.example.androiddevchallenge.ui.theme.typography
import com.example.androiddevchallenge.ui.view.AnimalTags
import dev.chrisbanes.accompanist.picasso.PicassoImage

@Composable
fun HomeScreen(
    navController: NavController,
    appContainer: AppContainer
) {
    HomeScreen({ uri -> navController.navigate(uri) }, appContainer.animalRepo.getAnimals())
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigate: (String) -> Unit,
    animals: List<Animal>
) {
    val title = stringResource(id = R.string.app_name)

    val layoutGrid = rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title) },
                actions = {
                    IconButton(onClick = { layoutGrid.value = !layoutGrid.value }) {
                        Icon(
                            imageVector = if (layoutGrid.value) Icons.Outlined.GridView else Icons.Outlined.ViewList,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {

        if (layoutGrid.value) {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2)
            ) {
                items(animals) {
                    AnimalItem(navigate, animal = it)
                }
            }
        } else {
            LazyColumn {
                animals.forEachIndexed { index, animal ->
                    item {
                        AnimalListItem(navigate = navigate, animal = animal)
                    }

                    if (index != (animals.size - 1)) {
                        item {
                            Divider(modifier = Modifier.padding(horizontal = 8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimalListItem(
    navigate: (String) -> Unit,
    animal: Animal
) {
    Row(
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .clickable { navigate("animalDetail/${animal.id}") }
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
        ) {
            Surface(
                modifier = Modifier
                    .padding(start = 16.dp, end = 8.dp)
                    .border(1.dp, black.copy(alpha = 0.15f))
                    .height(100.dp)
                    .width(100.dp)
                    .align(Alignment.Center),
                shape = RoundedCornerShape(8.dp)
            ) {
                PicassoImage(
                    data = animal.imageUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(Modifier.fillMaxSize()) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterVertically)
                .padding(8.dp)
        ) {
            AnimalTags(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                animal = animal
            )
        }
    }
}

@Composable
fun AnimalItem(
    navigate: (String) -> Unit,
    animal: Animal
) {
    Box(
        Modifier
            .fillMaxWidth()
            .aspectRatio(1.0f)
            .clickable {
                navigate("animalDetail/${animal.id}")
            }
    ) {
        PicassoImage(
            data = animal.imageUri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            loading = {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        val backgroundColor = if (animal.gender == Gender.FEMALE) pinkLight else blueLight
        val backgroundShape = RoundedCornerShape(4.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
                .border(1.dp, color = black.copy(alpha = 0.15f), shape = backgroundShape)
                .background(backgroundColor.copy(alpha = 0.8f), shape = backgroundShape)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = animal.name + " | " + animal.getAgeMonthsShort(),
                style = typography.subtitle2,
                color = black,
                maxLines = 1,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 2.dp)
                    .align(Alignment.CenterStart)
                    .wrapContentSize()
            )

            Icon(
                imageVector = if (animal.gender == Gender.FEMALE) Icons.Filled.Female else Icons.Filled.Male,
                contentDescription = "",
                tint = black.copy(0.6f),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
                    .height(24.dp)
                    .width(24.dp)
            )
        }
    }
}

@Preview("animal_list", widthDp = 400, heightDp = 200)
@Composable
fun AnimalListPreview() {
    MyTheme {
        AnimalListItem({ }, StubAnimalRepo().getAnimals().first())
    }
}

@Preview("animal", widthDp = 200, heightDp = 200)
@Composable
fun AnimalPreview() {
    MyTheme {
        AnimalItem(
            {},
            StubAnimalRepo().getAnimals().first()
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        HomeScreen({ }, StubAnimalRepo().getAnimals())
    }
}
