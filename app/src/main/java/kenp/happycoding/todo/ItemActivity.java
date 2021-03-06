package kenp.happycoding.todo;

import android.content.Intent;
import android.provider.CalendarContract;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etTaskName;
    private RadioGroup rbTaskPriority;
    private RadioButton rbHigh, rbMedium, rbLow;
    private DatePicker dpTaskDueDate;

    private Button btAdd, btSave, btDelete;

    private TodoItemDatabase database;

    private TodoItem item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = TodoItemDatabase.getInstance(this);

        etTaskName = (EditText) findViewById(R.id.etTaskName);
        rbTaskPriority = (RadioGroup) findViewById(R.id.rbTaskPriority);
        rbHigh = (RadioButton) findViewById(R.id.rbHighPriority);
        rbMedium = (RadioButton) findViewById(R.id.rbMediumPiority);
        rbLow = (RadioButton) findViewById(R.id.rbLowPiority);

        dpTaskDueDate = (DatePicker) findViewById(R.id.dpTaskDueDate);
        dpTaskDueDate.requestFocus();

        btAdd = (Button) findViewById(R.id.btAdd);
        btSave = (Button) findViewById(R.id.btSave);
        btDelete = (Button) findViewById(R.id.btDelete);

        btAdd.setOnClickListener(this);
        btSave.setOnClickListener(this);
        btDelete.setOnClickListener(this);

        Intent startIntent = getIntent();
        int todoId = startIntent.getIntExtra("todoId", -1);

        if (todoId != -1) {
            item = database.getItem(todoId);

            etTaskName.setText(item.getName());
            if (item.getPriority() == 1)
                rbHigh.setChecked(true);
            else if (item.getPriority() == 2)
                rbMedium.setChecked(true);
            else rbLow.setChecked(true);

            Date dueDate = new Date(item.getDueDate());

            int y = dueDate.getYear();
            int m = dueDate.getMonth();
            int d = dueDate.getDate();

            dpTaskDueDate.updateDate(dueDate.getYear() + 1900, dueDate.getMonth(), dueDate.getDate());
            dpTaskDueDate.setMinDate(System.currentTimeMillis());
            btAdd.setVisibility(View.GONE);
        } else {
            btSave.setVisibility(View.GONE);
            btDelete.setVisibility(View.GONE);
            dpTaskDueDate.setMinDate(System.currentTimeMillis());
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == btAdd.getId()) {
            if (!validateTodoItem()) {
                return;
            }

            TodoItem item = new TodoItem(0, etTaskName.getText().toString(), 1, 0);
            if (rbTaskPriority.getCheckedRadioButtonId() == rbHigh.getId())
                item.setPriority(1);
            else if (rbTaskPriority.getCheckedRadioButtonId() == rbMedium.getId()) {
                item.setPriority(2);
            } else item.setPriority(3);

            int y = dpTaskDueDate.getYear();
            int m = dpTaskDueDate.getMonth();
            int d = dpTaskDueDate.getDayOfMonth();

            Date dueDate = new Date(dpTaskDueDate.getYear() - 1900, dpTaskDueDate.getMonth(), dpTaskDueDate.getDayOfMonth());
            item.setDueDate(dueDate.getTime());

            database.addTodoItem(item);
            finish();
        } else if (view.getId() == btSave.getId()) {
            if (!validateTodoItem()) {
                return;
            }

            item.setName(etTaskName.getText().toString());
            if (rbHigh.isChecked())
                item.setPriority(1);
            else if (rbMedium.isChecked())
                item.setPriority(2);
            else item.setPriority(3);

            database.updateTodoItem(item);
            finish();
        } else {
            database.deleteTodoItem(item);
            finish();
        }
    }

    private boolean validateTodoItem() {
        if (etTaskName.getText().length() == 0) {
            Toast.makeText(this, "Task name can't be blank", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
