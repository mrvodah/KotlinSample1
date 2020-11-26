package com.example.kotlin

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent != null && intent.hasExtra("isGoDynamic")) {
            Log.d(TAG, "onCreate: has isGoDynamic")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            setupShortcut()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    private fun setupShortcut() {
        val appContext = applicationContext

        val shortcutManager = getSystemService(ShortcutManager::class.java)

        val shortcut = ShortcutInfo.Builder(appContext, "shortcut")
            .setShortLabel("Shortcut")
            .setLongLabel("Open the Shortcut")
            .setIcon(Icon.createWithResource(appContext, R.drawable.ic_baseline_adjust_24))
            .setIntent(
                Intent().apply {
                    action = Intent.ACTION_VIEW
                    setClassName(packageName, MainActivity::class.java.name)
                    putExtra("isGoDynamic", true)
                }
            )
            .build()

        shortcutManager!!.dynamicShortcuts = listOf(shortcut)

        Log.d(TAG, "setupShortcut: nothing special")
    }
}