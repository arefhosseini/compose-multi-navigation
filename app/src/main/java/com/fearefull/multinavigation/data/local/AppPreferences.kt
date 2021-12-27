package com.fearefull.multinavigation.data.local

import android.content.SharedPreferences
import com.fearefull.multinavigation.data.model.local.User
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AppPreferences(
    private val sharedPrefer: SharedPreferences
) {

    companion object {
        private const val USER_KEY = "user"
        const val PREFERENCES_NAME = "shared_pref"
    }

    var user: User?
        get() = sharedPrefer.getString(USER_KEY, null)?.let { Json.decodeFromString<User>(it) }
        set(value) = value
            ?.let { sharedPrefer.edit().putString(USER_KEY, Json.encodeToString(value)).apply() }
            ?: sharedPrefer.edit().remove(USER_KEY).apply()
}