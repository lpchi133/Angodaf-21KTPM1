package com.example.angodafake.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\'J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\r"}, d2 = {"Lcom/example/angodafake/db/PictureDAO;", "", "deletePicture", "", "picture", "Lcom/example/angodafake/db/Picture;", "getPictureByHotelID", "hotel_id", "", "getPictureList", "", "insertPicture", "updatePicture", "app_debug"})
@androidx.room.Dao()
public abstract interface PictureDAO {
    
    @androidx.room.Query(value = "Select * from picture_db")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.example.angodafake.db.Picture> getPictureList();
    
    @androidx.room.Query(value = "SELECT * FROM picture_db WHERE ID_Hotel = :hotel_id")
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.angodafake.db.Picture getPictureByHotelID(int hotel_id);
    
    @androidx.room.Insert()
    public abstract void insertPicture(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Picture picture);
    
    @androidx.room.Update()
    public abstract void updatePicture(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Picture picture);
    
    @androidx.room.Delete()
    public abstract void deletePicture(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Picture picture);
}