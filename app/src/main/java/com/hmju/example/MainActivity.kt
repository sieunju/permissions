package com.hmju.example

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.hmju.permission.SPermission
import com.hmju.permission.isAllGranted

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val permissions = listOf(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (isAllGranted(permissions)) {
            Log.d("JLOGGER","모두 동의")
        }

        findViewById<Button>(R.id.button1).setOnClickListener {
            SPermission(this)
                .addPermissions(permissions)
                .build { isAllGranted, resultPermissionMap ->
                    Log.d(
                        "JLogger",
                        "모두 권한 승인 $isAllGranted\t결과값 권한 $resultPermissionMap"
                    )
                }
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, ExampleActivity::class.java))
        }
    }
}