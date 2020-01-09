package com.example.recyclerviewswipe;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeActionHelper extends ItemTouchHelper.SimpleCallback {

    //my custom adapter
    private MyAdapter3 slAdapter;

    public SwipeActionHelper(MyAdapter3 adapter){
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        slAdapter = adapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i("ZALO-SWIPE", "Swipeado: " + String.valueOf(direction));
        int position = viewHolder.getAdapterPosition();
        slAdapter.deleteItem(position);
    }
}