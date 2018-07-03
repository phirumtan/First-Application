package com.example.tanphirum.firstapplication.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

public class QueryUtils {

    private static StringBuilder sb;

    /**
     * check if row existed
     *
     * @param db
     * @param fieldName
     * @param id
     * @param tableCategory
     * @return
     */
    public static boolean isRowExisted(SQLiteDatabase db, String fieldName, Integer id, String tableCategory) {
        sb = new StringBuilder();
        sb.append("SELECT 1 FROM ");
        sb.append(tableCategory);
        sb.append(" WHERE ");
        sb.append(fieldName);
        sb.append("=");
        sb.append(id);

        Cursor cursor = db.rawQuery(sb.toString(), new String[]{});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    public static boolean isRowExisted(SQLiteDatabase db, String fieldName, String data, String tableCategory) {
        sb = new StringBuilder();
        sb.append("SELECT 1 FROM ");
        sb.append(tableCategory);
        sb.append(" WHERE ");
        sb.append(fieldName);
        sb.append("=");
        sb.append("\"");
        sb.append(data);
        sb.append("\"");

        Cursor cursor = db.rawQuery(sb.toString(), new String[]{});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    /**
     * check row with multi fields
     *
     * @param db
     * @param fieldName
     * @param data
     * @param fieldName1
     * @param data1
     * @param tableCategory
     * @return
     */
    public static boolean isRowExisted(SQLiteDatabase db, String fieldName, String data, String fieldName1, String data1, String tableCategory) {
        sb = new StringBuilder();
        sb.append("SELECT 1 FROM ");
        sb.append(tableCategory);
        sb.append(" WHERE ");
        sb.append(fieldName);
        sb.append("=");
        sb.append("\"");
        sb.append(data);
        sb.append("\"");
        sb.append(" AND ");
        sb.append(fieldName1);
        sb.append("=");
        sb.append("\"");
        sb.append(data1);
        sb.append("\"");

        Cursor cursor = db.rawQuery(sb.toString(), new String[]{});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    /**
     * update only a field in table
     *
     * @param db
     * @param tableName
     * @param rowId
     * @param columnName
     * @param value
     * @return
     */
    public static int updateOneValueInTable(SQLiteDatabase db, String tableName, String nameRowId, String rowId, String columnName, String value) {
        ContentValues cv = new ContentValues();
        cv.put(columnName, value);
        return db.update(tableName, cv, nameRowId + "=?", new String[]{rowId});
    }

    /**
     * get the latest id from table
     *
     * @param tbName table name
     * @return
     */
    public static int getTheLatestIdFromTable(SQLiteDatabase db, String tbName, String fromField) {
        int id = 0;

        String stQuery = "SELECT MAX(" + fromField + ") as " + fromField + " FROM " + tbName + " ORDER BY " + fromField + "  DESC LIMIT -1 OFFSET 0;";

        Cursor cursor = db.rawQuery(stQuery, null);
        if (cursor.moveToLast()) id = cursor.getInt(0);

        return id;
    }

    /**
     * get max date
     *
     * @param db
     * @param tbName
     * @param fromField
     * @return
     */
    public static String getTheMaxFromTable(SQLiteDatabase db, String tbName, String fromField) {
        String date = "";

        String stQuery = "SELECT MAX(" + fromField + ") as " + fromField + " FROM " + tbName + " ORDER BY " + fromField + "  DESC LIMIT -1 OFFSET 0;";

        Cursor cursor = db.rawQuery(stQuery, null);
        if (cursor.moveToLast()) date = cursor.getString(0);

        return date;
    }

    /**
     * delete a row in table by id
     *
     * @param db
     * @param tableName
     * @param fieldName
     * @param articleId
     * @return
     */
    public static int deleteDataInFieldInTable(SQLiteDatabase db, String tableName, String fieldName, Object articleId) {
        int delete = db.delete(tableName, fieldName + " =? ", new String[]{String.valueOf(articleId)});
        db.close();
        return delete;
    }

    public static int deletDataInTable(SQLiteDatabase db, String tableName) {
        int delete = db.delete(tableName, null, null);
        db.close();
        return delete;
    }

    public static int getInt(Cursor cursor, String colName) {
        int result = 0;
        if (!TextUtils.isEmpty(colName)) {
            try {
                if (cursor != null && !cursor.isClosed() && cursor.getCount() > 0) {
                    int index = cursor.getColumnIndex(colName);
                    if (index > -1)
                        result = cursor.getInt(index);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static float getFloat(Cursor cursor, String colName) {
        float result = 0F;
        if (!TextUtils.isEmpty(colName)) {
            try {
                if (cursor != null && !cursor.isClosed() && cursor.getCount() > 0) {
                    int index = cursor.getColumnIndex(colName);
                    if (index > -1)
                        result = cursor.getFloat(index);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String getString(Cursor cursor, String colName) {
        String result = "";
        if (!TextUtils.isEmpty(colName)) {
            try {
                if (cursor != null && !cursor.isClosed() && cursor.getCount() > 0) {
                    int index = cursor.getColumnIndex(colName);
                    if (index > -1)
                        result = cursor.getString(index);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /*public static boolean getBoolean(Cursor cursor, String colName) {
        return BooleanUtils.toBoolean(getInt(cursor, colName));
    }*/
}
