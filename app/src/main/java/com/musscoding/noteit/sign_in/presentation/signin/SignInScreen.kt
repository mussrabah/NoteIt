package com.musscoding.noteit.sign_in.presentation.signin

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.musscoding.noteit.R

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    signInState: SignInState,
    viewModel: SignInViewModel = hiltViewModel<SignInViewModel>(),
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = signInState.signInError,) {
        signInState.signInError?.let {error ->
            Toast.makeText(
                    context,
                    error,
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            Log.d("Acc", "clicked Sign In")
            onSignInClick()
        }) {
            Text(text = stringResource(R.string.sign_in))
        }
    }
}