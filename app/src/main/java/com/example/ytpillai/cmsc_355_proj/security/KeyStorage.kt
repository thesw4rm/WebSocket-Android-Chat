package com.example.ytpillai.cmsc_355_proj.security

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context

import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log

import java.security.PublicKey
import java.security.PrivateKey
import java.security.KeyStore

open class KeyStorage private constructor() : Application() {

    private object Holder { val INSTANCE = KeyStorage() }

    companion object {
        val instance: KeyStorage by lazy { Holder.INSTANCE }
    }

    private val db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "ContactEntries"
    ).build()

    /**
     * Gets the public key from an alias if it exist
     * TODO: validate keys in case of tampering. Maybe use some sort of signing method?
     *
     * @alias: Alias of the key
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun getEncryptionKey(alias: String): PublicKey? {

        return if (keyPairInStore(alias)) {

            val ks = KeyStore.getInstance("AndroidKeyStore")
            ks.load(null)
            val privateKeyEntry = ks.getEntry(alias, null) as KeyStore.PrivateKeyEntry
            Log.d("HAHAHAHA", privateKeyEntry.certificate.publicKey.toString())

            privateKeyEntry.certificate.publicKey // public key

        } else {

            return db.userDao().findByAlias(alias)?.publicKey;

        }
    }

    /**
     * Gets the private key from an alias if it exists
     * TODO: validate keys in case of tampering. Maybe use some sort of signing method?
     *
     * @alias: Alias of the key
     */
    fun getDecryptionKey(alias: String): PrivateKey? {

        return if (keyPairInStore(alias)) {
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
     *
     * @alias: nickname for the key
     */
    fun keyPairInStore(alias: String): Boolean {

        val ks = KeyStore.getInstance("AndroidKeyStore")
        ks.load(null, null)
        return ks.containsAlias(alias)
    }

    fun containsAlias(alias: String): Boolean {
        return db.userDao().findByAlias(alias) != null
    }

    /**
     * Insert a new key into the database
     *
     * @key: The public key to insert
     * @alias: The alias for the key
     */
    fun insertKey(key: PublicKey, alias: String) {

        db.userDao().insertAll(User(alias, key))

    }

    /**
     * Deleted a key from the database
     *
     * @alias: The lias for the key to be deleted
     */
    fun deleteKey(alias: String) {
        db.userDao().delete(db.userDao().findByAlias(alias))
    }

}