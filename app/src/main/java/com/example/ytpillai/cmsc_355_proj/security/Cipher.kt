package com.example.ytpillai.cmsc_355_proj.security

interface Cipher {

    fun encrypt(plainText: String, alias: String): String?
    fun decrypt(cipherText: String, alias: String): String?
}