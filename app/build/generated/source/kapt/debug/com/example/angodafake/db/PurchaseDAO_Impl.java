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
public final class PurchaseDAO_Impl implements PurchaseDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Purchase> __insertionAdapterOfPurchase;

  private final EntityDeletionOrUpdateAdapter<Purchase> __deletionAdapterOfPurchase;

  private final EntityDeletionOrUpdateAdapter<Purchase> __updateAdapterOfPurchase;

  public PurchaseDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPurchase = new EntityInsertionAdapter<Purchase>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `purchase_db` (`ID_Owner`,`ID_Hotel`,`ID_Room`,`time_booking`,`time_purchase`,`total_purchase`,`status_purchase`,`id`) VALUES (?,?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Purchase entity) {
        statement.bindLong(1, entity.getID_Owner());
        statement.bindLong(2, entity.getID_Hotel());
        statement.bindLong(3, entity.getID_Room());
        if (entity.getTime_booking() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTime_booking());
        }
        if (entity.getTime_purchase() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTime_purchase());
        }
        statement.bindDouble(6, entity.getTotal_purchase());
        final int _tmp = entity.getStatus_purchase() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getId());
      }
    };
    this.__deletionAdapterOfPurchase = new EntityDeletionOrUpdateAdapter<Purchase>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `purchase_db` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Purchase entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPurchase = new EntityDeletionOrUpdateAdapter<Purchase>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `purchase_db` SET `ID_Owner` = ?,`ID_Hotel` = ?,`ID_Room` = ?,`time_booking` = ?,`time_purchase` = ?,`total_purchase` = ?,`status_purchase` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Purchase entity) {
        statement.bindLong(1, entity.getID_Owner());
        statement.bindLong(2, entity.getID_Hotel());
        statement.bindLong(3, entity.getID_Room());
        if (entity.getTime_booking() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTime_booking());
        }
        if (entity.getTime_purchase() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTime_purchase());
        }
        statement.bindDouble(6, entity.getTotal_purchase());
        final int _tmp = entity.getStatus_purchase() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getId());
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public void insertPurchase(final Purchase purchase) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPurchase.insert(purchase);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletePurchase(final Purchase purchase) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPurchase.handle(purchase);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatePurchase(final Purchase purchase) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPurchase.handle(purchase);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Purchase> getPurchaseList() {
    final String _sql = "Select * from purchase_db";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIDOwner = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Owner");
      final int _cursorIndexOfIDHotel = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Hotel");
      final int _cursorIndexOfIDRoom = CursorUtil.getColumnIndexOrThrow(_cursor, "ID_Room");
      final int _cursorIndexOfTimeBooking = CursorUtil.getColumnIndexOrThrow(_cursor, "time_booking");
      final int _cursorIndexOfTimePurchase = CursorUtil.getColumnIndexOrThrow(_cursor, "time_purchase");
      final int _cursorIndexOfTotalPurchase = CursorUtil.getColumnIndexOrThrow(_cursor, "total_purchase");
      final int _cursorIndexOfStatusPurchase = CursorUtil.getColumnIndexOrThrow(_cursor, "status_purchase");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final List<Purchase> _result = new ArrayList<Purchase>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Purchase _item;
        final int _tmpID_Owner;
        _tmpID_Owner = _cursor.getInt(_cursorIndexOfIDOwner);
        final int _tmpID_Hotel;
        _tmpID_Hotel = _cursor.getInt(_cursorIndexOfIDHotel);
        final int _tmpID_Room;
        _tmpID_Room = _cursor.getInt(_cursorIndexOfIDRoom);
        final String _tmpTime_booking;
        if (_cursor.isNull(_cursorIndexOfTimeBooking)) {
          _tmpTime_booking = null;
        } else {
          _tmpTime_booking = _cursor.getString(_cursorIndexOfTimeBooking);
        }
        final String _tmpTime_purchase;
        if (_cursor.isNull(_cursorIndexOfTimePurchase)) {
          _tmpTime_purchase = null;
        } else {
          _tmpTime_purchase = _cursor.getString(_cursorIndexOfTimePurchase);
        }
        final double _tmpTotal_purchase;
        _tmpTotal_purchase = _cursor.getDouble(_cursorIndexOfTotalPurchase);
        final boolean _tmpStatus_purchase;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfStatusPurchase);
        _tmpStatus_purchase = _tmp != 0;
        _item = new Purchase(_tmpID_Owner,_tmpID_Hotel,_tmpID_Room,_tmpTime_booking,_tmpTime_purchase,_tmpTotal_purchase,_tmpStatus_purchase);
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
