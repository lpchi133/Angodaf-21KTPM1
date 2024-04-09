package com.example.angodafake.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class HotelDatabase_Impl extends HotelDatabase {
  private volatile HotelDAO _hotelDAO;

  private volatile BookmarkDAO _bookmarkDAO;

  private volatile PictureDAO _pictureDAO;

  private volatile CommentDAO _commentDAO;

  private volatile PurchaseDAO _purchaseDAO;

  private volatile RoomDAO _roomDAO;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `hotel_db` (`ID_Owner` INTEGER NOT NULL, `name` TEXT NOT NULL, `locationDetail` TEXT NOT NULL, `city` TEXT NOT NULL, `description` TEXT NOT NULL, `conveniences` TEXT NOT NULL, `point` REAL NOT NULL, `profit` REAL NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `bookmark_db` (`ID_Owner` INTEGER NOT NULL, `ID_Hotel` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `picture_db` (`ID_Hotel` INTEGER NOT NULL, `picture` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `comment_db` (`ID_Owner` INTEGER NOT NULL, `ID_Hotel` INTEGER NOT NULL, `time` TEXT NOT NULL, `point` REAL NOT NULL, `detail` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `purchase_db` (`ID_Owner` INTEGER NOT NULL, `ID_Hotel` INTEGER NOT NULL, `ID_Room` INTEGER NOT NULL, `time_booking` TEXT NOT NULL, `time_purchase` TEXT NOT NULL, `total_purchase` REAL NOT NULL, `status_purchase` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `room_db` (`ID_Hotel` INTEGER NOT NULL, `quantity` INTEGER NOT NULL, `available` INTEGER NOT NULL, `type` TEXT NOT NULL, `acreage` REAL NOT NULL, `price` REAL NOT NULL, `bedQuantity` INTEGER NOT NULL, `checkIn` TEXT NOT NULL, `checkOut` TEXT NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd7bc64e4e230ee61c2e86bd4c9ee57d1')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `hotel_db`");
        db.execSQL("DROP TABLE IF EXISTS `bookmark_db`");
        db.execSQL("DROP TABLE IF EXISTS `picture_db`");
        db.execSQL("DROP TABLE IF EXISTS `comment_db`");
        db.execSQL("DROP TABLE IF EXISTS `purchase_db`");
        db.execSQL("DROP TABLE IF EXISTS `room_db`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsHotelDb = new HashMap<String, TableInfo.Column>(9);
        _columnsHotelDb.put("ID_Owner", new TableInfo.Column("ID_Owner", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotelDb.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotelDb.put("locationDetail", new TableInfo.Column("locationDetail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotelDb.put("city", new TableInfo.Column("city", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotelDb.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotelDb.put("conveniences", new TableInfo.Column("conveniences", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotelDb.put("point", new TableInfo.Column("point", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotelDb.put("profit", new TableInfo.Column("profit", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHotelDb.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHotelDb = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHotelDb = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHotelDb = new TableInfo("hotel_db", _columnsHotelDb, _foreignKeysHotelDb, _indicesHotelDb);
        final TableInfo _existingHotelDb = TableInfo.read(db, "hotel_db");
        if (!_infoHotelDb.equals(_existingHotelDb)) {
          return new RoomOpenHelper.ValidationResult(false, "hotel_db(com.example.angodafake.db.Hotel).\n"
                  + " Expected:\n" + _infoHotelDb + "\n"
                  + " Found:\n" + _existingHotelDb);
        }
        final HashMap<String, TableInfo.Column> _columnsBookmarkDb = new HashMap<String, TableInfo.Column>(3);
        _columnsBookmarkDb.put("ID_Owner", new TableInfo.Column("ID_Owner", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarkDb.put("ID_Hotel", new TableInfo.Column("ID_Hotel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBookmarkDb.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBookmarkDb = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBookmarkDb = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBookmarkDb = new TableInfo("bookmark_db", _columnsBookmarkDb, _foreignKeysBookmarkDb, _indicesBookmarkDb);
        final TableInfo _existingBookmarkDb = TableInfo.read(db, "bookmark_db");
        if (!_infoBookmarkDb.equals(_existingBookmarkDb)) {
          return new RoomOpenHelper.ValidationResult(false, "bookmark_db(com.example.angodafake.db.Bookmarks).\n"
                  + " Expected:\n" + _infoBookmarkDb + "\n"
                  + " Found:\n" + _existingBookmarkDb);
        }
        final HashMap<String, TableInfo.Column> _columnsPictureDb = new HashMap<String, TableInfo.Column>(3);
        _columnsPictureDb.put("ID_Hotel", new TableInfo.Column("ID_Hotel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPictureDb.put("picture", new TableInfo.Column("picture", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPictureDb.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPictureDb = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPictureDb = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPictureDb = new TableInfo("picture_db", _columnsPictureDb, _foreignKeysPictureDb, _indicesPictureDb);
        final TableInfo _existingPictureDb = TableInfo.read(db, "picture_db");
        if (!_infoPictureDb.equals(_existingPictureDb)) {
          return new RoomOpenHelper.ValidationResult(false, "picture_db(com.example.angodafake.db.Picture).\n"
                  + " Expected:\n" + _infoPictureDb + "\n"
                  + " Found:\n" + _existingPictureDb);
        }
        final HashMap<String, TableInfo.Column> _columnsCommentDb = new HashMap<String, TableInfo.Column>(6);
        _columnsCommentDb.put("ID_Owner", new TableInfo.Column("ID_Owner", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommentDb.put("ID_Hotel", new TableInfo.Column("ID_Hotel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommentDb.put("time", new TableInfo.Column("time", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommentDb.put("point", new TableInfo.Column("point", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommentDb.put("detail", new TableInfo.Column("detail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCommentDb.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCommentDb = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCommentDb = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCommentDb = new TableInfo("comment_db", _columnsCommentDb, _foreignKeysCommentDb, _indicesCommentDb);
        final TableInfo _existingCommentDb = TableInfo.read(db, "comment_db");
        if (!_infoCommentDb.equals(_existingCommentDb)) {
          return new RoomOpenHelper.ValidationResult(false, "comment_db(com.example.angodafake.db.Comment).\n"
                  + " Expected:\n" + _infoCommentDb + "\n"
                  + " Found:\n" + _existingCommentDb);
        }
        final HashMap<String, TableInfo.Column> _columnsPurchaseDb = new HashMap<String, TableInfo.Column>(8);
        _columnsPurchaseDb.put("ID_Owner", new TableInfo.Column("ID_Owner", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchaseDb.put("ID_Hotel", new TableInfo.Column("ID_Hotel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchaseDb.put("ID_Room", new TableInfo.Column("ID_Room", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchaseDb.put("time_booking", new TableInfo.Column("time_booking", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchaseDb.put("time_purchase", new TableInfo.Column("time_purchase", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchaseDb.put("total_purchase", new TableInfo.Column("total_purchase", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchaseDb.put("status_purchase", new TableInfo.Column("status_purchase", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPurchaseDb.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPurchaseDb = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPurchaseDb = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPurchaseDb = new TableInfo("purchase_db", _columnsPurchaseDb, _foreignKeysPurchaseDb, _indicesPurchaseDb);
        final TableInfo _existingPurchaseDb = TableInfo.read(db, "purchase_db");
        if (!_infoPurchaseDb.equals(_existingPurchaseDb)) {
          return new RoomOpenHelper.ValidationResult(false, "purchase_db(com.example.angodafake.db.Purchase).\n"
                  + " Expected:\n" + _infoPurchaseDb + "\n"
                  + " Found:\n" + _existingPurchaseDb);
        }
        final HashMap<String, TableInfo.Column> _columnsRoomDb = new HashMap<String, TableInfo.Column>(10);
        _columnsRoomDb.put("ID_Hotel", new TableInfo.Column("ID_Hotel", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomDb.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomDb.put("available", new TableInfo.Column("available", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomDb.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomDb.put("acreage", new TableInfo.Column("acreage", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomDb.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomDb.put("bedQuantity", new TableInfo.Column("bedQuantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomDb.put("checkIn", new TableInfo.Column("checkIn", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomDb.put("checkOut", new TableInfo.Column("checkOut", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRoomDb.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRoomDb = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRoomDb = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRoomDb = new TableInfo("room_db", _columnsRoomDb, _foreignKeysRoomDb, _indicesRoomDb);
        final TableInfo _existingRoomDb = TableInfo.read(db, "room_db");
        if (!_infoRoomDb.equals(_existingRoomDb)) {
          return new RoomOpenHelper.ValidationResult(false, "room_db(com.example.angodafake.db.Rooms).\n"
                  + " Expected:\n" + _infoRoomDb + "\n"
                  + " Found:\n" + _existingRoomDb);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d7bc64e4e230ee61c2e86bd4c9ee57d1", "0f37e904d7878e8b709fdb6c780be95e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "hotel_db","bookmark_db","picture_db","comment_db","purchase_db","room_db");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `hotel_db`");
      _db.execSQL("DELETE FROM `bookmark_db`");
      _db.execSQL("DELETE FROM `picture_db`");
      _db.execSQL("DELETE FROM `comment_db`");
      _db.execSQL("DELETE FROM `purchase_db`");
      _db.execSQL("DELETE FROM `room_db`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(HotelDAO.class, HotelDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(BookmarkDAO.class, BookmarkDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(PictureDAO.class, PictureDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(CommentDAO.class, CommentDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(PurchaseDAO.class, PurchaseDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(RoomDAO.class, RoomDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public HotelDAO HotelDAO() {
    if (_hotelDAO != null) {
      return _hotelDAO;
    } else {
      synchronized(this) {
        if(_hotelDAO == null) {
          _hotelDAO = new HotelDAO_Impl(this);
        }
        return _hotelDAO;
      }
    }
  }

  @Override
  public BookmarkDAO BookmarkDAO() {
    if (_bookmarkDAO != null) {
      return _bookmarkDAO;
    } else {
      synchronized(this) {
        if(_bookmarkDAO == null) {
          _bookmarkDAO = new BookmarkDAO_Impl(this);
        }
        return _bookmarkDAO;
      }
    }
  }

  @Override
  public PictureDAO PictureDAO() {
    if (_pictureDAO != null) {
      return _pictureDAO;
    } else {
      synchronized(this) {
        if(_pictureDAO == null) {
          _pictureDAO = new PictureDAO_Impl(this);
        }
        return _pictureDAO;
      }
    }
  }

  @Override
  public CommentDAO CommentDAO() {
    if (_commentDAO != null) {
      return _commentDAO;
    } else {
      synchronized(this) {
        if(_commentDAO == null) {
          _commentDAO = new CommentDAO_Impl(this);
        }
        return _commentDAO;
      }
    }
  }

  @Override
  public PurchaseDAO PurchaseDAO() {
    if (_purchaseDAO != null) {
      return _purchaseDAO;
    } else {
      synchronized(this) {
        if(_purchaseDAO == null) {
          _purchaseDAO = new PurchaseDAO_Impl(this);
        }
        return _purchaseDAO;
      }
    }
  }

  @Override
  public RoomDAO RoomDAO() {
    if (_roomDAO != null) {
      return _roomDAO;
    } else {
      synchronized(this) {
        if(_roomDAO == null) {
          _roomDAO = new RoomDAO_Impl(this);
        }
        return _roomDAO;
      }
    }
  }
}
