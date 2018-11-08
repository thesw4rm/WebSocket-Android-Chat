package com.example.ytpillai.cmsc_355_proj

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.ytpillai.cmsc_355_proj.messaging.App
import com.example.ytpillai.cmsc_355_proj.messaging.ChatService
import com.example.ytpillai.cmsc_355_proj.messaging.Message
import com.example.ytpillai.cmsc_355_proj.messaging.MessageAdapter
import kotlinx.android.synthetic.main.activity_conversation.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private const val TAG = "ConversationActivity"

class ConversationActivity : AppCompatActivity() {


//    val messageObj = Message("balls", "ball")

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



        sendBtn.setOnClickListener{
            if(chatbox.text.isNotEmpty()) {
                val message = Message(
                        App.nickname,
                        chatbox.text.toString()
//                        Calendar.getInstance().timeInMillis
                        )

                val call = ChatService.create().postMessage(message)

                call.enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        resetInput()
                        if (!response.isSuccessful) {
                            Log.e(TAG, response.code().toString());
                            Toast.makeText(applicationContext,"Response was not successful", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        resetInput()
                        Log.e(TAG, t.toString())
                        Toast.makeText(applicationContext,"Error when calling the service", Toast.LENGTH_SHORT).show()
                    }
                })

                val intent = intent
                val nameOfFriend = intent.getStringExtra(ProgramActivity.EXTRA_MESSAGE)

                var nickname = nameOfFriend

                sendMessage(nickname, chatbox.text.toString())
                resetInput()


            }




        }


    }

    private fun resetInput() {
        // Clean text box
        chatbox.text.clear()

        // Hide keyboard
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    private fun sendMessage(nickname: String, message: String){

//        val message = Message("nickname", "message", "time".toLong())

        val message = Message(nickname, message)

        Log.e(TAG, message.toString())

        runOnUiThread {
            adapter.addMessage(message)
            chatRecycler.scrollToPosition(adapter.itemCount - 1)
        }

    }

}
