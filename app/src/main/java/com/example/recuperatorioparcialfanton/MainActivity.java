package com.example.recuperatorioparcialfanton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private DataBaseHelper db;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private MainActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.habilitar);
        listView = findViewById(R.id.listaDatos);
        //String[] datosIniciales = {"DATO 1"};
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);
        db = new DataBaseHelper(this);
        List<String> datos = db.getDatos();
        arrayAdapter.addAll(datos);

    }

    public void agregar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setTitle("Ingresar dato:");

        builder.setPositiveButton("GUARDAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               db.addDato(input.getText().toString());
                List<String> datos = db.getDatos();
                arrayAdapter = new ArrayAdapter<String>(self, android.R.layout.simple_list_item_1);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.addAll(datos);
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    };

    public void habilitarDeshaiblitar(View view) {
        if (button.isEnabled()) {
            button.setEnabled(false);
        }
        else {
            button.setEnabled(true);
        }

    }
}