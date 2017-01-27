package kenp.happycoding.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView lvTodoItems;
    private String[] items = {"Complete CoderSchool Pre-work", "Learn Scrum", "Convert PhuotDi app to RxJava"};
    private TodoItemDatabase database;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = TodoItemDatabase.getInstance(this);

        todoAdapter = new TodoAdapter(this, R.layout.todo_item, database.getTodoItems());
        lvTodoItems = (ListView) findViewById(R.id.lvTodoItems);

        lvTodoItems.setAdapter(todoAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.miAdd) {
            startActivity(new Intent(this, ItemActivity.class));
            return true;
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoAdapter = new TodoAdapter(this, R.layout.todo_item, database.getTodoItems());
        lvTodoItems = (ListView) findViewById(R.id.lvTodoItems);
    }
}
