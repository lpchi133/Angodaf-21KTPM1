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
import java.text.SimpleDateFormat
import java.util.Locale

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

    fun getBookedRoomBillsByHotelID(ID_Hotel: String, date: String, listener: (List<Purchase>?) -> Unit){
        val billQuery = database.child("purchases")
        billQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val purchaseList = mutableListOf<Purchase>()
                for (purchaseSnapshort in dataSnapshot.children){
                    val purchase = purchaseSnapshort.getValue(Purchase::class.java)
                    if (purchase?.ID_Hotel == ID_Hotel
                        && (purchase.time_cancel == "" || purchase.time_cancel == null)
                        && isDateInRange(date, purchase.date_come!!, purchase.date_go!!)
                    ) {
                        purchase.ID = purchaseSnapshort.key
                        purchaseList.add(purchase)
                    }
                }
                listener(purchaseList)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Xử lý khi có lỗi xảy ra
                Log.e("firebase", "Error getting room count: ${databaseError.message}")
                listener(null)
            }
        })
    }

    fun isDateInRange(date: String, startDate: String, endDate: String): Boolean {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateObj = format.parse(date)
        val startDateObj = format.parse(startDate)
        val endDateObj = format.parse(endDate)
        return dateObj in startDateObj..endDateObj
    }
}