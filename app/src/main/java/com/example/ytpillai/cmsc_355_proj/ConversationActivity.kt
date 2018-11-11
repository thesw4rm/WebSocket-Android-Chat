package com.example.ytpillai.cmsc_355_proj

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.example.ytpillai.cmsc_355_proj.messaging.*
import kotlinx.android.synthetic.main.activity_conversation.*
import java.util.*

private const val TAG = "ConversationActivity"

class ConversationActivity : AppCompatActivity() {


    private lateinit var adapter: MessageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        backArrowBtn.setOnClickListener {
            val intent = Intent(this,ContactsActivity::class.java)
            startActivity(intent)
            finish()
        }

        chatRecycler.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter(this)
        chatRecycler.adapter = adapter

        val chatHeader: TextView = findViewById(R.id.nicknameHeader)
        chatHeader.text = App.nickname


        sendBtn.setOnClickListener{
            if(chatbox.text.isNotEmpty()) {

                sendMessage()
                resetInput()

/*              val intent = intent
                val nameOfFriend = intent.getStringExtra(ProgramActivity.EXTRA_MESSAGE)
                var nickname = nameOfFriend*/
            }
            else{
                Toast.makeText(this, "Please enter text to send", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun sendMessage(){

        Me.ip = "0"
        val message = Message(
//                App.nickname,
                Me.ip,
                chatbox.text.toString(),
                Calendar.getInstance().timeInMillis
        )

        //Log.e(TAG, message.toString())

        runOnUiThread {
            adapter.addMessage(message)
            chatRecycler.scrollToPosition(adapter.itemCount - 1)
        }

    }

    private fun receiveMessage(){
        val message = Message(
                App.ip,
                 "testing",
                Calendar.getInstance().timeInMillis
        )

        runOnUiThread {
            adapter.addMessage(message)
            chatRecycler.scrollToPosition(adapter.itemCount - 1)
        }

        TODO("Somehow be able to receive messages")

    }


    private fun resetInput() {
        // Clean text box
        chatbox.text.clear()

        // Hide keyboard
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

}
