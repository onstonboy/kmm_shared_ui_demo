package com.cdev.kmmsharedui.presentation.home

sealed interface HomeScreenSideEvent {
    data object GetUsers : HomeScreenSideEvent
}