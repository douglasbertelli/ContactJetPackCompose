package com.example.contactjetpackcompose.components

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contactjetpackcompose.ui.theme.Purple500

@Composable
fun ButtonCustom (
	text: String,
	onClick: () -> Unit,
) {
	Button(
		onClick,
		modifier = Modifier.fillMaxWidth(),
		shape = RoundedCornerShape(4.dp),
		colors = ButtonDefaults.buttonColors(
			containerColor = Purple500
		)
	) {
		Text(text = text)
	}
}

@Preview
@Composable
private fun PreviewLayout () {
	//ButtonCustom()
}