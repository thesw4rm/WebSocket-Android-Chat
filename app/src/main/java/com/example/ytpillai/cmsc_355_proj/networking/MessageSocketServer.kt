package com.example.ytpillai.cmsc_355_proj.networking

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.ytpillai.cmsc_355_proj.R
import com.example.ytpillai.cmsc_355_proj.security.RSA
import com.example.ytpillai.cmsc_355_proj.security.KeyStorage
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import java.net.InetSocketAddress

class MessageSocketServer(address: InetSocketAddress, var context: Context) : WebSocketServer(address) {

    /**
     * Message socket server open handler. Eventually will do handshake stuff
     */
    override fun onOpen(conn: WebSocket?, handshake: ClientHandshake?) {
        if (conn == null) {
            Log.e("MESSAGE_SOCKET_SERVER", "Tried to open a client's connection the ws server on this device, but got null connection. ")
        }

        conn!!.send("RSA_PUBLIC_KEY\n" + KeyStorage.instance.getEncryptionKey(RSA.instance.KEY_ALIAS)) //Need to make this do important stuff like initial handshake stuff and things. Eventually symmetric key encrypt this

    }

    override fun onClose(conn: WebSocket?, code: Int, reason: String?, remote: Boolean) {
        if (conn == null) {
            Log.e("MESSAGE_SOCKET_SERVER", "Tried to close websocket server but got null connection. ")
        }
        conn!!.close(200, "Closing the connection because why not? $code / $reason")
        Intent().also { intent ->
            intent.action = context.resources.getString(R.string.ACTION_CONVO_CLOSED)
            context.sendBroadcast(intent)
        }
    }

    override fun onMessage(conn: WebSocket?, message: String?) {
        if (conn == null)
            Log.e("MESSAGE_SOCKET_SERVER", "Tried to receive message on websocket server but got null connection. ")
        else
            Log.d("MESSAGE_SOCKET_SERVER", "I GOT UR MESSAGE $message")

        if (message!!.substring(0, "RSA_PUBLIC_KEY\n".length) == "RSA_PUBLIC_KEY\n")
        //setOtherKeyAlias("${getRemoteSocketAddress(conn).address}_PUBLIC_KEY", message.substring("RSA_PUBLIC_KEY\n".length - 1, message.length))
        else {
            Intent().also { intent ->
                intent.action = context.resources.getString(R.string.ACTION_RECEIVED_MESSAGE)
                intent.putExtra("message", message)
                context.sendBroadcast(intent)
            }
        }

    }

    override fun onStart() {
        Log.d("MESSAGE_SOCKET_SERVER", "WebSocket - server started.")
    }

    override fun onError(conn: WebSocket?, ex: Exception?) {
        Log.e("MESSAGE_SOCKET_SERVER", "Got an error in socket", ex)
    }

}