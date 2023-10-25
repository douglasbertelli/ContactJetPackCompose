package com.example.contactjetpackcompose

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactjetpackcompose.constantes.Constants
import com.example.contactjetpackcompose.dao.ContactDao
import com.example.contactjetpackcompose.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

	abstract fun contactDao(): ContactDao

	companion object {
		@Volatile
		private var INSTANCE: AppDatabase? = null

		fun getInstance(context: Context): AppDatabase {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					AppDatabase::class.java,
					Constants.DB_CONTACTS
				).build()

				INSTANCE = instance
				instance
			}
		}
	}

}