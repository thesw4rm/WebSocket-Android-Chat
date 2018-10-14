package com.example.ytpillai.cmsc_355_proj.messaging

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocketListener
import okhttp3.WebSocket
import okio.ByteString


class MessageSocket() : WebSocketListener() {
    private val NORMAL_CLOSURE_STATUS = 1000

    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.send("Hello, it's SSaurel !")
        webSocket.send("What's up ?")
        webSocket.send(ByteString.decodeHex("deadbeef"))
        webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !")
    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        Log.d("Receiving text", text!!)
    }

    override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
        Log.d("Receiving bytes", bytes!!.hex())
    }

    override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
        webSocket!!.close(NORMAL_CLOSURE_STATUS, null)
        Log.d("Close WebSocketListener", "$code / $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response) {
        Log.e("Err WebSocketListener ", t.message)
    }
}