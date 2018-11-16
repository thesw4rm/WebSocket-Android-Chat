package com.example.ytpillai.cmsc_355_proj.networking

import com.tinder.scarlet.Stream
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send

interface MessageClient {
  @Send
  fun sendText(message: String)
  @Receive
  fun observeText(): Stream<String>
}