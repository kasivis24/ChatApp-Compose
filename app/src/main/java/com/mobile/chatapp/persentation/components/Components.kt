package com.mobile.chatapp.persentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SnackBarSuccess(snackbarHostState: SnackbarHostState) {


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter){
        SnackbarHost(hostState = snackbarHostState,Modifier.padding(top = 15.dp)) { data ->
            androidx.compose.material3.Snackbar(
                snackbarData = data,
                containerColor = MaterialTheme.colorScheme.primary
            )
        }
    }

}

@Composable
fun SnackBarError(snackbarHostState: SnackbarHostState) {


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter){
        SnackbarHost(hostState = snackbarHostState,Modifier.padding(top = 15.dp)) { data ->
            androidx.compose.material3.Snackbar(
                snackbarData = data,
                containerColor = MaterialTheme.colorScheme.error,
            )
        }
    }

}
