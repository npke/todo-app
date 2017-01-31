package kenp.happycoding.todo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
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
        seedDatabase(sqLiteDatabase);
    }

    private void seedDatabase(SQLiteDatabase db) {
        ArrayList<TodoItem> initial_todo = new ArrayList<>();
        initial_todo.add(new TodoItem(0, "Complete CoderSchool pre-work", 1, new Date().getTime()));
        initial_todo.add(new TodoItem(1, "Travel to Saigon", 2, new Date().getTime()));
        initial_todo.add(new TodoItem(2, "Meet with Tuan", 3, new Date().getTime()));

        final String SEED_DATABASE = "INSERT INTO Items VALUES"
                + "(null,\"" + initial_todo.get(0).getName() + "\"," + initial_todo.get(0).getDueDate() + "," + initial_todo.get(0).getPriority() + "), "
        + "(null,\"" + initial_todo.get(1).getName() + "\"," + initial_todo.get(1).getDueDate() + "," + initial_todo.get(1).getPriority() + "), "
        + "(null,\"" + initial_todo.get(2).getName() + "\"," + initial_todo.get(2).getDueDate() + "," + initial_todo.get(2).getPriority() + ")";
        db.execSQL(SEED_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addTodoItem(TodoItem item) {
        String ADD_ITEM = "INSERT INTO Items VALUES(null,\"" + item.getName() + "\"," + item.getDueDate()
                + "," + item.getPriority() + ")";

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
                TodoItem tmp = new TodoItem(cursor.getInt(cursor.getColumnIndex("Id")),
                        cursor.getString(cursor.getColumnIndex("Name")),
                        cursor.getInt(cursor.getColumnIndex("Priority")),
                                cursor.getLong(cursor.getColumnIndex("DueDate")));

                todoItems.add(tmp);
            } while (cursor.moveToNext());
        }

        return todoItems;
    }

    public TodoItem getItem(int todoId) {
        TodoItem item = null;

        String query = String.format("SELECT * FROM Items WHERE Id = %d", todoId);
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            item = new TodoItem(cursor.getInt(cursor.getColumnIndex("Id")),
                    cursor.getString(cursor.getColumnIndex("Name")),
                    cursor.getInt(cursor.getColumnIndex("Priority")),
                    cursor.getLong(cursor.getColumnIndex("DueDate")));
        }

        return item;
    }

    public void updateTodoItem(TodoItem item) {
        String query = String.format("UPDATE Items SET Name=\"%s\",DueDate=%d,Priority=%d WHERE Id=%d", item.getName(), item.getDueDate(), item.getPriority(), item.getId());
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }

    public void deleteTodoItem(TodoItem item) {
        String query = String.format("DELETE FROM Items WHERE Id=%d", item.getId());
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
    }
}
