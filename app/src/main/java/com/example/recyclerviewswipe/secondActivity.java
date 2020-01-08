package com.example.recyclerviewswipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class secondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        // No se reciben parametros
    }

    public void onClickReturn(View view) {
        String datoRespuesta = ((EditText)findViewById(R.id.nuevoElm)).getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.REQUEST_RESULT, datoRespuesta);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
