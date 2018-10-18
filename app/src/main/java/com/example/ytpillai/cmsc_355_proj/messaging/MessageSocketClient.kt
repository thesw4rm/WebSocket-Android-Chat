package com.example.ytpillai.cmsc_355_proj.messaging

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocketListener
import okhttp3.WebSocket
import okio.ByteString


class MessageSocketClient() : WebSocketListener() {
    private val NORMAL_CLOSURE_STATUS = 1000

    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.send("Hello, it's SSaurel !")
        webSocket.send("What's up ?")
        webSocket.send(ByteString.decodeHex("deadbeef"))
        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
        Log.d("MESSAGE_SOCKET_CLIENT", "Opened connection")

        // Get public get

        //
    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        Log.d("MESSAGE_SOCKET_CLIENT", "Receiving text: " + text!!)
    }

    override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
        Log.d("MESSAGE_SOCKET_CLIENT" ,"Receiving bytes" + bytes!!.hex())
    }

    override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
        webSocket!!.close(NORMAL_CLOSURE_STATUS, null)
        Log.d("MESSAGE_SOCKET_CLIENT", "Close WebSocketListener: $code / $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response) {
        Log.e("MESSAGE_SOCKET_CLIENT", "Err WebSocketListener " + t.message)
    }
}