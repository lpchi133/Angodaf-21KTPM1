package com.example.angodafake.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\'J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\'J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000bH\'J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0012"}, d2 = {"Lcom/example/angodafake/db/HotelDAO;", "", "deleteHotel", "", "hotel", "Lcom/example/angodafake/db/Hotel;", "getHotelByID", "hotel_id", "", "getHotelByLocation", "hotel_location", "", "getHotelByName", "hotel_name", "getHotelList", "", "insertHotel", "updateHotel", "app_debug"})
@androidx.room.Dao()
public abstract interface HotelDAO {
    
    @androidx.room.Query(value = "Select * from hotel_db")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.example.angodafake.db.Hotel> getHotelList();
    
    @androidx.room.Query(value = "SELECT * FROM hotel_db WHERE id = :hotel_id")
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.angodafake.db.Hotel getHotelByID(int hotel_id);
    
    @androidx.room.Query(value = "SELECT * FROM hotel_db WHERE locationDetail = :hotel_location")
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.angodafake.db.Hotel getHotelByLocation(@org.jetbrains.annotations.NotNull()
    java.lang.String hotel_location);
    
    @androidx.room.Query(value = "SELECT * FROM hotel_db WHERE name = :hotel_name")
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.angodafake.db.Hotel getHotelByName(@org.jetbrains.annotations.NotNull()
    java.lang.String hotel_name);
    
    @androidx.room.Insert()
    public abstract void insertHotel(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Hotel hotel);
    
    @androidx.room.Update()
    public abstract void updateHotel(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Hotel hotel);
    
    @androidx.room.Delete()
    public abstract void deleteHotel(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Hotel hotel);
}