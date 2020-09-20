package com.ard.joinprint

import android.content.Context
import android.content.SharedPreferences

class PrefManager(name: String?, var context: Context) {
    var pref: SharedPreferences
    var editor: SharedPreferences.Editor

    // shared pref mode
    var PRIVATE_MODE = 0
    fun setToken(accessToken: String?, refreshToken: String?) {
        editor.putString("accessToken", accessToken)
        editor.putString("refreshToken", refreshToken)
        editor.commit()
    }

    val accessToken: String?
        get() = pref.getString("accessToken", "")
    val refreshToken: String?
        get() = pref.getString("refreshToken", "")
    var isLogin: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)
        set(login) {
            editor.putBoolean(IS_LOGIN, login)
            editor.commit()
        }

    companion object {
        private const val IS_LOGIN = "IsLogin"
    }

    init {
        pref = context.getSharedPreferences(name, PRIVATE_MODE)
        editor = pref.edit()
    }
}