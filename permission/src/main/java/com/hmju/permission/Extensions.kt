package com.hmju.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.isGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

fun Context.isAllGranted(list: List<String>): Boolean {
    list.forEach {
        if (!isGranted(it)) {
            return false
        }
    }
    return true
}

fun Context.isDeniedPermissions(list: List<String>): List<String> {
    return list.filterNot { isGranted(it) }
}
