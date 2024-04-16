package com.example.angodafake.db

data class Picture_Room(
    var ID: String? = null,
    val ID_Hotel: String? = null,
    val picture: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
