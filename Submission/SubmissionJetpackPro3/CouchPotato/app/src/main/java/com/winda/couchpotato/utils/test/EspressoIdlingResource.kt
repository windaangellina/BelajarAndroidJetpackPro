package com.winda.couchpotato.utils.test

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