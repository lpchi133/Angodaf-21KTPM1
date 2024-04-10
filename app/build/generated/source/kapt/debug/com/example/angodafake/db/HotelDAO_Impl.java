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
public final class HotelDAO_Impl implements HotelDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Hotel> __insertionAdapterOfHotel;

  private final EntityDeletionOrUpdateAdapter<Hotel> __deletionAdapterOfHotel;

  private final EntityDeletionOrUpdateAdapter<Hotel> __updateAdapterOfHotel;

  public HotelDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHotel = new EntityInsertionAdapter<Hotel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `hotel_db` (`ID_Owner`,`name`,`locationDetail`,`city`,`description`,`conveniences`,`point`,`profit`,`checkIn`,`checkOut`,`id`) VALUES (?,?,?,?,?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Hotel entity) {
        statement.bindLong(1, entity.getID_Owner());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getLocationDetail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getLocationDetail());
        }
        if (entity.getCity() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCity());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getConvenience() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getConvenience());
        }
        statement.bindDouble(7, entity.getPoint());
        statement.bindDouble(8, entity.getProfit());
        if (entity.getCheckIn() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getCheckIn());
        }
        if (entity.getCheckOut() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCheckOut());
        }
        statement.bindLong(11, entity.getId());
      }
    };
    this.__deletionAdapterOfHotel = new EntityDeletionOrUpdateAdapter<Hotel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `hotel_db` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Hotel entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfHotel = new EntityDeletionOrUpdateAdapter<Hotel>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `hotel_db` SET `ID_Owner` = ?,`name` = ?,`locationDetail` = ?,`city` = ?,`description` = ?,`conveniences` = ?,`point` = ?,`profit` = ?,`checkIn` = ?,`checkOut` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Hotel entity) {
        statement.bindLong(1, entity.getID_Owner());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getLocationDetail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getLocationDetail());
        }
        if (entity.getCity() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCity());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getConvenience() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getConvenience());
        }
        statement.bindDouble(7, entity.getPoint());
        statement.bindDouble(8, entity.getProfit());
        if (entity.getCheckIn() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getCheckIn());
        }
        if (entity.getCheckOut() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCheckOut());
        }
        statement.bindLong(11, entity.getId());
        statement.bindLong(12, entity.getId());
      }
    };
  }

  @Override
  public void insertHotel(final Hotel hotel) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfHotel.insert(hotel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteHotel(final Hotel hotel) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfHotel.handle(hotel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateHotel(final Hotel hotel) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfHotel.handle(hotel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Hotel> getHotelList() {
    final String _sql = "Select * from hotel_db";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Owner");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfLocationDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "locationDetail");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfConvenience = CursorUtil.getColumnIndexOrThrow(_cursor, "conveniences");
      final int _cursorIndexOfPoint = CursorUtil.getColumnIndexOrThrow(_cursor, "point");
      final int _cursorIndexOfProfit = CursorUtil.getColumnIndexOrThrow(_cursor, "profit");
      final int _cursorIndexOfCheckIn = CursorUtil.getColumnIndexOrThrow(_cursor, "checkIn");
      final int _cursorIndexOfCheckOut = CursorUtil.getColumnIndexOrThrow(_cursor, "checkOut");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Hotel> _result = new ArrayList<Hotel>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Hotel _item;
        final int _tmpID_Owner;
        _tmpID_Owner = _cursor.getInt(_cursorIndexOfIDOwner);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpLocationDetail;
        if (_cursor.isNull(_cursorIndexOfLocationDetail)) {
          _tmpLocationDetail = null;
        } else {
          _tmpLocationDetail = _cursor.getString(_cursorIndexOfLocationDetail);
        }
        final String _tmpCity;
        if (_cursor.isNull(_cursorIndexOfCity)) {
          _tmpCity = null;
        } else {
          _tmpCity = _cursor.getString(_cursorIndexOfCity);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpConvenience;
        if (_cursor.isNull(_cursorIndexOfConvenience)) {
          _tmpConvenience = null;
        } else {
          _tmpConvenience = _cursor.getString(_cursorIndexOfConvenience);
        }
        final double _tmpPoint;
        _tmpPoint = _cursor.getDouble(_cursorIndexOfPoint);
        final double _tmpProfit;
        _tmpProfit = _cursor.getDouble(_cursorIndexOfProfit);
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
        _item = new Hotel(_tmpID_Owner,_tmpName,_tmpLocationDetail,_tmpCity,_tmpDescription,_tmpConvenience,_tmpPoint,_tmpProfit,_tmpCheckIn,_tmpCheckOut);
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
  public Hotel getHotelByID(final int hotel_id) {
    final String _sql = "SELECT * FROM hotel_db WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, hotel_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Owner");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfLocationDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "locationDetail");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfConvenience = CursorUtil.getColumnIndexOrThrow(_cursor, "conveniences");
      final int _cursorIndexOfPoint = CursorUtil.getColumnIndexOrThrow(_cursor, "point");
      final int _cursorIndexOfProfit = CursorUtil.getColumnIndexOrThrow(_cursor, "profit");
      final int _cursorIndexOfCheckIn = CursorUtil.getColumnIndexOrThrow(_cursor, "checkIn");
      final int _cursorIndexOfCheckOut = CursorUtil.getColumnIndexOrThrow(_cursor, "checkOut");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final Hotel _result;
      if (_cursor.moveToFirst()) {
        final int _tmpID_Owner;
        _tmpID_Owner = _cursor.getInt(_cursorIndexOfIDOwner);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpLocationDetail;
        if (_cursor.isNull(_cursorIndexOfLocationDetail)) {
          _tmpLocationDetail = null;
        } else {
          _tmpLocationDetail = _cursor.getString(_cursorIndexOfLocationDetail);
        }
        final String _tmpCity;
        if (_cursor.isNull(_cursorIndexOfCity)) {
          _tmpCity = null;
        } else {
          _tmpCity = _cursor.getString(_cursorIndexOfCity);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpConvenience;
        if (_cursor.isNull(_cursorIndexOfConvenience)) {
          _tmpConvenience = null;
        } else {
          _tmpConvenience = _cursor.getString(_cursorIndexOfConvenience);
        }
        final double _tmpPoint;
        _tmpPoint = _cursor.getDouble(_cursorIndexOfPoint);
        final double _tmpProfit;
        _tmpProfit = _cursor.getDouble(_cursorIndexOfProfit);
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
        _result = new Hotel(_tmpID_Owner,_tmpName,_tmpLocationDetail,_tmpCity,_tmpDescription,_tmpConvenience,_tmpPoint,_tmpProfit,_tmpCheckIn,_tmpCheckOut);
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

  @Override
  public Hotel getHotelByLocation(final String hotel_location) {
    final String _sql = "SELECT * FROM hotel_db WHERE locationDetail = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (hotel_location == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotel_location);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Owner");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfLocationDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "locationDetail");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfConvenience = CursorUtil.getColumnIndexOrThrow(_cursor, "conveniences");
      final int _cursorIndexOfPoint = CursorUtil.getColumnIndexOrThrow(_cursor, "point");
      final int _cursorIndexOfProfit = CursorUtil.getColumnIndexOrThrow(_cursor, "profit");
      final int _cursorIndexOfCheckIn = CursorUtil.getColumnIndexOrThrow(_cursor, "checkIn");
      final int _cursorIndexOfCheckOut = CursorUtil.getColumnIndexOrThrow(_cursor, "checkOut");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final Hotel _result;
      if (_cursor.moveToFirst()) {
        final int _tmpID_Owner;
        _tmpID_Owner = _cursor.getInt(_cursorIndexOfIDOwner);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpLocationDetail;
        if (_cursor.isNull(_cursorIndexOfLocationDetail)) {
          _tmpLocationDetail = null;
        } else {
          _tmpLocationDetail = _cursor.getString(_cursorIndexOfLocationDetail);
        }
        final String _tmpCity;
        if (_cursor.isNull(_cursorIndexOfCity)) {
          _tmpCity = null;
        } else {
          _tmpCity = _cursor.getString(_cursorIndexOfCity);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpConvenience;
        if (_cursor.isNull(_cursorIndexOfConvenience)) {
          _tmpConvenience = null;
        } else {
          _tmpConvenience = _cursor.getString(_cursorIndexOfConvenience);
        }
        final double _tmpPoint;
        _tmpPoint = _cursor.getDouble(_cursorIndexOfPoint);
        final double _tmpProfit;
        _tmpProfit = _cursor.getDouble(_cursorIndexOfProfit);
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
        _result = new Hotel(_tmpID_Owner,_tmpName,_tmpLocationDetail,_tmpCity,_tmpDescription,_tmpConvenience,_tmpPoint,_tmpProfit,_tmpCheckIn,_tmpCheckOut);
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

  @Override
  public Hotel getHotelByName(final String hotel_name) {
    final String _sql = "SELECT * FROM hotel_db WHERE name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (hotel_name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotel_name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Owner");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfLocationDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "locationDetail");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfConvenience = CursorUtil.getColumnIndexOrThrow(_cursor, "conveniences");
      final int _cursorIndexOfPoint = CursorUtil.getColumnIndexOrThrow(_cursor, "point");
      final int _cursorIndexOfProfit = CursorUtil.getColumnIndexOrThrow(_cursor, "profit");
      final int _cursorIndexOfCheckIn = CursorUtil.getColumnIndexOrThrow(_cursor, "checkIn");
      final int _cursorIndexOfCheckOut = CursorUtil.getColumnIndexOrThrow(_cursor, "checkOut");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final Hotel _result;
      if (_cursor.moveToFirst()) {
        final int _tmpID_Owner;
        _tmpID_Owner = _cursor.getInt(_cursorIndexOfIDOwner);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpLocationDetail;
        if (_cursor.isNull(_cursorIndexOfLocationDetail)) {
          _tmpLocationDetail = null;
        } else {
          _tmpLocationDetail = _cursor.getString(_cursorIndexOfLocationDetail);
        }
        final String _tmpCity;
        if (_cursor.isNull(_cursorIndexOfCity)) {
          _tmpCity = null;
        } else {
          _tmpCity = _cursor.getString(_cursorIndexOfCity);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpConvenience;
        if (_cursor.isNull(_cursorIndexOfConvenience)) {
          _tmpConvenience = null;
        } else {
          _tmpConvenience = _cursor.getString(_cursorIndexOfConvenience);
        }
        final double _tmpPoint;
        _tmpPoint = _cursor.getDouble(_cursorIndexOfPoint);
        final double _tmpProfit;
        _tmpProfit = _cursor.getDouble(_cursorIndexOfProfit);
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
        _result = new Hotel(_tmpID_Owner,_tmpName,_tmpLocationDetail,_tmpCity,_tmpDescription,_tmpConvenience,_tmpPoint,_tmpProfit,_tmpCheckIn,_tmpCheckOut);
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
