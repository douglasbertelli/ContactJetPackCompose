package com.example.contactjetpackcompose.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.contactjetpackcompose.model.Contact

@Dao
interface ContactDao {
	@Insert
	fun saveContact(listContacts: MutableList<Contact>)

	@Query("SELECT * FROM table_contacts ORDER BY name ASC")
	fun getContacts(): MutableList<Contact>

	@Query("UPDATE table_contacts SET name = :newName, lastname = :newLastName, age = :newAge, phone = :newPhone WHERE uid = :id")
	fun updateContact(id: Int, newName: String, newLastName: String, newAge: String, newPhone: String)

	@Query("DELETE FROM table_contacts WHERE uid = :id")
	fun deleleContact(id: Int)

}