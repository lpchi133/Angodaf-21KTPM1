package com.example.angodafake.db

data class Picture_Room(
    val ID: String? = null,
    val ID_Hotel: String? = null,
    val url : String? = null,) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
