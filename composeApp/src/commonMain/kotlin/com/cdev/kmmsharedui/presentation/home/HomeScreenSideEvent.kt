package com.cdev.kmmsharedui.presentation.home

sealed class HomeScreenSideEvent {
    data object GetUsers : HomeScreenSideEvent()
}