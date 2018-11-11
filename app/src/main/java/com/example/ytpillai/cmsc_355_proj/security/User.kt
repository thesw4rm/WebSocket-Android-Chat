package com.example.ytpillai.cmsc_355_proj.security

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.security.PublicKey


@Entity
data class User(
        @PrimaryKey var alias: String?,
        @ColumnInfo(name = "PublicKey") var publicKey: PublicKey?
)