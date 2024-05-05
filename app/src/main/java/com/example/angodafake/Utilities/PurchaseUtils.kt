package com.example.angodafake.Utilities

import android.util.Log
import com.example.angodafake.db.Hotel
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

    fun updatePurchaseStatus(purchaseID: String, callback: (String) -> Unit) {
        val purchaseUpdate = hashMapOf<String, Any>(
            "/purchases/$purchaseID/detail" to "HOAN_TAT",
            "/purchases/$purchaseID/status_purchase" to "DA_THANH_TOAN"
        )

        database.updateChildren(purchaseUpdate)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.invoke("success")
                } else {
                    callback.invoke("failure")
                }
            }
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
                listener(purchasesList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    fun getAllPurchasesByHotelID(ownerID: String, listener: (List<Purchase>) -> Unit) {
        val purchasesQuery = database.child("purchases")

        purchasesQuery.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val purchasesList = mutableListOf<Purchase>()
                for (purchaseSnapshot in dataSnapshot.children) {
                    val purchase = purchaseSnapshot.getValue(Purchase::class.java)
                    if (purchase != null && purchase.ID_Hotel == ownerID) {
                        purchase.ID = purchaseSnapshot.key
                        purchasesList.add(purchase)
                    }
                }
                listener(purchasesList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
            }
        })
    }

    fun getPurchaseByID(ID: String, listener: (Purchase) -> Unit){
        database.child("purchases").child(ID).get().addOnSuccessListener {
            val purchase = it.getValue(Purchase::class.java)
            purchase?.ID = ID
            if(purchase != null) {
                listener(purchase)
            }
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    fun cancelPurchase(purchaseID: String, detail: String, reason: String, status_purchase: String, time_cancel: String, callback: (String) -> Unit) {
        val purchaseUpdate = hashMapOf<String, Any>(
            "/purchases/$purchaseID/detail" to detail,
            "/purchases/$purchaseID/reason" to reason,
            "/purchases/$purchaseID/status_purchase" to status_purchase,
            "/purchases/$purchaseID/time_cancel" to time_cancel,
        )

        database.updateChildren(purchaseUpdate)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback.invoke("success")
                } else {
                    callback.invoke("failure")
                }
            }
    }
}