package com.hmju.example

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.hmju.permission.SPermission

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).setOnClickListener {
            SPermission(this)
                .addPermission(Manifest.permission.READ_PHONE_STATE)
                .addPermission(Manifest.permission.CAMERA)
                .addPermission(Manifest.permission.RECORD_AUDIO)
                .addPermission(Manifest.permission.BLUETOOTH)
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