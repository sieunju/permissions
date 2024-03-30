package com.hmju.permission.internal

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.hmju.permission.SPermissions

/**
 * Description : 권한 Activity
 *
 * Created by juhongmin on 3/30/24
 */
internal class PermissionsActivity : AppCompatActivity() {

    /**
     * 권한 팝업 노출후 결과값 받는 부분
     */
    private val permissionsResult = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { handleCallback() }

    private val requestPermissions: Array<String>? by lazy {
        intent.getStringArrayExtra(SPermissions.extraCode)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        permissionsResult.launch(requestPermissions)
    }

    /**
     * SimplePermissions 클래스에 리스너 전달 처리 함수
     */
    private fun handleCallback() {
        finish()
        Handler(Looper.getMainLooper()).post {
            SPermissions.listener?.callback()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }
}