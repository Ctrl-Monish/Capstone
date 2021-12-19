package com.projects.capstonear

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tagId: String? = null
    private var loginButton: Button? = null

    private val loginOnClickListener = View.OnClickListener {
        Toast.makeText(baseContext, "Let's T.U.R.N", Toast.LENGTH_SHORT).show()
        tagId = R.string.intent_tag_id.toString()
        tagId?.let { safeTagId ->
            Place.fetchPlaces(this, safeTagId, true)
        }
    }

    private val placeListener = PlaceListener { places ->
        if (places != null) {
            val intent = Intent(baseContext, ArActivity::class.java).apply {
                putExtra(getString(R.string.intent_tag_id), tagId)
            }
            startActivity(intent)
        } else {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton = findViewById(R.id.loginButton)
    }

    override fun onResume() {
        super.onResume()
        loginButton?.setOnClickListener(loginOnClickListener)
        PlaceListener.register(placeListener)
    }

    override fun onPause() {
        super.onPause()
        PlaceListener.unregister(placeListener)
    }
}