package com.example.kotlindevcourse

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

const val USER_DATASTORE = "user_data"

/* Declare a DataStore instance for storing preferences in the context of this application */
val Context.preferenceDataStore : DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

class UserDataStoreManager(val context: Context) {

    companion object{

/*        val USERNAME = stringPreferencesKey("username")
        val NAME = stringPreferencesKey("name")
        val EMAIL = stringPreferencesKey("email")
        val ROLE = stringPreferencesKey("role")
        val DEPARTMENT = stringPreferencesKey("department")
        val DESIGNATION = stringPreferencesKey("designation")
        val ACTION_ITEMS = stringPreferencesKey("actionItems")
        val LATEST_ACTIVITY = stringPreferencesKey("latestActivity")
        val LAST_LOGGED_IN = stringPreferencesKey("lastLoggedIn")
        val LAST_LOGGED_OUT = stringPreferencesKey("lastLoggedOut")*/

        /* Creating a Preference Key "User" for User object as a whole */
        val USER = stringPreferencesKey("user")

    }

    /* Async operation: Saving to data store is an async operation. */
    suspend fun saveToDataStore(user: String){

        Log.d("USERDATASTOREMANAGER - Before .edit", user)

        /* [COMMENTED] */
        /*context.preferenceDataStore.edit {

            it[USERNAME] = user.username
            it[NAME] = user.name
            it[EMAIL] = user.email
            it[ROLE] = user.role
            it[DEPARTMENT] = user.department
            it[DESIGNATION] = user.designation
            it[ACTION_ITEMS] = user.actionItems.toString()
            it[LATEST_ACTIVITY] = user.latestActivity
            it[LAST_LOGGED_IN] = user.lastLoggedIn
            it[LAST_LOGGED_OUT] = user.lastLoggedOut

        }*/

        context.preferenceDataStore.edit {

            it[USER] = user
            Log.d("USERDATASTOREMANAGER - After .edit", it[USER].toString())

        }

    }

    fun getFromDataStore() = context.preferenceDataStore.data.map{

        Log.d("USERDATASTOREMANAGER - In getFromDataStore",it[USER].toString())

        /*User(

            username = it[USERNAME]?:"",
            name = it[NAME]?:"",
            email = it[EMAIL]?:"",
            role = it[ROLE]?:"",
            department = it[DEPARTMENT]?:"",
            designation = it[DESIGNATION]?:"",
            *//* Set this to null meanwhile. Finding ways to define nested objects in Data Store *//*
            actionItems = null,
            latestActivity = it[LATEST_ACTIVITY]?:"",
            lastLoggedIn = it[LAST_LOGGED_IN]?:"",
            lastLoggedOut = it[LAST_LOGGED_OUT]?:""

        )*/

        it[USER]?.let {
            it1 -> Json.decodeFromString<User>(it1)
        }




    }

    suspend fun clearDataStore() = context.preferenceDataStore.edit {

        /*it.clear()*/
        it.remove(USER)

    }



}