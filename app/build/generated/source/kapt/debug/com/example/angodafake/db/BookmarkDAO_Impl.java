package com.example.angodafake.db;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BookmarkDAO_Impl implements BookmarkDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Bookmarks> __insertionAdapterOfBookmarks;

  private final EntityDeletionOrUpdateAdapter<Bookmarks> __deletionAdapterOfBookmarks;

  private final EntityDeletionOrUpdateAdapter<Bookmarks> __updateAdapterOfBookmarks;

  public BookmarkDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBookmarks = new EntityInsertionAdapter<Bookmarks>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `bookmark_db` (`ID_Owner`,`ID_Hotel`,`id`) VALUES (?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Bookmarks entity) {
        statement.bindLong(1, entity.getID_Owner());
        statement.bindLong(2, entity.getID_Hotel());
        statement.bindLong(3, entity.getId());
      }
    };
    this.__deletionAdapterOfBookmarks = new EntityDeletionOrUpdateAdapter<Bookmarks>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `bookmark_db` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Bookmarks entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfBookmarks = new EntityDeletionOrUpdateAdapter<Bookmarks>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `bookmark_db` SET `ID_Owner` = ?,`ID_Hotel` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Bookmarks entity) {
        statement.bindLong(1, entity.getID_Owner());
        statement.bindLong(2, entity.getID_Hotel());
        statement.bindLong(3, entity.getId());
        statement.bindLong(4, entity.getId());
      }
    };
  }

  @Override
  public void insertBookmark(final Bookmarks bookmark) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBookmarks.insert(bookmark);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteBookmark(final Bookmarks bookmark) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfBookmarks.handle(bookmark);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateBookmark(final Bookmarks bookmark) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfBookmarks.handle(bookmark);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Bookmarks> getBookmarkList() {
    final String _sql = "Select * from bookmark_db";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Owner");
      final int _cursorIndexOfIDHotel = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Hotel");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Bookmarks> _result = new ArrayList<Bookmarks>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Bookmarks _item;
        final int _tmpID_Owner;
        _tmpID_Owner = _cursor.getInt(_cursorIndexOfIDOwner);
        final int _tmpID_Hotel;
        _tmpID_Hotel = _cursor.getInt(_cursorIndexOfIDHotel);
        _item = new Bookmarks(_tmpID_Owner,_tmpID_Hotel);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Bookmarks> getBookmarksByUserID(final int user_id) {
    final String _sql = "SELECT * FROM bookmark_db WHERE ID_Owner = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, user_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Owner");
      final int _cursorIndexOfIDHotel = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Hotel");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Bookmarks> _result = new ArrayList<Bookmarks>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Bookmarks _item;
        final int _tmpID_Owner;
        _tmpID_Owner = _cursor.getInt(_cursorIndexOfIDOwner);
        final int _tmpID_Hotel;
        _tmpID_Hotel = _cursor.getInt(_cursorIndexOfIDHotel);
        _item = new Bookmarks(_tmpID_Owner,_tmpID_Hotel);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
