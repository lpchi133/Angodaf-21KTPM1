package com.example.angodafake.Utilities

import com.example.angodafake.db.Purchase
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

object PurchaseUtils {
    private lateinit var database: DatabaseReference

    init {
        database = Firebase.database.reference
    }

    fun getAllPurchases(ownerID: String, listener: (List<Purchase>) -> Unit) {
        val purchasesQuery = database.child("purchases")

        purchasesQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val purchasesList = mutableListOf<Purchase>()
                for (purchaseSnapshot in dataSnapshot.children) {
                    val purchase = purchaseSnapshot.getValue(Purchase::class.java)
                    if (purchase?.ID_Owner == ownerID) {
                        purchase.ID = purchaseSnapshot.key
                        purchase.let { purchasesList.add(it) }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}