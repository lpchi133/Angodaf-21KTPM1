package com.example.angodafake;

/**
 * A simple [Fragment] subclass.
 * Use the [Filter.newInstance] factory method to
 * create an instance of this fragment.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 02\u00020\u0001:\u00010B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J&\u0010\'\u001a\u0004\u0018\u00010\"2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010,\u001a\u00020$H\u0002J\b\u0010-\u001a\u00020$H\u0002J\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\t0\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00061"}, d2 = {"Lcom/example/angodafake/Filter;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lcom/example/angodafake/HotelAdapter;", "endValue", "", "hotelAdapter", "", "Lcom/example/angodafake/db/Hotel;", "isFitTextViewSelected", "", "layoutManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "listHotels", "listHotelsOri", "param1", "", "param2", "popupWindow", "Landroid/widget/PopupWindow;", "prevCommentCheckmarkVisibility", "", "prevCommentTextColor", "prevFitCheckmarkVisibility", "prevFitTextColor", "prevHighestCheckmarkVisibility", "prevLowestCheckmarkVisibility", "prevPriceHighTextColor", "prevPriceLowTextColor", "rangeSlider", "Lcom/google/android/material/slider/RangeSlider;", "startValue", "view", "Landroid/view/View;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "showPopupPrice", "showPopupSort", "sortHotelsByLowestRoomPrice", "hotels", "Companion", "app_debug"})
public final class Filter extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private java.lang.String param1;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String param2;
    private android.view.View view;
    private java.util.List<com.example.angodafake.db.Hotel> hotelAdapter;
    private com.example.angodafake.HotelAdapter adapter;
    private java.util.List<com.example.angodafake.db.Hotel> listHotels;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.angodafake.db.Hotel> listHotelsOri;
    private androidx.recyclerview.widget.RecyclerView.LayoutManager layoutManager;
    private android.widget.PopupWindow popupWindow;
    private float startValue = 1000.0F;
    private float endValue = 9000.0F;
    private com.google.android.material.slider.RangeSlider rangeSlider;
    private boolean isFitTextViewSelected = false;
    private int prevPriceLowTextColor = 0;
    private int prevPriceHighTextColor = 0;
    private int prevCommentTextColor = 0;
    private int prevFitTextColor = 0;
    private int prevLowestCheckmarkVisibility = android.view.View.INVISIBLE;
    private int prevHighestCheckmarkVisibility = android.view.View.INVISIBLE;
    private int prevCommentCheckmarkVisibility = android.view.View.INVISIBLE;
    private int prevFitCheckmarkVisibility = android.view.View.INVISIBLE;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.angodafake.Filter.Companion Companion = null;
    
    public Filter() {
        super();
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
    
    private final void showPopupPrice() {
    }
    
    private final void showPopupSort() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.angodafake.db.Hotel> sortHotelsByLowestRoomPrice(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.angodafake.db.Hotel> hotels) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final com.example.angodafake.Filter newInstance(@org.jetbrains.annotations.NotNull()
    java.lang.String param1, @org.jetbrains.annotations.NotNull()
    java.lang.String param2) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/angodafake/Filter$Companion;", "", "()V", "newInstance", "Lcom/example/angodafake/Filter;", "param1", "", "param2", "app_debug"})
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
         * @return A new instance of fragment MyRoom.
         */
        @kotlin.jvm.JvmStatic()
        @org.jetbrains.annotations.NotNull()
        public final com.example.angodafake.Filter newInstance(@org.jetbrains.annotations.NotNull()
        java.lang.String param1, @org.jetbrains.annotations.NotNull()
        java.lang.String param2) {
            return null;
        }
    }
}