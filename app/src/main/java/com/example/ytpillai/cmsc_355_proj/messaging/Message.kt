package com.example.ytpillai.cmsc_355_proj.messaging

open class Message(msgDestIP: String, msgBody: String) {
    private var messageDestination: String = msgDestIP
    private var messageBody: String = msgBody

}