package com.example.angodafake.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\'J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\'J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H\'J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\'J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\u0006\u0010\r\u001a\u00020\u0003H\'J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\'J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\'\u00a8\u0006\u0010"}, d2 = {"Lcom/example/angodafake/db/BookmarkDAO;", "", "checkIfExistHotelId", "", "hotel_id", "deleteBookmark", "", "bookmark", "Lcom/example/angodafake/db/Bookmarks;", "deleteBookmarkByHotelId", "getBookmarkList", "", "getBookmarksByUserID", "user_id", "insertBookmark", "updateBookmark", "app_debug"})
@androidx.room.Dao()
public abstract interface BookmarkDAO {
    
    @androidx.room.Query(value = "Select * from bookmark_db")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.example.angodafake.db.Bookmarks> getBookmarkList();
    
    @androidx.room.Query(value = "SELECT * FROM bookmark_db WHERE ID_Owner = :user_id")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.example.angodafake.db.Bookmarks> getBookmarksByUserID(int user_id);
    
    @androidx.room.Insert()
    public abstract void insertBookmark(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Bookmarks bookmark);
    
    @androidx.room.Update()
    public abstract void updateBookmark(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Bookmarks bookmark);
    
    @androidx.room.Delete()
    public abstract void deleteBookmark(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.Bookmarks bookmark);
    
    @androidx.room.Query(value = "DELETE FROM bookmark_db WHERE ID_Hotel = :hotel_id")
    public abstract void deleteBookmarkByHotelId(int hotel_id);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM bookmark_db WHERE ID_Hotel = :hotel_id")
    public abstract int checkIfExistHotelId(int hotel_id);
}