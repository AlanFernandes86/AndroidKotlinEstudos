package com.example.analize.service.repository.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class SecurityPreferences (context: Context) {

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    private val mPreferences = EncryptedSharedPreferences
        .create(
            "queryShared",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun store(key: String, value: String) {
        mPreferences.edit().putString(key, value).apply()
    }

    fun remove(key: String) {
        mPreferences.edit().remove(key).apply()
    }

    fun get(key: String): String {
        return mPreferences.getString(key, "") ?: ""
    }

}