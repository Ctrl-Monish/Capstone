package com.projects.capstonear

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.app.ActivityCompat


class ArActivity : AppCompatActivity() {

    private var arOverlayView: ArOverlayView? = null

    private var cameraHandler: CameraHandler? = null
    private var locationHandler: LocationHandler? = null
    private var rotationHandler: RotationHandler? = null
    private var refreshHandler: RefreshHandler? = null

    private var backButton: AppCompatImageButton? = null

    private val backOnClickListener = View.OnClickListener { finish() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        arOverlayView = ArOverlayView(this)
        cameraHandler = CameraHandler(this)
        locationHandler = LocationHandler(this)
        rotationHandler = RotationHandler(this)
        refreshHandler = RefreshHandler(this)

        backButton = findViewById(R.id.backButton)
    }

    override fun onResume() {
        super.onResume()

        arOverlayView?.start()
        cameraHandler?.start()
        locationHandler?.start()
        rotationHandler?.start()
        refreshHandler?.start()

        backButton?.setOnClickListener(backOnClickListener)
    }

    override fun onPause() {
        super.onPause()

        arOverlayView?.stop()
        locationHandler?.stop()
        refreshHandler?.stop()
    }


    private fun checkPermission(type: String): Boolean {
        val permission = ActivityCompat.checkSelfPermission(this, type)
        return permission == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CameraHandler.CAMERA_REQUEST_CODE_PERMISSIONS -> {
                if (CameraHandler.CAMERA_REQUIRED_PERMISSIONS.all { checkPermission(it) }) {
                    cameraHandler?.start()
                } else {
                    Toast.makeText(
                        this,
                        "Camera permission not granted",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }

            LocationHandler.LOCATION_REQUEST_CODE_PERMISSIONS -> {
                if (LocationHandler.LOCATION_REQUIRED_PERMISSIONS.all { checkPermission(it) }) {
                    locationHandler?.start()
                } else {
                    Toast.makeText(
                        this,
                        "Location permission not granted",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
            }
        }
    }
}