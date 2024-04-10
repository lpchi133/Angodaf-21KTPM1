package com.example.angodafake.db;

@com.google.firebase.database.IgnoreExtraProperties()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\rJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0019J\u0010\u0010!\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003Jn\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020\u000b2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\'\u001a\u00020(H\u00d6\u0001J\t\u0010)\u001a\u00020\u0003H\u00d6\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006*"}, d2 = {"Lcom/example/angodafake/db/Purchase;", "", "ID_Owner", "", "ID_Hotel", "ID_Room", "time_booking", "time_purchase", "total_purchase", "", "status_purchase", "", "detail", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Boolean;Ljava/lang/String;)V", "getID_Hotel", "()Ljava/lang/String;", "getID_Owner", "getID_Room", "getDetail", "getStatus_purchase", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getTime_booking", "getTime_purchase", "getTotal_purchase", "()Ljava/lang/Double;", "Ljava/lang/Double;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/example/angodafake/db/Purchase;", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class Purchase {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ID_Owner = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ID_Hotel = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String ID_Room = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String time_booking = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String time_purchase = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Double total_purchase = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean status_purchase = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String detail = null;
    
    public Purchase(@org.jetbrains.annotations.Nullable()
    java.lang.String ID_Owner, @org.jetbrains.annotations.Nullable()
    java.lang.String ID_Hotel, @org.jetbrains.annotations.Nullable()
    java.lang.String ID_Room, @org.jetbrains.annotations.Nullable()
    java.lang.String time_booking, @org.jetbrains.annotations.Nullable()
    java.lang.String time_purchase, @org.jetbrains.annotations.Nullable()
    java.lang.Double total_purchase, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean status_purchase, @org.jetbrains.annotations.Nullable()
    java.lang.String detail) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getID_Owner() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getID_Hotel() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getID_Room() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTime_booking() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTime_purchase() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getTotal_purchase() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean getStatus_purchase() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDetail() {
        return null;
    }
    
    public Purchase() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.angodafake.db.Purchase copy(@org.jetbrains.annotations.Nullable()
    java.lang.String ID_Owner, @org.jetbrains.annotations.Nullable()
    java.lang.String ID_Hotel, @org.jetbrains.annotations.Nullable()
    java.lang.String ID_Room, @org.jetbrains.annotations.Nullable()
    java.lang.String time_booking, @org.jetbrains.annotations.Nullable()
    java.lang.String time_purchase, @org.jetbrains.annotations.Nullable()
    java.lang.Double total_purchase, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean status_purchase, @org.jetbrains.annotations.Nullable()
    java.lang.String detail) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}