package com.example.androiddevchallenge.ui.detail

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CalendarViewDay
import androidx.compose.material.icons.outlined.Label
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.animal.StubAnimalRepo
import com.example.androiddevchallenge.di.AppContainer
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.ui.theme.*
import com.example.androiddevchallenge.ui.view.AnimalTags
import dev.chrisbanes.accompanist.picasso.PicassoImage

@Composable
fun AnimalDetailScreen(
    animalId: String,
    navController: NavController,
    appContainer: AppContainer,
) {
    val animal = appContainer.animalRepo.getAnimalById(animalId)
    AnimalDetailScreen({ navController.popBackStack() }, animal)
}

@Composable
fun AnimalDetailScreen(
    upPressed: () -> Unit,
    animal: Animal?
) {
    if (animal == null) {
        ErrorMessageContent(message = "not found")
    } else {
        AnimalDetailContent(upPressed, animal = animal)
    }
}

@Composable
fun ErrorMessageContent(
    message: String
) {
    Scaffold(
        topBar = {
            TopAppBar { Text(text = "") }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = message,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp),
                style = typography.h6,
            )
        }
    }
}

@Composable
fun AnimalDetailContent(
    upPressed: () -> Unit,
    animal: Animal
) {
    val scrollState = rememberScrollState()
    val imageBackdropHeight = remember { mutableStateOf(0) }

    val isToolbarTransparent =
        scrollState.value < (imageBackdropHeight.value - with(LocalDensity.current) { 56.dp.toPx() })

    val showAnimalOnFab = scrollState.value > with(LocalDensity.current) { 120.dp.toPx() }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .onSizeChanged {
                    imageBackdropHeight.value = it.height
                }) {

                PicassoImage(
                    data = animal.imageUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )

                AnimalTags(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .wrapContentHeight(),
                    animal = animal
                )
            }

            Text(text = animal.shortDescription, style = typography.h6, modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, top = 24.dp))

            Text(text = animal.description, style = typography.body1, modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 128.dp))
        }

        AnimalDetailAppBar(upPressed, animal, isToolbarTransparent)

        AdoptFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            animal = animal,
            showAnimalOnFab = showAnimalOnFab
        )
    }
}

@Composable
fun AnimalDetailAppBar(upPressed: () -> (Unit), animal: Animal, toolbarTransparent: Boolean) {
    TopAppBar (
        title = { Text(text = animal.name) },
        elevation = if (toolbarTransparent) 0.dp else 4.dp,
        backgroundColor = if (toolbarTransparent) MaterialTheme.colors.primarySurface.copy(alpha = 0.3f) else MaterialTheme.colors.primarySurface,
        contentColor = MaterialTheme.colors.onSurface,
        navigationIcon = {
            IconButton(onClick = { upPressed() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.navigate_up)
                )
            }
        },
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AdoptFloatingActionButton(
    modifier: Modifier,
    animal: Animal,
    showAnimalOnFab: Boolean
) {
    Box(modifier = modifier
        .height(96.dp)
        .wrapContentHeight()
    ) {
        FloatingActionButton(
            onClick = { },
            modifier = Modifier
                .padding(PaddingValues(horizontal = 16.dp, vertical = 0.dp))
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.Center),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary
        ) {
            Text(
                text = stringResource(id = R.string.animal_detail_adopt),
                style = typography.h6,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        AnimatedVisibility(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .padding(8.dp)
                .align(Alignment.CenterEnd),
            initiallyVisible = false,
            visible = showAnimalOnFab,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                PicassoImage(
                    data = animal.imageUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview("errorMessage", widthDp = 200, heightDp = 200)
@Composable
fun PreviewErrorMessage() {
    MyTheme {
        ErrorMessageContent("error message")
    }
}

@Preview("animalDetailScreen", widthDp = 400, heightDp = 600)
@Composable
fun PreviewAnimalDetail() {
    MyTheme {
        AnimalDetailContent(
            {},
            animal = StubAnimalRepo().getAnimals().first()
        )
    }
}