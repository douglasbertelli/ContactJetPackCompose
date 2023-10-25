package com.example.contactjetpackcompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.contactjetpackcompose.constantes.Constants

@Entity(tableName = Constants.TABLE_CONTACT)
data class Contact(
	@ColumnInfo(name = "name") val name: String,
	@ColumnInfo(name = "lastname") val lastname: String,
	@ColumnInfo(name = "age") val age: String,
	@ColumnInfo(name = "phone") val phone: String,
) {
	@PrimaryKey(autoGenerate = true)
	var uid: Int = 0
}
