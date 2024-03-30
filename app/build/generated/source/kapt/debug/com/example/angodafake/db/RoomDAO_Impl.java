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
public final class RoomDAO_Impl implements RoomDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Rooms> __insertionAdapterOfRooms;

  private final EntityDeletionOrUpdateAdapter<Rooms> __deletionAdapterOfRooms;

  private final EntityDeletionOrUpdateAdapter<Rooms> __updateAdapterOfRooms;

  public RoomDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRooms = new EntityInsertionAdapter<Rooms>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `room_db` (`ID_Hotel`,`quantity`,`available`,`type`,`acreage`,`price`,`bedQuantity`,`checkIn`,`checkOut`,`id`) VALUES (?,?,?,?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Rooms entity) {
        statement.bindLong(1, entity.getID_Hotel());
        statement.bindLong(2, entity.getQuantity());
        statement.bindLong(3, entity.getAvailable());
        if (entity.getType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getType());
        }
        statement.bindDouble(5, entity.getAcreage());
        statement.bindDouble(6, entity.getPrice());
        statement.bindLong(7, entity.getBedQuantity());
        if (entity.getCheckIn() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCheckIn());
        }
        if (entity.getCheckOut() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getCheckOut());
        }
        statement.bindLong(10, entity.getId());
      }
    };
    this.__deletionAdapterOfRooms = new EntityDeletionOrUpdateAdapter<Rooms>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `room_db` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Rooms entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfRooms = new EntityDeletionOrUpdateAdapter<Rooms>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `room_db` SET `ID_Hotel` = ?,`quantity` = ?,`available` = ?,`type` = ?,`acreage` = ?,`price` = ?,`bedQuantity` = ?,`checkIn` = ?,`checkOut` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Rooms entity) {
        statement.bindLong(1, entity.getID_Hotel());
        statement.bindLong(2, entity.getQuantity());
        statement.bindLong(3, entity.getAvailable());
        if (entity.getType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getType());
        }
        statement.bindDouble(5, entity.getAcreage());
        statement.bindDouble(6, entity.getPrice());
        statement.bindLong(7, entity.getBedQuantity());
        if (entity.getCheckIn() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCheckIn());
        }
        if (entity.getCheckOut() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getCheckOut());
        }
        statement.bindLong(10, entity.getId());
        statement.bindLong(11, entity.getId());
      }
    };
  }

  @Override
  public void insertRoom(final Rooms room) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfRooms.insert(room);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteRoom(final Rooms room) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfRooms.handle(room);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateRoom(final Rooms room) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfRooms.handle(room);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Rooms> getRoomList() {
    final String _sql = "Select * from room_db";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDHotel = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Hotel");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "available");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfAcreage = CursorUtil.getColumnIndexOrThrow(_cursor, "acreage");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfBedQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "bedQuantity");
      final int _cursorIndexOfCheckIn = CursorUtil.getColumnIndexOrThrow(_cursor, "checkIn");
      final int _cursorIndexOfCheckOut = CursorUtil.getColumnIndexOrThrow(_cursor, "checkOut");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Rooms> _result = new ArrayList<Rooms>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Rooms _item;
        final int _tmpID_Hotel;
        _tmpID_Hotel = _cursor.getInt(_cursorIndexOfIDHotel);
        final int _tmpQuantity;
        _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
        final int _tmpAvailable;
        _tmpAvailable = _cursor.getInt(_cursorIndexOfAvailable);
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        final double _tmpAcreage;
        _tmpAcreage = _cursor.getDouble(_cursorIndexOfAcreage);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final int _tmpBedQuantity;
        _tmpBedQuantity = _cursor.getInt(_cursorIndexOfBedQuantity);
        final String _tmpCheckIn;
        if (_cursor.isNull(_cursorIndexOfCheckIn)) {
          _tmpCheckIn = null;
        } else {
          _tmpCheckIn = _cursor.getString(_cursorIndexOfCheckIn);
        }
        final String _tmpCheckOut;
        if (_cursor.isNull(_cursorIndexOfCheckOut)) {
          _tmpCheckOut = null;
        } else {
          _tmpCheckOut = _cursor.getString(_cursorIndexOfCheckOut);
        }
        _item = new Rooms(_tmpID_Hotel,_tmpQuantity,_tmpAvailable,_tmpType,_tmpAcreage,_tmpPrice,_tmpBedQuantity,_tmpCheckIn,_tmpCheckOut);
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
  public List<Rooms> getRoomsByHotelID(final int hotel_id) {
    final String _sql = "SELECT * FROM room_db WHERE ID_Hotel = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, hotel_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDHotel = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Hotel");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "available");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfAcreage = CursorUtil.getColumnIndexOrThrow(_cursor, "acreage");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfBedQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "bedQuantity");
      final int _cursorIndexOfCheckIn = CursorUtil.getColumnIndexOrThrow(_cursor, "checkIn");
      final int _cursorIndexOfCheckOut = CursorUtil.getColumnIndexOrThrow(_cursor, "checkOut");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Rooms> _result = new ArrayList<Rooms>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Rooms _item;
        final int _tmpID_Hotel;
        _tmpID_Hotel = _cursor.getInt(_cursorIndexOfIDHotel);
        final int _tmpQuantity;
        _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
        final int _tmpAvailable;
        _tmpAvailable = _cursor.getInt(_cursorIndexOfAvailable);
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        final double _tmpAcreage;
        _tmpAcreage = _cursor.getDouble(_cursorIndexOfAcreage);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final int _tmpBedQuantity;
        _tmpBedQuantity = _cursor.getInt(_cursorIndexOfBedQuantity);
        final String _tmpCheckIn;
        if (_cursor.isNull(_cursorIndexOfCheckIn)) {
          _tmpCheckIn = null;
        } else {
          _tmpCheckIn = _cursor.getString(_cursorIndexOfCheckIn);
        }
        final String _tmpCheckOut;
        if (_cursor.isNull(_cursorIndexOfCheckOut)) {
          _tmpCheckOut = null;
        } else {
          _tmpCheckOut = _cursor.getString(_cursorIndexOfCheckOut);
        }
        _item = new Rooms(_tmpID_Hotel,_tmpQuantity,_tmpAvailable,_tmpType,_tmpAcreage,_tmpPrice,_tmpBedQuantity,_tmpCheckIn,_tmpCheckOut);
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
