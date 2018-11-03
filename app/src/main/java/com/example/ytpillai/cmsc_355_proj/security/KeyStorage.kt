package com.example.ytpillai.cmsc_355_proj.security

import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import java.security.*
import java.util.Enumeration

class KeyStorage {

    private object Holder { val INSTANCE = KeyStorage() }

    companion object {
        val instance: KeyStorage by lazy { Holder.INSTANCE }
    }

    /**
     * Gets the local public key as a string if it exist, otherwise generates
     *
     * @alias: Alias of the key
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun getEncryptionKey(alias: String): PublicKey? {

        return if (keyPairExists(alias)) {

            val ks = KeyStore.getInstance("AndroidKeyStore")
            ks.load(null)
            val privateKeyEntry = ks.getEntry(alias, null) as KeyStore.PrivateKeyEntry
            Log.d("HAHAHAHA", privateKeyEntry.certificate.publicKey.toString())

            privateKeyEntry.certificate.publicKey // public key

        } else {
            return null
        }
    }

    fun getDecryptionKey(alias: String): PrivateKey? {

        return if (keyPairExists(alias)) {
            val ks = KeyStore.getInstance("AndroidKeyStore")
            ks.load(null, null)

            val privateKeyEntry = ks.getEntry(alias, null) as KeyStore.PrivateKeyEntry

            privateKeyEntry.privateKey
        } else {
            return null
        }

    }

    /**
     * Check android keystore for aliases
     * TODO: validate keys in case of tampering. Maybe use some sort of signing method?
     *
     * @alias: nickname for the key
     */
    fun keyPairExists(alias: String): Boolean {

        val ks = KeyStore.getInstance("AndroidKeyStore")
        ks.load(null, null)
        return ks.containsAlias(alias)
    }

    fun listAliases(): Enumeration<String> {
        val ks: KeyStore = KeyStore.getInstance("AndroidKeyStore").apply{
            load(null)
        }

        return ks.aliases()
    }

    /**
     * Insert a new key into the Android KeyStore
     */
    fun insertKey(key: PublicKey, alias: String) {

        val ks = KeyStore.getInstance("AndroidKeyStore")

        ks.setKeyEntry(alias, key, null, null)

    }

}