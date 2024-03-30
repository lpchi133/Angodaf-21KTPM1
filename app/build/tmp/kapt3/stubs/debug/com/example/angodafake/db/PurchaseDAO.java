package com.example.angodafake.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\'J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\n"}, d2 = {"Lcom/example/angodafake/db/PurchaseDAO;", "", "deletePurchase", "", "purchase", "Lcom/example/angodafake/db/Purchase;", "getPurchaseList", "", "insertPurchase", "updatePurchase", "app_debug"})
@androidx.room.Dao()
public abstract interface PurchaseDAO {
    
    @androidx.room.Query(value = "Select * from purchase_db")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.example.angodafake.db.Purchase> getPurchaseList();
    
    @androidx.room.Insert()
    public abstract void insertPurchase(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Purchase purchase);
    
    @androidx.room.Update()
    public abstract void updatePurchase(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Purchase purchase);
    
    @androidx.room.Delete()
    public abstract void deletePurchase(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Purchase purchase);
}