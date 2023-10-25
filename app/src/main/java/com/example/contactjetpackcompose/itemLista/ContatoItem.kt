package com.example.contactjetpackcompose.itemLista

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.contactjetpackcompose.AppDatabase
import com.example.contactjetpackcompose.R
import com.example.contactjetpackcompose.dao.ContactDao
import com.example.contactjetpackcompose.model.Contact
import com.example.contactjetpackcompose.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var contactDao : ContactDao

@Composable
fun ContatoItem(
	navController: NavController,
	postion: Int,
	listContacts: MutableList<Contact>,
	context: Context
) {

	val scope = rememberCoroutineScope()

	val name = listContacts[postion].name
	val lastName = listContacts[postion].lastname
	val age = listContacts[postion].age
	val phone = listContacts[postion].phone
	val uid = listContacts[postion].uid

	val contact = listContacts[postion]

	fun alertDialogDeleteContact() {
		val alertDialog = AlertDialog.Builder(context)
		alertDialog.setTitle("Deseja Excluir?").setMessage("Tem certeza?")
		alertDialog.setPositiveButton("OK"){_,_ ->
			scope.launch(Dispatchers.IO) {
				contactDao = AppDatabase.getInstance(context).contactDao()
				contactDao.deleleContact(uid)

				listContacts.remove(contact)
			}

			scope.launch(Dispatchers.Main) {
				navController.navigate("listContact")
				Toast.makeText(context, "Contato deletado com sucesso", Toast.LENGTH_SHORT).show()
			}

		}
		alertDialog.setNegativeButton("Cancelar") {_,_ ->

		}
		alertDialog.show()

	}

	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(
				horizontal = 10.dp,
				vertical = 5.dp
			),
		colors = CardDefaults.cardColors(
			containerColor = White
		),
		elevation = CardDefaults.cardElevation(
			defaultElevation = 8.dp
		)
	) {
		ConstraintLayout(
			modifier = Modifier.padding(20.dp)
		) {
			val (txtNome, txtIdade, txtCelular, btnAtualizar, btnDeletar) = createRefs()

			Text(
				text = "Contato: $name $lastName",
				fontSize = 18.sp,
				color = Color.Black,
				modifier = Modifier.constrainAs(txtNome) {
					top.linkTo(parent.top)
					start.linkTo(parent.start)
				}
			)

			Text(
				text = "Idade: $age",
				fontSize = 18.sp,
				color = Color.Black,
				modifier = Modifier.constrainAs(txtIdade) {
					top.linkTo(txtNome.bottom)
					start.linkTo(parent.start)
				}
			)

			Text(
				text = "Celular: $phone",
				fontSize = 18.sp,
				color = Color.Black,
				modifier = Modifier.constrainAs(txtCelular) {
					top.linkTo(txtIdade.bottom)
					start.linkTo(parent.start)
				}
			)

			IconButton(
				onClick = {
									navController.navigate("editContact/${uid}")
				},
				modifier = Modifier
					.constrainAs(btnAtualizar) {
						start.linkTo(txtCelular.end, margin = 40.dp)
						top.linkTo(parent.top, margin = 35.dp)
					}
			) {
				Image(
					imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
					contentDescription = "Editar Informações",
					modifier = Modifier.size(30.dp)
				)
			}

			IconButton(
				onClick = { alertDialogDeleteContact() },
				modifier = Modifier
					.constrainAs(btnDeletar) {
						start.linkTo(btnAtualizar.end)
						top.linkTo(parent.top, margin = 35.dp)
					}
			) {
				Image(
					imageVector = ImageVector.vectorResource(R.drawable.ic_delelte),
					contentDescription = "Editar Informações",
					modifier = Modifier.size(30.dp)
				)
			}
		}
	}
}
