package com.example.angodafake.db

data class Picture_Room(
    val id: String? = null,
    val url : String? = null,
    val ID_Hotel: String? = null,
    val picture: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
