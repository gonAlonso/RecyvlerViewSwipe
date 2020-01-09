package com.example.recyclerviewswipe;

import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;


public class MyAdapter3 extends RecyclerView.Adapter<MyAdapter3.MyViewHolder> implements SelectMode {  //Added implements

    private SelectMode mListener;
    private List<String> nameList;
    //Added following list
    private SparseArray<Boolean> selectedList = new SparseArray<>();

    public MyAdapter3(List<String> list, SelectMode listener) { //Added listener to parameters
        nameList = list;
        mListener=listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item3, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final String name = nameList.get(position);
        holder.textView.setText(name);
        holder.itemView.setSelected(selectedList.get(position,false)); //New (for There's more)

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.setSelected(!holder.itemView.isSelected());
                if (holder.itemView.isSelected()) {
                    selectedList.put(position, true);
                } else {
                    selectedList.remove(position);
                }
                onSelect();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (nameList==null) {
            return 0;
        } else {
            return nameList.size();
        }
    }

    private void remove(int position) {
        nameList.remove(position);
        notifyDataSetChanged();
    }
    public void deleteItem(int pos){
        remove(pos);
    }

    //New method
    public void deleteAllSelected() {
        if (selectedList.size()==0) { return; }
        for (int index = nameList.size()-1; index >=0; index--) {
            if (selectedList.get(index,false)) {
                remove(index);
            }
        }
        selectedList.clear();
    }

    @Override
    public void onSelect() {
        if (mListener!=null) {
            mListener.onSelect();
        }
    }

    public void add(String elm){
        nameList.add( elm );
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(View itemVieww) {
            super(itemVieww);
            textView = itemView.findViewById(R.id.task_name);
        }
    }
}



