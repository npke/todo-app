package kenp.happycoding.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;


public class TodoAdapter extends ArrayAdapter {

    List<TodoItem> todoItems;

    public TodoAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.todoItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem item = todoItems.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }

        TextView tvTaskName = (TextView) convertView.findViewById(R.id.tvTaskName);
        TextView tvTaskPriority = (TextView) convertView.findViewById(R.id.tvTaskPriority);
        TextView tvTaskDueDate = (TextView) convertView.findViewById(R.id.tvTaskDueDate);

        tvTaskName.setText(item.getName());
        tvTaskPriority.setText(item.getPriority());
        tvTaskDueDate.setText(item.getDueDate());

        return convertView;
    }
}
