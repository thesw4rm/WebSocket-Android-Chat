package com.example.ytpillai.cmsc_355_proj.security

abstract class Cipher {
    val KEY_ALIAS: String = "INKO_KEY"

    abstract fun encrypt(plainText: String, alias: String): String?
    abstract fun decrypt(cipherText: String, alias: String): String?
}