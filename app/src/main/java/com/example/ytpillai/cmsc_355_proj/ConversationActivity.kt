package com.example.ytpillai.cmsc_355_proj

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.ytpillai.cmsc_355_proj.messaging.App
import com.example.ytpillai.cmsc_355_proj.messaging.Message
import com.example.ytpillai.cmsc_355_proj.messaging.MessageAdapter
import kotlinx.android.synthetic.main.activity_conversation.*
import java.util.*

class ConversationActivity : AppCompatActivity() {

    private lateinit var adapter: MessageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        backArrowBtn.setOnClickListener {
            val intent = Intent(this,Contacts::class.java)
            startActivity(intent)
        }

        chatRecycler.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter(this)
        chatRecycler.adapter = adapter



        sendBtn.setOnClickListener{
            if(chatbox.text != null) {

                val message = Message(App.nickname, chatbox.text.toString(), Calendar.getInstance().timeInMillis)
                adapter.addMessage(message)
                chatRecycler.scrollToPosition(adapter.itemCount - 1)
            }
        }



    }


}
