package com.example.ytpillai.cmsc_355_proj.messaging

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ytpillai.cmsc_355_proj.R
import kotlinx.android.synthetic.main.chat_sent.view.*
import kotlinx.android.synthetic.main.chat_received.view.*

private const val MY_MESSAGE = 2
private const val OTHER_MESSAGE = 1

private const val TAG = "Adapter"

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

        return if(App.ip == message.ip) {

            OTHER_MESSAGE
//            Log.i(TAG,"Their nickname was called: " + App.nickname)
        }
        else {

            MY_MESSAGE
//            Log.i(TAG,"My nickname was called: " + Me.nickname)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if(viewType == MY_MESSAGE) {
            Log.i(TAG, "The viewType is: $viewType")
            MyMessageViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_sent, parent, false))
        } else {
            OtherMessageViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_received, parent, false))
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
//      viewHolder.getTextView().setText(mDataSet.get(getItemCount() - 1 -position));

        holder.bind(message)
    }

    inner class MyMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.ChatBodySent
        private var timeText: TextView = view.timeStamp

        override fun bind(message: Message) {
            messageText.text = message.message
            timeText.text = DateUtils.fromMillisToTimeString(message.time)
            timeText.visibility = View.GONE
        }

        fun showHide(view:View) {
            view.timeStamp.visibility = if (view.timeStamp.visibility == View.GONE){
                View.VISIBLE
            } else{
                View.GONE
            }
        }

        init {
            view.setOnClickListener {
                showHide(view.timeStamp)
            }
        }
    }

    inner class OtherMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.ChatBodyReceived
        private var timeText: TextView = view.timeStampReceived

        override fun bind(message: Message) {
            messageText.text = message.message
            timeText.text = DateUtils.fromMillisToTimeString(message.time)
            timeText.visibility = View.GONE
        }

        fun showHide(view:View) {
            view.timeStampReceived.visibility = if (view.timeStampReceived.visibility == View.GONE){
                View.VISIBLE
            } else{
                View.GONE
            }
        }

        init {
            view.setOnClickListener {
                showHide(view.timeStampReceived)
            }
        }
    }
}

    open class MessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(message:Message) {}
}