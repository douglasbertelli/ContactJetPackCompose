package com.example.contactjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.contactjetpackcompose.ui.theme.ContactJetPackComposeTheme
import com.example.contactjetpackcompose.views.EditContact
import com.example.contactjetpackcompose.views.ListContact
import com.example.contactjetpackcompose.views.SaveContact

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ContactJetPackComposeTheme {
				val navController = rememberNavController()

				NavHost(navController = navController, startDestination = "listContact") {
					composable("listContact") {
						ListContact(navController)
					}

					composable("saveContact") {
						SaveContact(navController)
					}

					composable(
						"editContact/{uid}",
						arguments = listOf(navArgument("uid"){})
					) {
						EditContact(navController, it.arguments?.getString("uid").toString())
					}
				}
			}
		}
	}
}
