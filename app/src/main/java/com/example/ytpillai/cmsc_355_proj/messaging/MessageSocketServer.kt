package com.example.ytpillai.cmsc_355_proj.messaging

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.ytpillai.cmsc_355_proj.ConversationActivity
import com.example.ytpillai.cmsc_355_proj.ProgramActivity
import com.example.ytpillai.cmsc_355_proj.R
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.lang.Exception
import java.net.InetSocketAddress

class MessageSocketServer(address: InetSocketAddress, var context: Context) : WebSocketServer(address) {

    /**
     * Message socket server open handler. Eventually will do handshake stuff
     */
    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        if (conn == null) {
            Log.e("MESSAGE_SOCKET_SERVER", "Tried to open a client's connection the ws server on this device, but got null connection. ")
        }
        conn!!.send("Ya I got the connection bro!") //Need to make this do important stuff like initial handshake stuff and things

    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        if (conn == null) {
            Log.e("MESSAGE_SOCKET_SERVER", "Tried to close websocket server but got null connection. ")
        }
        conn!!.close(200, "Closing the connection because why not?")
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        if (conn == null)
            Log.e("MESSAGE_SOCKET_SERVER", "Tried to receive message on websocket server but got null connection. ")
        else
            Log.d("MESSAGE_SOCKET_SERVER", "I GOT UR MESSAGE $message")

        val intent = Intent(context, ProgramActivity::class.java)
        intent.action = context.resources.getString(R.string.ACTION_START)
        intent.putExtra("message", message)
        context.sendBroadcast(intent)

    }

    override fun onStart() {
        Log.d("MESSAGE_SOCKET_SERVER", "WebSocket - server started.")
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        Log.e("MESSAGE_SOCKET_SERVER", "Got an error in socket", ex)
    }

}