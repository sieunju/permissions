package com.hmju.example

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.hmju.permission.SPermission

/**
 * Description :
 *
 * Created by juhongmin on 3/30/24
 */
internal class ExampleFragment : Fragment(R.layout.fragment_example) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button1).setOnClickListener {
            SPermission(requireContext())
                .addPermission(Manifest.permission.CAMERA)
                .addPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .build { isAllGranted, negativePermissions ->
                    startActivity(Intent(requireContext(), ExampleActivity::class.java))
                    requireActivity().finish()
                }
        }
    }
}