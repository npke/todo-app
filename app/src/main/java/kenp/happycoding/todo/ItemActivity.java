package kenp.happycoding.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Date;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTaskName;
    private RadioGroup rbTaskPriority;
    private RadioButton rbHigh, rbMedium, rbLow;
    private DatePicker dpTaskDueDate;

    private Button btAdd;

    private TodoItemDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        database = TodoItemDatabase.getInstance(this);

        etTaskName = (EditText) findViewById(R.id.etTaskName);
        rbTaskPriority = (RadioGroup) findViewById(R.id.rbTaskPriority);
        rbHigh = (RadioButton) findViewById(R.id.rbHighPriority);
        rbMedium = (RadioButton) findViewById(R.id.rbMediumPiority);
        rbLow = (RadioButton) findViewById(R.id.rbLowPiority);

        dpTaskDueDate = (DatePicker) findViewById(R.id.dpTaskDueDate);

        btAdd = (Button) findViewById(R.id.btAdd);

        btAdd.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == btAdd.getId()) {
            TodoItem item = new TodoItem(0, etTaskName.getText().toString(), 1, 0);
            if (rbTaskPriority.getCheckedRadioButtonId() == rbHigh.getId())
                item.setPriority(1);
            else if (rbTaskPriority.getCheckedRadioButtonId() == rbMedium.getId()) {
                item.setPriority(2);
            } else item.setPriority(3);

            Date dueDate = new Date(dpTaskDueDate.getYear() - 1900, dpTaskDueDate.getMonth(), dpTaskDueDate.getDayOfMonth());
            item.setDueDate(dueDate.getTime());

            database.addTodoItem(item);
            finish();
        }
    }
}
