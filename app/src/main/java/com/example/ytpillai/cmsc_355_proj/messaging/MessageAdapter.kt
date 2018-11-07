package com.example.ytpillai.cmsc_355_proj.messaging

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ytpillai.cmsc_355_proj.R
import kotlinx.android.synthetic.main.activity_conversation.view.*
import kotlinx.android.synthetic.main.chat_sent.view.*
import kotlinx.android.synthetic.main.chat_received.view.*


private const val MY_MESSAGE = 1
private const val OTHER_MESSAGE = 2

class MessageAdapter (val context: Context) : RecyclerView.Adapter<MessageViewHolder>() {
    private val messages: ArrayList<Message> = ArrayList()


    fun addMessage(message: Message){
        messages.add(message)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]

        return if(App.nickname == message.nickname) {
            MY_MESSAGE
        }
        else {
            OTHER_MESSAGE
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if(viewType == MY_MESSAGE) {
            MyMessageViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_sent, parent, false))
        } else {
            OtherMessageViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_received, parent, false))
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]

        holder.bind(message)
    }

    inner class MyMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.chatbox
//        private var timeText: TextView = view.timeStamp

        override fun bind(message: Message) {
            messageText.text = message.message
//            timeText.text = DateUtils.fromMillisToTimeString(message.time)
        }
    }

    inner class OtherMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.ChatBodyReceived
        private var userText: TextView = view.UserNickname
//        private var timeText: TextView = view.timeStampReceived

        override fun bind(message: Message) {
            messageText.text = message.message
            userText.text = message.nickname
//            timeText.text = DateUtils.fromMillisToTimeString(message.time)
        }
    }
}

    open class MessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(message:Message) {}
}