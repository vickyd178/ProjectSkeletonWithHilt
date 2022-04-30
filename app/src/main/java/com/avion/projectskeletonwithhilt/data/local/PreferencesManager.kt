package com.avion.projectskeletonwithhilt.data.local

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.avion.projectskeletonwithhilt.data.model.UserDetails
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext val context: Context) {
    private val Context.dataStore by preferencesDataStore("user_preferences")
    private val dataStore = context.dataStore

    fun getUserDetails(): Flow<UserDetails> = dataStore.data
        .map { preferences ->
            UserDetails(
                id = preferences[PreferenceKeys.USER_ID],
                first_name = preferences[PreferenceKeys.USER_FIRST_NAME],
                last_name = preferences[PreferenceKeys.USER_LAST_NAME],
                email = preferences[PreferenceKeys.USER_EMAIL],
                mobile = preferences[PreferenceKeys.USER_MOBILE_NUMBER],
                profile_image = preferences[PreferenceKeys.USER_PROFILE_IMAGE],
                isCurrentlyLoggedIn = preferences[PreferenceKeys.USER_LOGIN_STATUS],
            )
        }

    suspend fun logout() {
        dataStore.edit {
            it.clear()
        }
        // TODO: Write your Logic to Show logout screen here
    }


    suspend fun saveUserInfo(user: UserDetails) {
        dataStore.edit { preferences ->
            saveInteger(preferences, PreferenceKeys.USER_ID, user.id)
            saveString(preferences, PreferenceKeys.USER_FIRST_NAME, user.first_name)
            saveString(preferences, PreferenceKeys.USER_LAST_NAME, user.last_name)
            saveString(preferences, PreferenceKeys.USER_EMAIL, user.email)
            saveString(preferences, PreferenceKeys.USER_MOBILE_NUMBER, user.mobile)
            saveBoolean(preferences, PreferenceKeys.USER_LOGIN_STATUS, user.isCurrentlyLoggedIn)

        }
    }


    private fun saveBoolean(
        preferences: MutablePreferences,
        pref_key: Preferences.Key<Boolean>,
        value: Boolean?
    ) {
        value?.let {
            preferences[pref_key] = it
        }
    }

    private fun saveString(
        preferences: MutablePreferences,
        pref_key: Preferences.Key<String>,
        value: String?
    ) {
        value?.let { preferences[pref_key] = it }
    }

    private fun saveInteger(
        preferences: MutablePreferences,
        pref_key: Preferences.Key<Int>,
        value: Int?
    ) {
        value?.let {
            preferences[pref_key] = it
        }
    }

    private object PreferenceKeys {
        val USER_ID = intPreferencesKey("user_id")
        val USER_FIRST_NAME = stringPreferencesKey("user_first_name")
        val USER_LAST_NAME = stringPreferencesKey("user_last_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_MOBILE_NUMBER = stringPreferencesKey("mobile")
        val USER_PROFILE_IMAGE = stringPreferencesKey("user_profile_image")
        val USER_LOGIN_STATUS = booleanPreferencesKey("user_login_status")

    }
}