package ru.uaz_prokat.purchases;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
    Button btnPurchasesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editTextName);
        btnAddName = (Button)findViewById(R.id.btnAddName);
        btnPurchasesList = (Button)findViewById(R.id.btnPurchasesList);

        addData();
        viewPurchaseList();
    }

    public void addData() {
        btnAddName.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     boolean isInserted = myDb.insertData(editName.getText().toString());
                        if (isInserted =true)
                            Toast.makeText(MainActivity.this,editName.getText().toString() + " добавлен(а)", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Ошибка", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewPurchaseList() {
        btnPurchasesList.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount()==0) {
                            showMessage("Ошибка", "Ничего не найдено");
                            return;
                        }
                            StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append(res.getString(0) + "\n");
                        }
                        showMessage("Список", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
