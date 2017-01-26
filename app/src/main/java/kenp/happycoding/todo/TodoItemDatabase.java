package kenp.happycoding.todo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TodoItemDatabase extends SQLiteOpenHelper {

    private static TodoItemDatabase instance;

    private TodoItemDatabase(Context context) {
        super(context, "TodoDatabase", null, 1);
    }

    public static synchronized TodoItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new TodoItemDatabase(context.getApplicationContext());
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_ITEM_TABLE = "CREATE TABLE Items(Id INTEGER PRIMARY KEY AUTOINCREMENT"
                + ",Name TEXT,DueDate DateTime, Priority INTEGER)";

        sqLiteDatabase.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addTodoItem(TodoItem item) {
        String ADD_ITEM = "INSERT INTO Items VALUES(null," + item.getName() + "," + item.getDueDate()
                + "," + item.getPriority();

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(ADD_ITEM);
        db.close();
    }

    public List<TodoItem> getTodoItems() {

        List<TodoItem> todoItems = new ArrayList<TodoItem>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Items", null);
        if (cursor.moveToFirst()) {
            do {
                TodoItem tmp = new TodoItem(cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getInt(cursor.getColumnIndex("priority")),
                                cursor.getLong(cursor.getColumnIndex("dueDate")));

                todoItems.add(tmp);
            } while (cursor.moveToNext());
        }

        return todoItems;
    }
}
