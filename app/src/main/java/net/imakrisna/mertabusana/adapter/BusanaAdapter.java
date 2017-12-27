package net.imakrisna.mertabusana.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.imakrisna.mertabusana.R;
import net.imakrisna.mertabusana.model.BusanaModel;

import java.util.List;

/**
 * Created by Mustika on 28/12/2017.
 */

public class BusanaAdapter extends RecyclerView.Adapter<BusanaAdapter.ViewHolder>{
    private List<BusanaModel> listBusana;
    private Context context;

    public static final String IMAGE_URL="http://192.168.43.124:8000/images/busana/";

    public BusanaAdapter(List<BusanaModel> listBusana,Context context) {
        this.listBusana = listBusana;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_banten,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BusanaModel busanaModel=listBusana.get(position);
        holder.txtNamaBusana.setText(busanaModel.getNama_busana());
        Glide.with(this.context).load(IMAGE_URL+busanaModel.getGambar()).into(holder.imgBusana);
    }

    @Override
    public int getItemCount() {
        return listBusana.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBusana;
        TextView txtNamaBusana;
        public ViewHolder(View itemView) {
            super(itemView);
            imgBusana=itemView.findViewById(R.id.img_row_busana);
            txtNamaBusana=itemView.findViewById(R.id.txt_row_busana);
        }
    }
}
