package work.frame.com.hzcitizenframework.datebase.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import work.frame.com.hzcitizenframework.utils.LogUtil;

public class DataBaseHelper extends SQLiteOpenHelper implements DataBase {

    public static int ver = 2;

    private SQLiteDatabase dbOperator;

    private Context context;

    // 添加 “删除”字段：默认如果 服务端没有的，客户端有，则客户端需要将其逻辑删除。（逻辑待定）
    private String[] cols = {"id", "appName", "downLoadUrl", "priority",
            "iconUrl", "status", "packageName", "intentArg", "version"};

    public DataBaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, DATABASE_NAME, null, ver);
        this.context = context;
    }

    public void initDB() {
        dbOperator = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sqlB = new StringBuffer();
        sqlB.append("create table IF NOT EXISTS " + Table_NAME + " ( ");
        for (String col : cols) {
            if (col.equalsIgnoreCase("priority")) {
                sqlB.append(col + "  smallint  not null ,"); // UNIQUE
            } else if (col.equalsIgnoreCase("packageName")) {
                sqlB.append(col + "  text not null  UNIQUE ,");
            } else if (col.equalsIgnoreCase("id")) {
                sqlB.append(col + "  text not null  UNIQUE ,");
            } else {
                sqlB.append(col + "  text not null ,");
            }
        }
        sqlB.deleteCharAt(sqlB.length() - 1);
        sqlB.append(" ) ");

        LogUtil.d("sqlB:" + sqlB.toString());

        db.execSQL(sqlB.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // 将packageName两个字段搞成 UNIQUE，如果这两个字段中的值出现重复，那么该值从第二次出现，就不会再被插入到数据库中
    public void createTable() {
        StringBuffer sqlB = new StringBuffer();
        sqlB.append("create table IF NOT EXISTS " + Table_NAME + " ( ");
        for (String col : cols) {
            if (col.equalsIgnoreCase("priority")) {
                sqlB.append(col + "  smallint  not null ,"); // UNIQUE
            } else if (col.equalsIgnoreCase("id")) {
                sqlB.append(col + "  text not null  UNIQUE ,");
            } else if (col.equalsIgnoreCase("packageName")) {
                sqlB.append(col + "  text not null  UNIQUE ,");
            } else {
                sqlB.append(col + "  text not null ,");
            }
        }
        sqlB.deleteCharAt(sqlB.length() - 1);
        sqlB.append(" ) ");

        LogUtil.d("sqlB:" + sqlB.toString());

        dbOperator.execSQL(sqlB.toString());
    }


    /**
     * **** 测试用的方法 *****
     */
    public void deleteAll() {
        dbOperator.delete(Table_NAME, null, null);
    }


}
