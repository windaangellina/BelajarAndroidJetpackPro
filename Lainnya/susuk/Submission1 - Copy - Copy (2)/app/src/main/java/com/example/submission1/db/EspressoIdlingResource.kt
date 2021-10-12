package com.example.submission1.db

import androidx.test.espresso.idling.CountingIdlingResource

class EspressoIdlingResource {
    companion object{
        private const val resource = "GLOBAL"
        val espressoTestIdlingResource = CountingIdlingResource(resource)

        fun increment() {
            espressoTestIdlingResource.increment()
        }

        fun decrement() {
            espressoTestIdlingResource.decrement()
        }

    }
}
