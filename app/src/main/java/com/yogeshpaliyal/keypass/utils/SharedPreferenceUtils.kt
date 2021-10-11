package com.yogeshpaliyal.keypass.utils

import android.content.SharedPreferences
import androidx.core.content.edit

/*
* @author Yogesh Paliyal
* techpaliyal@gmail.com
* https://techpaliyal.com
* created on 21-02-2021 11:18
*/

/**
 * Pair
 * 1st => true if key is created now & false if key is created previously
 *
 */
fun getOrCreateBackupKey(sp: SharedPreferences, reset: Boolean = false): Pair<Boolean, String> {

    return if (sp.contains(BACKUP_KEY) && reset.not()) {
        Pair(false, sp.getString(BACKUP_KEY, "") ?: "")
    } else {
        val randomKey = getRandomString(16)
        sp.edit {
            putString(BACKUP_KEY, randomKey)
        }
        Pair(true, randomKey)
    }
}

fun clearBackupKey(sp: SharedPreferences) {

    sp.edit {
        remove(BACKUP_KEY)
    }
}

fun setBackupDirectory(sp: SharedPreferences, string: String) {
    sp.edit {
        putString(BACKUP_DIRECTORY, string)
    }
}

fun setBackupTime(sp: SharedPreferences, time: Long) {
    sp.edit {
        putLong(BACKUP_DATE_TIME, time)
    }
}

fun getBackupDirectory(sp: SharedPreferences,): String {
    return sp.getString(BACKUP_DIRECTORY, "") ?: ""
}

fun SharedPreferences?.isAutoBackupEnabled(): Boolean {
    return this?.getBoolean(AUTO_BACKUP, false) ?: false
}

fun SharedPreferences?.overrideAutoBackup(): Boolean {
    return this?.getBoolean(OVERRIDE_AUTO_BACKUP, false) ?: false
}

fun SharedPreferences?.setOverrideAutoBackup(value: Boolean) {
    this?.edit {
        putBoolean(OVERRIDE_AUTO_BACKUP, value)
    }
}

fun SharedPreferences?.setAutoBackupEnabled(value: Boolean) {
    this?.edit {
        putBoolean(AUTO_BACKUP, value)
    }
}

fun getBackupTime(sp: SharedPreferences,): Long {
    return sp.getLong(BACKUP_DATE_TIME, -1) ?: -1L
}

private const val BACKUP_KEY = "backup_key"
private const val BACKUP_DIRECTORY = "backup_directory"
private const val BACKUP_DATE_TIME = "backup_date_time"
private const val AUTO_BACKUP = "auto_backup"
private const val OVERRIDE_AUTO_BACKUP = "override_auto_backup"
