package com.example.ytpillai.cmsc_355_proj

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.ytpillai.cmsc_355_proj.messaging.App
import com.example.ytpillai.cmsc_355_proj.services.MessageServerService


class ProgramActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program)

        val messageServiceIntent = Intent(this, MessageServerService::class.java)
        stopService(messageServiceIntent)
        this.startService(messageServiceIntent)


        /*val messageServerServiceIntent = Intent(this, MessageServerService::class.java)
        startService(messageServerServiceIntent)*/
    }

    /*This is the activity that is loaded after user creates password for the first time or enters correct password*/

    fun isValidIP(view: View) {

        val getNickname = findViewById<EditText>(R.id.nickname)

        val getIP = findViewById<EditText>(R.id.ipAddress)

        val otherUser = getNickname.text.toString()

        val check = getIP.text.toString()
        Log.e("CHECK", check)
        val checkarray = check.toCharArray()

        var validChar = true //false

        /*for (i in checkarray) {
            when (i) {
                '0' -> validChar = true
                '1' -> validChar = true
                '2' -> validChar = true
                '3' -> validChar = true
                '4' -> validChar = true
                '5' -> validChar = true
                '6' -> validChar = true
                '7' -> validChar = true
                '8' -> validChar = true
                '9' -> validChar = true
                '.' -> validChar = true
                ':' -> validChar = true
                else -> {
                    validChar = false

                }
            }
            if (validChar == false) {
                break
            }

        }
*/
        if (!validChar) {
            Toast.makeText(this, "Please Enter Valid IP", Toast.LENGTH_SHORT).show()
            //TODO: remove this from the validation ting

        } else {
            Toast.makeText(this, "Connecting...", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ConversationActivity::class.java)
            App.nickname = otherUser
            App.ip = check
//            intent.putExtra(EXTRA_MESSAGE, nameOfFriend)
            startActivity(intent)
            finish()
        }


    }

/*    companion object {
        val EXTRA_MESSAGE = "Friend"
    }*/


}