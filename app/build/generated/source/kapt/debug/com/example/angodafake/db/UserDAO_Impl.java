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
public final class UserDAO_Impl implements UserDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<User> __deletionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<User> __updateAdapterOfUser;

  public UserDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `user_db` (`name`,`dob`,`gender`,`number`,`email`,`country`,`cardNumber`,`cardName`,`point`,`userName`,`password`,`id`) VALUES (?,?,?,?,?,?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final User entity) {
        if (entity.getName() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getName());
        }
        if (entity.getDob() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDob());
        }
        if (entity.getGender() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getGender());
        }
        if (entity.getNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getNumber());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getEmail());
        }
        if (entity.getCountry() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCountry());
        }
        if (entity.getCardNumber() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCardNumber());
        }
        if (entity.getCardName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCardName());
        }
        statement.bindLong(9, entity.getPoint());
        if (entity.getUserName() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getUserName());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getPassword());
        }
        statement.bindLong(12, entity.getId());
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `user_db` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final User entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_db` SET `name` = ?,`dob` = ?,`gender` = ?,`number` = ?,`email` = ?,`country` = ?,`cardNumber` = ?,`cardName` = ?,`point` = ?,`userName` = ?,`password` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final User entity) {
        if (entity.getName() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getName());
        }
        if (entity.getDob() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getDob());
        }
        if (entity.getGender() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getGender());
        }
        if (entity.getNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getNumber());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getEmail());
        }
        if (entity.getCountry() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCountry());
        }
        if (entity.getCardNumber() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCardNumber());
        }
        if (entity.getCardName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCardName());
        }
        statement.bindLong(9, entity.getPoint());
        if (entity.getUserName() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getUserName());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getPassword());
        }
        statement.bindLong(12, entity.getId());
        statement.bindLong(13, entity.getId());
      }
    };
  }

  @Override
  public void insertUser(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteUser(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUser.handle(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateUser(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUser.handle(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<User> getUserList() {
    final String _sql = "Select * from user_db";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDob = CursorUtil.getColumnIndexOrThrow(_cursor, "dob");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
      final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfCardNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "cardNumber");
      final int _cursorIndexOfCardName = CursorUtil.getColumnIndexOrThrow(_cursor, "cardName");
      final int _cursorIndexOfPoint = CursorUtil.getColumnIndexOrThrow(_cursor, "point");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final User _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpDob;
        if (_cursor.isNull(_cursorIndexOfDob)) {
          _tmpDob = null;
        } else {
          _tmpDob = _cursor.getString(_cursorIndexOfDob);
        }
        final String _tmpGender;
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _tmpGender = null;
        } else {
          _tmpGender = _cursor.getString(_cursorIndexOfGender);
        }
        final String _tmpNumber;
        if (_cursor.isNull(_cursorIndexOfNumber)) {
          _tmpNumber = null;
        } else {
          _tmpNumber = _cursor.getString(_cursorIndexOfNumber);
        }
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        final String _tmpCountry;
        if (_cursor.isNull(_cursorIndexOfCountry)) {
          _tmpCountry = null;
        } else {
          _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        }
        final String _tmpCardNumber;
        if (_cursor.isNull(_cursorIndexOfCardNumber)) {
          _tmpCardNumber = null;
        } else {
          _tmpCardNumber = _cursor.getString(_cursorIndexOfCardNumber);
        }
        final String _tmpCardName;
        if (_cursor.isNull(_cursorIndexOfCardName)) {
          _tmpCardName = null;
        } else {
          _tmpCardName = _cursor.getString(_cursorIndexOfCardName);
        }
        final int _tmpPoint;
        _tmpPoint = _cursor.getInt(_cursorIndexOfPoint);
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _item = new User(_tmpName,_tmpDob,_tmpGender,_tmpNumber,_tmpEmail,_tmpCountry,_tmpCardNumber,_tmpCardName,_tmpPoint,_tmpUserName,_tmpPassword);
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
  public User getUserByID(final int user_id) {
    final String _sql = "SELECT * FROM user_db WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, user_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDob = CursorUtil.getColumnIndexOrThrow(_cursor, "dob");
      final int _cursorIndexOfGender = CursorUtil.getColumnIndexOrThrow(_cursor, "gender");
      final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
      final int _cursorIndexOfCardNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "cardNumber");
      final int _cursorIndexOfCardName = CursorUtil.getColumnIndexOrThrow(_cursor, "cardName");
      final int _cursorIndexOfPoint = CursorUtil.getColumnIndexOrThrow(_cursor, "point");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final User _result;
      if (_cursor.moveToFirst()) {
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpDob;
        if (_cursor.isNull(_cursorIndexOfDob)) {
          _tmpDob = null;
        } else {
          _tmpDob = _cursor.getString(_cursorIndexOfDob);
        }
        final String _tmpGender;
        if (_cursor.isNull(_cursorIndexOfGender)) {
          _tmpGender = null;
        } else {
          _tmpGender = _cursor.getString(_cursorIndexOfGender);
        }
        final String _tmpNumber;
        if (_cursor.isNull(_cursorIndexOfNumber)) {
          _tmpNumber = null;
        } else {
          _tmpNumber = _cursor.getString(_cursorIndexOfNumber);
        }
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        final String _tmpCountry;
        if (_cursor.isNull(_cursorIndexOfCountry)) {
          _tmpCountry = null;
        } else {
          _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        }
        final String _tmpCardNumber;
        if (_cursor.isNull(_cursorIndexOfCardNumber)) {
          _tmpCardNumber = null;
        } else {
          _tmpCardNumber = _cursor.getString(_cursorIndexOfCardNumber);
        }
        final String _tmpCardName;
        if (_cursor.isNull(_cursorIndexOfCardName)) {
          _tmpCardName = null;
        } else {
          _tmpCardName = _cursor.getString(_cursorIndexOfCardName);
        }
        final int _tmpPoint;
        _tmpPoint = _cursor.getInt(_cursorIndexOfPoint);
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _result = new User(_tmpName,_tmpDob,_tmpGender,_tmpNumber,_tmpEmail,_tmpCountry,_tmpCardNumber,_tmpCardName,_tmpPoint,_tmpUserName,_tmpPassword);
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
