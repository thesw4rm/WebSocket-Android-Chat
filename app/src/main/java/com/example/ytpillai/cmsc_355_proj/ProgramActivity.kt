package com.example.ytpillai.cmsc_355_proj

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.ytpillai.cmsc_355_proj.messaging.MessageSocketClient
import com.example.ytpillai.cmsc_355_proj.messaging.MessageSocketServer
import com.example.ytpillai.cmsc_355_proj.services.MessageIntentService
import java.lang.StringBuilder
import java.net.InetSocketAddress


class ProgramActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program)


        val messageServiceIntent = Intent(this, MessageIntentService::class.java)
        messageServiceIntent.action = resources.getString(R.string.ACTION_START)
        this.startService(messageServiceIntent)

        val messageBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("PROGRAM_ACTIVITY", "Something received by program activity: ${intent!!.extras["message"]}")

                if (intent!!.action.equals(resources.getString(R.string.ACTION_RECEIVED_MESSAGE))){
                    StringBuilder().apply {
                        append("Message received: ${intent.extras["message"]}")
                        toString().also { log ->
                            Log.d("PROGRAM_ACTIVITY", "Message received by program activity: ${intent.extras["message"]}")
                            Toast.makeText(context, log, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        }

    }

    /*This is the activity that is loaded after user creates password for the first time or enters correct password*/

    fun isValidIP(view: View) {

        var editTextHello = findViewById(R.id.ipAddress) as EditText

        var check = editTextHello.text.toString()

        var checkarray = check.toCharArray()

        var validChar = false

        for (i in checkarray) {
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
                else -> {
                    validChar = false

                }
            }
            if (validChar == false) {
                break
            }

        }

        if (validChar == false) {
            Toast.makeText(this, "Please Enter Valid IP", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Connecting...", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ConversationActivity::class.java)
            startActivity(intent)
        }


    }


}