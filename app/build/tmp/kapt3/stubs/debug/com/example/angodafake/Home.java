package com.example.angodafake;

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 42\u00020\u0001:\u00014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\'\u001a\u00020\u0019H\u0002J\u0012\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J&\u0010,\u001a\u0004\u0018\u00010\u00122\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u0001002\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0010\u00101\u001a\u00020)2\u0006\u00102\u001a\u00020\u0012H\u0002J\b\u00103\u001a\u00020)H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00150!X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/example/angodafake/Home;", "Landroidx/fragment/app/Fragment;", "idUser", "", "(I)V", "adapter", "Lcom/example/angodafake/Adapter/HotelAdapter;", "checkIn", "Lcom/google/android/material/textfield/TextInputLayout;", "checkIn_Text", "Lcom/google/android/material/textfield/TextInputEditText;", "checkOut", "checkOut_Text", "database", "Lcom/google/firebase/database/DatabaseReference;", "dateFormat", "Ljava/text/SimpleDateFormat;", "homeLayout", "Landroid/view/View;", "hotelAdapter", "", "Lcom/example/angodafake/db/Hotel;", "infor", "Landroid/widget/TextView;", "inforCount", "", "getInforCount", "()Ljava/lang/String;", "setInforCount", "(Ljava/lang/String;)V", "layoutManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "listHotels", "", "param1", "param2", "popupWindow", "Landroid/widget/PopupWindow;", "filterHotels", "query", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "setupViews", "view", "showPopup", "Companion", "app_debug"})
public final class Home extends androidx.fragment.app.Fragment {
    private int idUser;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String param1;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String param2;
    private android.view.View homeLayout;
    private java.util.List<com.example.angodafake.db.Hotel> hotelAdapter;
    private com.example.angodafake.Adapter.HotelAdapter adapter;
    private java.util.List<com.example.angodafake.db.Hotel> listHotels;
    private com.google.firebase.database.DatabaseReference database;
    private androidx.recyclerview.widget.RecyclerView.LayoutManager layoutManager;
    private com.google.android.material.textfield.TextInputLayout checkIn;
    private com.google.android.material.textfield.TextInputLayout checkOut;
    private com.google.android.material.textfield.TextInputEditText checkIn_Text;
    private com.google.android.material.textfield.TextInputEditText checkOut_Text;
    private android.widget.PopupWindow popupWindow;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String inforCount;
    private android.widget.TextView infor;
    @org.jetbrains.annotations.NotNull()
    private final java.text.SimpleDateFormat dateFormat = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.angodafake.Home.Companion Companion = null;
    
    public Home(int idUser) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getInforCount() {
        return null;
    }
    
    public final void setInforCount(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final void setupViews(android.view.View view) {
    }
    
    private final void showPopup() {
    }
    
    private final java.util.List<com.example.angodafake.db.Hotel> filterHotels(java.lang.String query) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final com.example.angodafake.Home newInstance(@org.jetbrains.annotations.NotNull()
    java.lang.String param1, @org.jetbrains.annotations.NotNull()
    java.lang.String param2, int idUser) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0007\u00a8\u0006\n"}, d2 = {"Lcom/example/angodafake/Home$Companion;", "", "()V", "newInstance", "Lcom/example/angodafake/Home;", "param1", "", "param2", "idUser", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        @kotlin.jvm.JvmStatic()
        @org.jetbrains.annotations.NotNull()
        public final com.example.angodafake.Home newInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String param1, @org.jetbrains.annotations.NotNull()
        java.lang.String param2, int idUser) {
            return null;
        }
    }
}