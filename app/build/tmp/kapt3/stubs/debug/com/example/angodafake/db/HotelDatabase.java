package com.example.angodafake.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&\u00a8\u0006\u0010"}, d2 = {"Lcom/example/angodafake/db/HotelDatabase;", "Landroidx/room/RoomDatabase;", "()V", "BookmarkDAO", "Lcom/example/angodafake/db/BookmarkDAO;", "CommentDAO", "Lcom/example/angodafake/db/CommentDAO;", "HotelDAO", "Lcom/example/angodafake/db/HotelDAO;", "PictureDAO", "Lcom/example/angodafake/db/PictureDAO;", "PurchaseDAO", "Lcom/example/angodafake/db/PurchaseDAO;", "RoomDAO", "Lcom/example/angodafake/db/RoomDAO;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.example.angodafake.db.Hotel.class, com.example.angodafake.db.Bookmarks.class, com.example.angodafake.db.Picture.class, com.example.angodafake.db.Comment.class, com.example.angodafake.db.Purchase.class, com.example.angodafake.db.Rooms.class}, version = 1)
public abstract class HotelDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DB_NAME = "hotel_db";
    @org.jetbrains.annotations.Nullable()
    private static com.example.angodafake.db.HotelDatabase instance;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.angodafake.db.HotelDatabase.Companion Companion = null;
    
    public HotelDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.angodafake.db.HotelDAO HotelDAO();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.angodafake.db.BookmarkDAO BookmarkDAO();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.angodafake.db.PictureDAO PictureDAO();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.angodafake.db.CommentDAO CommentDAO();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.angodafake.db.PurchaseDAO PurchaseDAO();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.angodafake.db.RoomDAO RoomDAO();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/angodafake/db/HotelDatabase$Companion;", "", "()V", "DB_NAME", "", "instance", "Lcom/example/angodafake/db/HotelDatabase;", "buildDatabase", "context", "Landroid/content/Context;", "getInstance", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.angodafake.db.HotelDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private final com.example.angodafake.db.HotelDatabase buildDatabase(android.content.Context context) {
            return null;
        }
    }
}