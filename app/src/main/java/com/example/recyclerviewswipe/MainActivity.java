package com.example.recyclerviewswipe;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectMode {
    final static String REQUEST_RESULT = "NAME";
    MyAdapter2 myAdapter = null;
    ActionMode mActionMode;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.contextual_menu, menu);
            return true;
        }
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu)
        {
            return false;
        }
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete_all:
                    myAdapter.deleteAllSelected();
                    mode.finish();
                    Toast.makeText(getApplicationContext(), "Quedan "+myAdapter.getItemCount(), Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return false;
            }
        }
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    /************** CUERPO DE LA CLASE ********************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Crea lista de elementos
        List<String> list = new ArrayList<>();
        for(int i=0; i<50; i++) {
            list.add("Pais: " + i);
        }

        // Pilla el recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        // Crea linearLayout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // asigna el layout al recyclerView
        recyclerView.setLayoutManager(linearLayoutManager);
        //myAdapter = new MyAdapter(list);
        myAdapter = new MyAdapter2(list, this);
        recyclerView.setAdapter(myAdapter);

        SwipeController swipeController = new SwipeController();
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    public void addElementView(View v){
        Intent intent = new Intent(this, secondActivity.class);
        //intent.putExtra(Intent.EXTRA_TEXT, text );
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK) {
            //String retorno =  Integer.toString(data.getIntExtra(REQUEST_RESULT,0));
            String retorno = data.getStringExtra(REQUEST_RESULT);
            //((TextView)findViewById(R.id.txtSalida)).setText( retorno );
            myAdapter.add( retorno );
        }
    }


    @Override
    public void onSelect() {
        if (mActionMode != null) return;
        mActionMode = startSupportActionMode(mActionModeCallback);
    }

}
