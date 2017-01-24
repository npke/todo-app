package kenp.happycoding.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lvTodoItems;
    private String[] items = {"Complete CoderSchool Pre-work", "Learn Scrum", "Convert PhuotDi app to RxJava"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTodoItems = (ListView) findViewById(R.id.lvTodoItems);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.todo_item, items);

    }
}
