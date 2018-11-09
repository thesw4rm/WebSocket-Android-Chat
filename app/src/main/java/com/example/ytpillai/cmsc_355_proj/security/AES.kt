package com.example.ytpillai.cmsc_355_proj.security

open class AES private constructor() : Cipher {

    val keyStorage: KeyStorage = KeyStorage.instance

    private object Holder { val INSTANCE = AES() }

    companion object {
        val instance: AES by lazy { Holder.INSTANCE }
    }

    /**
     * Encrypt plain text with key associated with the alias
     *
     * @plainText: Message string
     * @alias: Name associated with the key
     */
    override fun encrypt(plainText: String, alias: String): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Decrypt cipher text with key associated with the alias
     *
     * @cipherText: Encoded message
     * @alias: Name associated with the key
     */
    override fun decrypt(cipherText: String, alias: String): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}