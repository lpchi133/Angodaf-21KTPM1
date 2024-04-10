package com.example.angodafake.db;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH\'J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bH\'J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\bH\'J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u0012"}, d2 = {"Lcom/example/angodafake/db/UserDAO;", "", "deleteUser", "", "user", "Lcom/example/angodafake/db/User;", "getUserByEmail", "userEmail", "", "getUserById", "userId", "", "getUserByPhoneNumber", "userNumber", "getUserList", "", "insertUser", "updateUser", "app_debug"})
@androidx.room.Dao()
public abstract interface UserDAO {
    
    @androidx.room.Query(value = "Select * from user_db")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.example.angodafake.db.User> getUserList();
    
    @androidx.room.Insert()
    public abstract void insertUser(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.User user);
    
    @androidx.room.Query(value = "SELECT * FROM user_db WHERE id = :userId")
    @org.jetbrains.annotations.Nullable()
    public abstract com.example.angodafake.db.User getUserById(int userId);
    
    @androidx.room.Query(value = "SELECT * FROM user_db WHERE email = :userEmail")
    @org.jetbrains.annotations.Nullable()
    public abstract com.example.angodafake.db.User getUserByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String userEmail);
    
    @androidx.room.Query(value = "SELECT * FROM user_db WHERE number = :userNumber")
    @org.jetbrains.annotations.Nullable()
    public abstract com.example.angodafake.db.User getUserByPhoneNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String userNumber);
    
    @androidx.room.Update()
    public abstract void updateUser(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.User user);
    
    @androidx.room.Delete()
    public abstract void deleteUser(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.db.User user);
}