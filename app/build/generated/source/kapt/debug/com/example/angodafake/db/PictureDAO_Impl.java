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
public final class PictureDAO_Impl implements PictureDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Picture> __insertionAdapterOfPicture;

  private final EntityDeletionOrUpdateAdapter<Picture> __deletionAdapterOfPicture;

  private final EntityDeletionOrUpdateAdapter<Picture> __updateAdapterOfPicture;

  public PictureDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPicture = new EntityInsertionAdapter<Picture>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `picture_db` (`ID_Hotel`,`picture`,`picture_onwer`,`id`) VALUES (?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Picture entity) {
        statement.bindLong(1, entity.getID_Hotel());
        if (entity.getPicture() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPicture());
        }
        if (entity.getPicture_onwer() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPicture_onwer());
        }
        statement.bindLong(4, entity.getId());
      }
    };
    this.__deletionAdapterOfPicture = new EntityDeletionOrUpdateAdapter<Picture>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `picture_db` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Picture entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPicture = new EntityDeletionOrUpdateAdapter<Picture>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `picture_db` SET `ID_Hotel` = ?,`picture` = ?,`picture_onwer` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Picture entity) {
        statement.bindLong(1, entity.getID_Hotel());
        if (entity.getPicture() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPicture());
        }
        if (entity.getPicture_onwer() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPicture_onwer());
        }
        statement.bindLong(4, entity.getId());
        statement.bindLong(5, entity.getId());
      }
    };
  }

  @Override
  public void insertPicture(final Picture picture) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPicture.insert(picture);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletePicture(final Picture picture) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPicture.handle(picture);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatePicture(final Picture picture) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPicture.handle(picture);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Picture> getPictureList() {
    final String _sql = "Select * from picture_db";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDHotel = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Hotel");
      final int _cursorIndexOfPicture = CursorUtil.getColumnIndexOrThrow(_cursor, "picture");
      final int _cursorIndexOfPictureOnwer = CursorUtil.getColumnIndexOrThrow(_cursor, "picture_onwer");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Picture> _result = new ArrayList<Picture>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Picture _item;
        final int _tmpID_Hotel;
        _tmpID_Hotel = _cursor.getInt(_cursorIndexOfIDHotel);
        final String _tmpPicture;
        if (_cursor.isNull(_cursorIndexOfPicture)) {
          _tmpPicture = null;
        } else {
          _tmpPicture = _cursor.getString(_cursorIndexOfPicture);
        }
        final String _tmpPicture_onwer;
        if (_cursor.isNull(_cursorIndexOfPictureOnwer)) {
          _tmpPicture_onwer = null;
        } else {
          _tmpPicture_onwer = _cursor.getString(_cursorIndexOfPictureOnwer);
        }
        _item = new Picture(_tmpID_Hotel,_tmpPicture,_tmpPicture_onwer);
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
  public Picture getPictureByHotelID(final int hotel_id) {
    final String _sql = "SELECT * FROM picture_db WHERE ID_Hotel = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, hotel_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDHotel = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Hotel");
      final int _cursorIndexOfPicture = CursorUtil.getColumnIndexOrThrow(_cursor, "picture");
      final int _cursorIndexOfPictureOnwer = CursorUtil.getColumnIndexOrThrow(_cursor, "picture_onwer");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final Picture _result;
      if (_cursor.moveToFirst()) {
        final int _tmpID_Hotel;
        _tmpID_Hotel = _cursor.getInt(_cursorIndexOfIDHotel);
        final String _tmpPicture;
        if (_cursor.isNull(_cursorIndexOfPicture)) {
          _tmpPicture = null;
        } else {
          _tmpPicture = _cursor.getString(_cursorIndexOfPicture);
        }
        final String _tmpPicture_onwer;
        if (_cursor.isNull(_cursorIndexOfPictureOnwer)) {
          _tmpPicture_onwer = null;
        } else {
          _tmpPicture_onwer = _cursor.getString(_cursorIndexOfPictureOnwer);
        }
        _result = new Picture(_tmpID_Hotel,_tmpPicture,_tmpPicture_onwer);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
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
