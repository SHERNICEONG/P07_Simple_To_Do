package sg.edu.rp.c346.id22017424.p07_simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddRemove;
    EditText etTask;
    Button btnAdd;
    Button btnRemove;
    Button btnClear;
    ListView lvToDo;
    ArrayList<String> alToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddRemove = findViewById(R.id.spinner);
        etTask = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnRemove = findViewById(R.id.buttonRemove);
        btnClear = findViewById(R.id.buttonClear);
        lvToDo = findViewById(R.id.listViewToDo);

        alToDo = new ArrayList<String>();

        // create and set adapter
        ArrayAdapter aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        // spinner
        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        // code for add selected
                        // disable remove button
                        btnRemove.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTask.setHint("Type in a new task here");
                        // add new task when button is clicked
                        btnAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String newTask = etTask.getText().toString();
                                alToDo.add(newTask);
                                aaToDo.notifyDataSetChanged();
                            }
                        });
                        break;
                    case 1:
                        // code for remove selected
                        // disable add button
                        btnRemove.setEnabled(true);
                        btnAdd.setEnabled(false);
                        etTask.setHint("Type in the index of the task to be removed");
                        btnRemove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int pos = Integer.parseInt(etTask.getText().toString());
                                String display = "";
                                // handle exceptional cases when deleting a task
                                if (alToDo.isEmpty()){
                                    display = "You don't have any task to remove";
                                    Toast.makeText(MainActivity.this, display, Toast.LENGTH_SHORT).show();

                                } else if (pos > alToDo.size()) {
                                    display = "Wrong index number";
                                    Toast.makeText(MainActivity.this, display, Toast.LENGTH_SHORT).show();

                                } else if (!alToDo.isEmpty() && pos <= alToDo.size() && pos<=0) {
                                    // remove colour when button is clicked
                                    alToDo.remove(Integer.parseInt(etTask.getText().toString()));
                                    aaToDo.notifyDataSetChanged();
                                }
                            }
                        });
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // remove task when button is clicked
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });

    }

}