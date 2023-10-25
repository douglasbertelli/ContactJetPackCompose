package com.example.contactjetpackcompose.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.contactjetpackcompose.AppDatabase
import com.example.contactjetpackcompose.R
import com.example.contactjetpackcompose.dao.ContactDao
import com.example.contactjetpackcompose.itemLista.ContatoItem
import com.example.contactjetpackcompose.model.Contact
import com.example.contactjetpackcompose.ui.theme.Purple500
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contactDao: ContactDao

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContact(navController: NavController) {
	val listContacts: MutableList<Contact> = mutableListOf()
	val context = LocalContext.current
	val scope = rememberCoroutineScope()

	scope.launch(Dispatchers.IO) {
		contactDao = AppDatabase.getInstance(context).contactDao()
		val contacts = contactDao.getContacts()

		for (contact in contacts) {
			listContacts.add(contact)
		}

	}

	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text(
						text = "Lista de contato",
						fontSize = 18.sp,
						color = Color.White
					)
				},
				colors = TopAppBarDefaults.smallTopAppBarColors(
					containerColor = Purple500
				)
			)
		},
		floatingActionButton = {
			FloatingActionButton(
				onClick = {
					navController.navigate("saveContact")
				},
				containerColor = Purple500
			) {
				Icon(
					imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
					contentDescription = "Adicionar um novo contato",
					tint = Color.White
				)
			}
		}
	) {
		LazyColumn(
			modifier = Modifier.padding(10.dp, 80.dp, 10.dp, 0.dp).fillMaxHeight()
		) {
			itemsIndexed(listContacts) { position, item ->
				ContatoItem(navController, position, listContacts, context)
			}
		}
	}
}
