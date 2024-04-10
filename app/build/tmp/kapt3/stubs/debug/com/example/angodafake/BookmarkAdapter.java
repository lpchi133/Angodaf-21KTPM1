package com.example.angodafake;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002#$B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0014H\u0016J\u0018\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0014H\u0016J\u000e\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0016\u0010!\u001a\u00020\u00162\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0007R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/example/angodafake/BookmarkAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/angodafake/BookmarkAdapter$MyViewHolder;", "context", "Landroid/content/Context;", "rootView", "Landroid/view/View;", "bookmarks", "", "Lcom/example/angodafake/db/Bookmarks;", "(Landroid/content/Context;Landroid/view/View;Ljava/util/List;)V", "HotelMarked", "Lcom/example/angodafake/db/Hotel;", "Picture", "Lcom/example/angodafake/db/Picture;", "hotel_db", "Lcom/example/angodafake/db/HotelDatabase;", "listener", "Lcom/example/angodafake/BookmarkAdapter$OnItemClickListener;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setOnItemClickListener", "showSuccessSnackBar", "msg", "", "updateList", "newList", "MyViewHolder", "OnItemClickListener", "app_debug"})
public final class BookmarkAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.angodafake.BookmarkAdapter.MyViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private android.view.View rootView;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.angodafake.db.Bookmarks> bookmarks;
    @org.jetbrains.annotations.Nullable()
    private com.example.angodafake.BookmarkAdapter.OnItemClickListener listener;
    private com.example.angodafake.db.HotelDatabase hotel_db;
    private com.example.angodafake.db.Hotel HotelMarked;
    private com.example.angodafake.db.Picture Picture;
    
    public BookmarkAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.view.View rootView, @org.jetbrains.annotations.NotNull()
    java.util.List<com.example.angodafake.db.Bookmarks> bookmarks) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.angodafake.BookmarkAdapter.MyViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.BookmarkAdapter.MyViewHolder holder, int position) {
    }
    
    @android.annotation.SuppressLint(value = {"NotifyDataSetChanged"})
    public final void updateList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.angodafake.db.Bookmarks> newList) {
    }
    
    public final void setOnItemClickListener(@org.jetbrains.annotations.NotNull()
    com.example.angodafake.BookmarkAdapter.OnItemClickListener listener) {
    }
    
    private final void showSuccessSnackBar(java.lang.String msg) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0011\u0010\u0017\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\b\u00a8\u0006\u0019"}, d2 = {"Lcom/example/angodafake/BookmarkAdapter$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "bookmarkItem", "Landroid/view/View;", "(Landroid/view/View;)V", "Point", "Landroid/widget/TextView;", "getPoint", "()Landroid/widget/TextView;", "buttonFav", "Landroid/widget/Button;", "getButtonFav", "()Landroid/widget/Button;", "hotelName", "getHotelName", "img", "Landroid/widget/ImageView;", "getImg", "()Landroid/widget/ImageView;", "location", "getLocation", "quaCM", "getQuaCM", "rateStatus", "getRateStatus", "app_debug"})
    public static final class MyViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView img = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView hotelName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.Button buttonFav = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView location = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView rateStatus = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView Point = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView quaCM = null;
        
        public MyViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View bookmarkItem) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getImg() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getHotelName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.Button getButtonFav() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getLocation() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getRateStatus() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getPoint() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getQuaCM() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/example/angodafake/BookmarkAdapter$OnItemClickListener;", "", "onItemClick", "", "bookmark", "Lcom/example/angodafake/db/Bookmarks;", "app_debug"})
    public static abstract interface OnItemClickListener {
        
        public abstract void onItemClick(@org.jetbrains.annotations.NotNull()
        com.example.angodafake.db.Bookmarks bookmark);
    }
}