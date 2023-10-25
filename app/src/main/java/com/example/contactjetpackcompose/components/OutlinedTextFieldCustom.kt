package com.example.contactjetpackcompose.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.contactjetpackcompose.ui.theme.Gray200
import com.example.contactjetpackcompose.ui.theme.Purple500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFielCustom(
	value: String,
	label: @Composable () -> Unit,
	keyboardOptions: KeyboardOptions,
	onValueChange: (String) -> Unit,
) {
	OutlinedTextField(
		value,
		onValueChange,
		label = label,
		keyboardOptions = keyboardOptions,
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 10.dp),
		maxLines = 1,
		colors = TextFieldDefaults.outlinedTextFieldColors(
			cursorColor = Purple500,
			focusedBorderColor = Purple500,
			focusedLabelColor = Purple500,
			unfocusedBorderColor = Gray200
		)
	)

}
