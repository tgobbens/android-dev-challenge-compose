package com.example.androiddevchallenge.di

import com.example.androiddevchallenge.data.animal.AnimalRepo
import com.example.androiddevchallenge.data.animal.StubAnimalRepo

interface AppContainer {
    val animalRepo: AnimalRepo
}

class AppContainerImpl : AppContainer {
    override val animalRepo: AnimalRepo by lazy {
        StubAnimalRepo()
    }
}