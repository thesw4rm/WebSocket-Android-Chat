package com.example.ytpillai.cmsc_355_proj

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils

class CreatePasswordActivity : AppCompatActivity() {

    internal lateinit var mPatternLockView: PatternLockView

    internal var x: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)

        val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        mPatternLockView = findViewById(R.id.pattern_lock_view)

        mPatternLockView.addPatternLockListener(object : PatternLockViewListener {
            override fun onStarted() {

            }

            override fun onProgress(progressPattern: List<PatternLockView.Dot>) {

            }

            override fun onComplete(pattern: List<PatternLockView.Dot>) {

                if (x == PatternLockUtils.patternToString(mPatternLockView, pattern)) {
                    val intent = Intent(applicationContext, ProgramActivity::class.java)
                    val preferences = getSharedPreferences("PREFS", 0)
                    val editor = preferences.edit()
                    editor.putString("password", PatternLockUtils.patternToString(mPatternLockView, pattern))
                    editor.apply()
                    startActivity(intent)
                    finish()
                } else if (x == null) {
                    Toast.makeText(this@CreatePasswordActivity, "Confirm Pattern", Toast.LENGTH_SHORT).show()
                    x = PatternLockUtils.patternToString(mPatternLockView, pattern)
                    mPatternLockView.clearPattern()

                } else {
                    vibratorService.vibrate(100)
                    Toast.makeText(this@CreatePasswordActivity, "Pattern not match, try again", Toast.LENGTH_SHORT).show()
                    x = null
                    mPatternLockView.clearPattern()
                }
                mPatternLockView.clearPattern()
            }

            override fun onCleared() {

            }
        })

    }

    fun skipPassword(view: View) {
        val intent2 = Intent(this, ProgramActivity::class.java)
        startActivity(intent2)
        finish()
    }
}