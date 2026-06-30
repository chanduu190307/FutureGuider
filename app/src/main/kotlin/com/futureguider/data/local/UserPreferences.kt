package com.futureguider.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object Keys {
        val IS_LOGGED_IN   = booleanPreferencesKey("is_logged_in")
        val CURRENT_USER_ID = intPreferencesKey("current_user_id")
        val USER_NAME      = stringPreferencesKey("user_name")
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data.map { it[Keys.IS_LOGGED_IN] ?: false }
    val currentUserId: Flow<Int>  = context.dataStore.data.map { it[Keys.CURRENT_USER_ID] ?: -1 }
    val userName: Flow<String>    = context.dataStore.data.map { it[Keys.USER_NAME] ?: "" }

    suspend fun saveLoginSession(userId: Int, name: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.IS_LOGGED_IN]    = true
            prefs[Keys.CURRENT_USER_ID] = userId
            prefs[Keys.USER_NAME]       = name
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs[Keys.IS_LOGGED_IN]    = false
            prefs[Keys.CURRENT_USER_ID] = -1
            prefs[Keys.USER_NAME]       = ""
        }
    }
}
