package com.cdev.kmmsharedui.presentation.userdetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.cdev.kmmsharedui.domain.domain_model.UserDomainModel

data class UserDetailScreen(val userDomainModel: UserDomainModel): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel: UserDetailScreenModel = getScreenModel()

        LaunchedEffect(key1 = Unit) {
            screenModel.onIntent(UserDetailSideEvent.CacheUser(userDomainModel))
        }

        val state by screenModel.state.collectAsState()
        VoucherDetail(state, navigator)
    }
}

@Composable
private fun VoucherDetail(state: UserDetailState, navigator: Navigator) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        when (state) {
            is UserDetailState.Success -> {
                Text(
                    text = state.user.name,
                    style = MaterialTheme.typography.h3
                )
            }

            else -> {}
        }
    }
}