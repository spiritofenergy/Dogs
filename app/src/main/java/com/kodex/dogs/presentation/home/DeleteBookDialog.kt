package com.kodex.dogs.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.kodex.dogs.R
import com.kodex.dogs.ui.dialods.SettingsRestartDialog

@Composable
fun DeleteBookDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    SettingsRestartDialog(
        showDialog = true,
        onDismiss = onDismiss,
        title = stringResource(id = R.string.attention),
        message = stringResource(id = R.string.want_to_delete_this_message),
        onConfirm = onConfirm
    )
}