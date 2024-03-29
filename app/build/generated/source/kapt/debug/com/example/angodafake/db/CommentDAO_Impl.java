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
public final class CommentDAO_Impl implements CommentDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Comment> __insertionAdapterOfComment;

  private final EntityDeletionOrUpdateAdapter<Comment> __deletionAdapterOfComment;

  private final EntityDeletionOrUpdateAdapter<Comment> __updateAdapterOfComment;

  public CommentDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfComment = new EntityInsertionAdapter<Comment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `comment_db` (`ID_Owner`,`ID_Hotel`,`time`,`point`,`detail`,`id`) VALUES (?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Comment entity) {
        statement.bindLong(1, entity.getID_Owner());
        statement.bindLong(2, entity.getID_Hotel());
        if (entity.getTime() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTime());
        }
        statement.bindDouble(4, entity.getPoint());
        if (entity.getDetail() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDetail());
        }
        statement.bindLong(6, entity.getId());
      }
    };
    this.__deletionAdapterOfComment = new EntityDeletionOrUpdateAdapter<Comment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `comment_db` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Comment entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfComment = new EntityDeletionOrUpdateAdapter<Comment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `comment_db` SET `ID_Owner` = ?,`ID_Hotel` = ?,`time` = ?,`point` = ?,`detail` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Comment entity) {
        statement.bindLong(1, entity.getID_Owner());
        statement.bindLong(2, entity.getID_Hotel());
        if (entity.getTime() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTime());
        }
        statement.bindDouble(4, entity.getPoint());
        if (entity.getDetail() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDetail());
        }
        statement.bindLong(6, entity.getId());
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public void insertComment(final Comment comment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfComment.insert(comment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteComment(final Comment comment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfComment.handle(comment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateComment(final Comment comment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfComment.handle(comment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Comment> getCommentList() {
    final String _sql = "Select * from comment_db";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Owner");
      final int _cursorIndexOfIDHotel = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Hotel");
      final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final int _cursorIndexOfPoint = CursorUtil.getColumnIndexOrThrow(_cursor, "point");
      final int _cursorIndexOfDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "detail");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Comment> _result = new ArrayList<Comment>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Comment _item;
        final int _tmpID_Owner;
        _tmpID_Owner = _cursor.getInt(_cursorIndexOfIDOwner);
        final int _tmpID_Hotel;
        _tmpID_Hotel = _cursor.getInt(_cursorIndexOfIDHotel);
        final String _tmpTime;
        if (_cursor.isNull(_cursorIndexOfTime)) {
          _tmpTime = null;
        } else {
          _tmpTime = _cursor.getString(_cursorIndexOfTime);
        }
        final float _tmpPoint;
        _tmpPoint = _cursor.getFloat(_cursorIndexOfPoint);
        final String _tmpDetail;
        if (_cursor.isNull(_cursorIndexOfDetail)) {
          _tmpDetail = null;
        } else {
          _tmpDetail = _cursor.getString(_cursorIndexOfDetail);
        }
        _item = new Comment(_tmpID_Owner,_tmpID_Hotel,_tmpTime,_tmpPoint,_tmpDetail);
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
