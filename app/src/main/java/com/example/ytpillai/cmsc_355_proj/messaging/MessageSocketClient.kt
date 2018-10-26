package com.example.ytpillai.cmsc_355_proj.messaging

import android.content.Intent
import android.content.Context
import android.util.Log
import com.example.ytpillai.cmsc_355_proj.R
import okhttp3.Response
import okhttp3.WebSocketListener
import okhttp3.WebSocket
import okio.ByteString
import java.net.InetSocketAddress
import java.net.URI


class MessageSocketClient(uri: URI, var context: Context) : WebSocketListener() {
    private val NORMAL_CLOSURE_STATUS = 1000

    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.send("Chat has been connected by client!")
        Log.d("MESSAGE_SOCKET_CLIENT", "Opened connection")

        // Get public get

    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        Log.d("MESSAGE_SOCKET_CLIENT", "Receiving text: " + text!!)

        Intent().also { intent ->
            intent.action = context.resources.getString(R.string.ACTION_RECEIVED_MESSAGE)
            intent.putExtra("message", text)
            context.sendBroadcast(intent)
        }

    }

    override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
        Log.d("MESSAGE_SOCKET_CLIENT" ,"Receiving bytes" + bytes!!.hex())

        Intent().also { intent ->
            intent.action = context.resources.getString(R.string.ACTION_RECEIVED_MESSAGE)
            intent.putExtra("message", bytes)
            context.sendBroadcast(intent)
        }
    }

    override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
        webSocket!!.close(NORMAL_CLOSURE_STATUS, null)
        Log.d("MESSAGE_SOCKET_CLIENT", "Close WebSocketListener: $code / $reason")
        Intent().also { intent ->
            intent.action = context.resources.getString(R.string.ACTION_CONVO_CLOSED)
            context.sendBroadcast(intent)
        }
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response) {
        Log.e("MESSAGE_SOCKET_CLIENT", "Err WebSocketListener " + t.message)
    }
}