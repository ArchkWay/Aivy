package com.example.archek.aivytask;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {

    private ArrayList<Fields> coins = new ArrayList <>();
    private final Callback callback;

    public CoinAdapter(Callback callback) {
        this.callback = callback;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate( R.layout.item, viewGroup, false );
        final ViewHolder holder = new ViewHolder( itemView );
        itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fields fields  = coins.get(holder.getAdapterPosition());
                callback.onCoinClick( fields );
            }
        } );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Fields fields = coins.get( position );
        viewHolder.name.setText( fields.getName() );
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    public void addAll(List<Fields> coinsAdd){
        coins.addAll( coinsAdd );
        notifyDataSetChanged();
    }

    public interface Callback {
        void onCoinClick(Fields fields);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }

}
