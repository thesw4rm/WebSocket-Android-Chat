package com.example.ytpillai.cmsc_355_proj

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast

import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils
import com.kevalpatel2106.fingerprintdialog.AuthenticationCallback
import com.kevalpatel2106.fingerprintdialog.FingerprintDialogBuilder


class CreatePasswordActivity : AppCompatActivity() {

    internal lateinit var mPatternLockView: PatternLockView

    val dialogBuilder = FingerprintDialogBuilder(this)
            .setTitle("Inkko"/* Title of the fingerprint dialog */)
            .setSubtitle("Fingerprint Authenticator"/* Subtitle of the fingerprint dialog */)
            .setDescription("Touch your fingerprint scanner to enter the app. Or tap cancel to use a pattern lock instead."/* Description of the fingerprint dialog */)
            .setNegativeButton("Cancel"/* Negative button of the fingerprint dialog */)

    val callback = object : AuthenticationCallback {

        override fun fingerprintAuthenticationNotSupported() {
            // Device doesn't support fingerprint authentication. May be device doesn't have fingerprint hardware or device is running on Android below Marshmallow.
            // Switch to alternate authentication method.
        }

        override fun hasNoFingerprintEnrolled() {
            // User has no fingerprint enrolled.
            // Application should redirect the user to the lock screen settings.
            // FingerprintUtils.openSecuritySettings(this@SecureActivity)
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
            // Unrecoverable error. Cannot use fingerprint scanner. Library will stop scanning for the fingerprint after this callback.
            // Switch to alternate authentication method.
        }

        override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
            // Authentication process has some warning. such as "Sensor dirty, please clean it."
            // Handle it if you want. Library will continue scanning for the fingerprint after this callback.
        }

        override fun authenticationCanceledByUser() {
            // User canceled the authentication by tapping on the cancel button (which is at the bottom of the dialog).
        }

        override fun onAuthenticationSucceeded() {
            // Authentication success
            // Your user is now authenticated.

            val intent = Intent(applicationContext, ProgramActivity::class.java)
            startActivity(intent)
            finish()

            Log.d("balls", "YOU HAVE BEEN AUTHENTICATED")
        }

        override fun onAuthenticationFailed() {
            // Authentication failed.
            // Library will continue scanning the fingerprint after this callback.

            Log.d("balls", "WRONG FINGER")
        }
    }

    internal var x: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_password)

        dialogBuilder.show(supportFragmentManager, callback)

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
                    finish() }

                else if(x == null){
                    Toast.makeText(this@CreatePasswordActivity, "Confirm Pattern", Toast.LENGTH_SHORT).show()
                    x = PatternLockUtils.patternToString(mPatternLockView, pattern)
                    mPatternLockView.clearPattern()

                }
                else {
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