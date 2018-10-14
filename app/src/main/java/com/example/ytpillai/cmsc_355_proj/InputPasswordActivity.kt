package com.example.ytpillai.cmsc_355_proj

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils

class InputPasswordActivity : AppCompatActivity() {

    internal lateinit var mPatternLockView: PatternLockView

    internal var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_password)

        val preferences = getSharedPreferences("PREFS", 0)
        password = preferences.getString("password", "0")


        mPatternLockView = findViewById(R.id.pattern_lock_view)
        mPatternLockView.addPatternLockListener(object : PatternLockViewListener {
            override fun onStarted() {

            }

            override fun onProgress(progressPattern: List<PatternLockView.Dot>) {

            }
            /*This is the function that decides what activity will open when the user enters correct password. If you do not
            * want to use ProgramActivity as the activity to go to, then switch the ProgramActivity inside the intent with
             * whatever activity you want.*/
            override fun onComplete(pattern: List<PatternLockView.Dot>) {
                if (password == PatternLockUtils.patternToString(mPatternLockView, pattern)) {
                    val intent = Intent(applicationContext, ProgramActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@InputPasswordActivity, "Wrong password", Toast.LENGTH_SHORT).show()
                    mPatternLockView.clearPattern()
                }
            }

            override fun onCleared() {

            }
        })
    }
}