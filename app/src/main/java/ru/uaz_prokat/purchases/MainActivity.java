package ru.uaz_prokat.purchases;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.uaz_prokat.purchases.lib.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName;
    Button btnAddName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editTextName);
        btnAddName = (Button) findViewById(R.id.btnAddName);
    }

    public void addData() {
        btnAddName.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     boolean isInserted = myDb.insertData(editName.getText().toString());
                        if (isInserted =true)
                            Toast.makeText(MainActivity.this,"Покупка добавлена", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Ошибка", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
