package com.example.ytpillai.cmsc_355_proj

import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import org.junit.Test

class isValidIPUnitTest {

    @Test
    fun isValidIP(String checkMyIP) {

        var editTextHello = findViewById(R.id.ipAddress) as EditText

        var checkMyIP = editTextHello.text.toString()



        var checkMyIParray = checkMyIP.toCharArray()

        var validChar = false

        for (i in checkMyIParray) {
            when (i) {
                '0' -> validChar = true
                '1' -> validChar = true
                '2' -> validChar = true
                '3' -> validChar = true
                '4' -> validChar = true
                '5' -> validChar = true
                '6' -> validChar = true
                '7' -> validChar = true
                '8' -> validChar = true
                '9' -> validChar = true
                '.' -> validChar = true
                else -> {
                    validChar = false

                }
            }
            if (validChar == false ) {
                break
            }

        }

        if (validChar == false) {
            Toast.makeText(this, "Please Enter Valid IP", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "Connecting...", Toast.LENGTH_SHORT).show()

        }

        assert()

    }

}