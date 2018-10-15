package com.example.ytpillai.cmsc_355_proj

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils

class CreatePasswordActivity : AppCompatActivity() {

    internal lateinit  var mPatternLockView: PatternLockView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)

        mPatternLockView = findViewById(R.id.pattern_lock_view)

        mPatternLockView.addPatternLockListener(object : PatternLockViewListener {
            override fun onStarted() {

            }

            override fun onProgress(progressPattern: List<PatternLockView.Dot>) {

            }

            override fun onComplete(pattern: List<PatternLockView.Dot>) {
                val preferences = getSharedPreferences("PREFS", 0)
                val editor = preferences.edit()
                editor.putString("password", PatternLockUtils.patternToString(mPatternLockView, pattern))
                editor.apply()

                val intent = Intent(applicationContext, ProgramActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onCleared() {

            }
        })
    }
}