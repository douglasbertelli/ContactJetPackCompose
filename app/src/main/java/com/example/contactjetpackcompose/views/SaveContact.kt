package com.example.contactjetpackcompose.views

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.contactjetpackcompose.AppDatabase
import com.example.contactjetpackcompose.components.ButtonCustom
import com.example.contactjetpackcompose.components.OutlinedTextFielCustom
import com.example.contactjetpackcompose.dao.ContactDao
import com.example.contactjetpackcompose.model.Contact
import com.example.contactjetpackcompose.ui.theme.Purple500
import com.example.contactjetpackcompose.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contactDao: ContactDao

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveContact(navController: NavController) {
	val listContact: MutableList<Contact> = mutableListOf()
	val scope = rememberCoroutineScope()

	var nome by remember { mutableStateOf("") }
	var sobrenome by remember { mutableStateOf("") }
	var idade by remember { mutableStateOf("") }
	var celular by remember { mutableStateOf("") }

	val context = LocalContext.current

	Scaffold(
		topBar = {
			TopAppBar(
				title = {
					Text(
						text = "Criar novo contato",
						fontSize = 18.sp,
						color = White
					)
				},
				colors = TopAppBarDefaults.smallTopAppBarColors(
					containerColor = Purple500
				)
			)
		}
	) {
		Column(
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
				.fillMaxSize()
				.padding(20.dp)
				.verticalScroll(rememberScrollState())
		) {
			OutlinedTextFielCustom(
				value = nome,
				label = { Text(text = "Nome") },
				onValueChange = { nome = it },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
			)

			OutlinedTextFielCustom(
				value = sobrenome,
				label = { Text(text = "Sobrenome") },
				onValueChange = { sobrenome = it },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
			)

			OutlinedTextFielCustom(
				value = idade,
				label = { Text(text = "Idade") },
				onValueChange = { idade = it },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
			)

			OutlinedTextFielCustom(
				value = celular,
				label = { Text(text = "Celular") },
				onValueChange = { celular = it },
				keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
			)

			ButtonCustom(
				text = "Salvar",
				onClick = {
					var message = false

					scope.launch(Dispatchers.IO) {
						if (nome.isEmpty() || sobrenome.isEmpty() || idade.isEmpty() || celular.isEmpty()) {
							message = false
						} else {
							message = true

							val contact = Contact(nome, sobrenome, idade, celular)
							listContact.add(contact)

							contactDao = AppDatabase.getInstance(context).contactDao()
							contactDao.saveContact(listContact)
						}
					}

					scope.launch(Dispatchers.Main) {
						if (message) {
							Toast.makeText(context, "Usuários salvo com sucesso", Toast.LENGTH_SHORT).show()
							navController.popBackStack()
						} else {
							Toast.makeText(context, "Preencha os campos.", Toast.LENGTH_LONG).show()
						}
					}

				}
			)
		}
	}
}

@Preview
@Composable
private fun PreviewLayout() {
	SaveContact(navController = rememberNavController())
}