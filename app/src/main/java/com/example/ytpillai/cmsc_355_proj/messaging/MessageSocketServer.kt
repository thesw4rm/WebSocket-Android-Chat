package com.example.ytpillai.cmsc_355_proj.messaging

import android.util.Log
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.lang.Exception
import java.net.InetSocketAddress

class MessageSocketServer(address: InetSocketAddress) : WebSocketServer(address) {

    /**
     * Message socket server open handler. Eventually will do handshake stuff
     */
    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        if(conn == null){
            Log.e("MESSAGE_SOCKET_SERVER", "Tried to open a client's connection the ws server on this device, but got null connection. ")
        }
        conn!!.send("Ya I got the connection bro!") //Need to make this do important stuff like initial handshake stuff and things
    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        if(conn == null){
            Log.e("MESSAGE_SOCKET_SERVER", "Tried to close websocket server but got null connection. ")
        }
        conn!!.close(200, "Closing the connection because why not?")
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        if(conn == null){
            Log.e("MESSAGE_SOCKET_SERVER", "Tried to receive message on websocket server but got null connection. ")
        }
    }

    override fun onStart() {
        Log.d("MESSAGE_SOCKET_SERVER", "WS server started.")
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        Log.e("MESSAGE_SOCKET_SERVER", "Got an error: " + ex.toString())
    }

}