package com.hmju.permission

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.hmju.permission.internal.PermissionsActivity
import com.hmju.permission.internal.PermissionsListener

/**
 * Description : 간단하게 권한에 대해 처리할수 있는 SPermissions Class
 *
 * Created by juhongmin on 3/30/24
 */
@Suppress("unused")
class SPermission {

    private val context: Context

    constructor(context: Context) {
        this.context = context
    }

    constructor(fragment: Fragment) {
        this.context = fragment.requireContext()
    }

    constructor(activity: Activity) {
        this.context = activity.baseContext
    }

    companion object {
        internal var listener: PermissionsListener? = null
        internal val extraCode = "a"
    }

    private val requestPermissions: MutableSet<String> = mutableSetOf()

    fun addPermission(permission: String): SPermission {
        requestPermissions.add(permission)
        return this
    }

    /**
     * Request Permissions
     * @param permissions Manifest.CAMERA, Manifest.STORAGE ...
     */
    fun addPermissions(permissions: List<String>): SPermission {
        requestPermissions.addAll(permissions)
        return this
    }

    /**
     * 껍데기인 권한 액티비티 실행 함수
     * @param negativePermissions 거부된 권한들
     */
    private fun movePermissionsActivity(negativePermissions: List<String>) {
        Intent(context, PermissionsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra(extraCode, negativePermissions.toTypedArray())
            context.startActivity(this)
        }
    }

    /**
     * Permission Check Start
     */
    fun build(callback: (Boolean, Map<String, Boolean>) -> Unit) {
        if (requestPermissions.isEmpty()) throw NullPointerException("권한은 필수 값입니다.")
        // 권한 거부인것들만 권한 팝업 처리하도록
        val negativePermissions = context.isDeniedPermissions(requestPermissions.toList())
        if (negativePermissions.isEmpty()) {
            callback(true, mapOf())
        } else {
            movePermissionsActivity(negativePermissions)

            // Single Listener
            if (listener != null) listener = null

            listener = object : PermissionsListener {
                override fun callback() {
                    val resultPermissions = hashMapOf<String, Boolean>()
                    var isAllGrated = true
                    requestPermissions.forEach {
                        val isGranted = context.isGranted(it)
                        resultPermissions[it] = isGranted
                        // 하나라도 거부된 경우
                        if (!isGranted) {
                            isAllGrated = false
                        }
                    }

                    callback(isAllGrated, resultPermissions)
                    listener = null
                }
            }
        }
    }
}