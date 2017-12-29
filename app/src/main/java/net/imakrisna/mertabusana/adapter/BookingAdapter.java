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
import net.imakrisna.mertabusana.model.BookingModel;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder>{
    private List<BookingModel> listBooking;
    private Context context;

    public static final String IMAGE_URL="http://192.168.43.124:8000/images/busana/";


    public BookingAdapter(List<BookingModel> listBooking, Context context) {
        this.listBooking = listBooking;
        this.context=context;
    }

    @Override
    public BookingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.row_booking,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookingAdapter.ViewHolder holder, int position) {
        BookingModel bookingModel=listBooking.get(position);
        holder.txtTglPinjam.setText(bookingModel.getTgl_pinjam());
        holder.txtNamaBusana.setText(bookingModel.getNama_busana());
        holder.txtTglKembali.setText(bookingModel.getTgl_kembali());
        holder.txtJumlah.setText(bookingModel.getJumlah_busana());
        Glide.with(this.context).load(IMAGE_URL+bookingModel.getGambar()).into(holder.imgBooking);
    }

    @Override
    public int getItemCount() {
        return listBooking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgBooking;
        TextView txtNamaBusana,txtTglPinjam,txtTglKembali,txtJumlah;
        public ViewHolder(View itemView) {
            super(itemView);
            imgBooking=itemView.findViewById(R.id.img_row_booking);
            txtNamaBusana=itemView.findViewById(R.id.txt_nama_busana_booking);
            txtTglPinjam=itemView.findViewById(R.id.txt_tgl_pinjam);
            txtTglKembali=itemView.findViewById(R.id.txt_tgl_kembali_booking);
            txtJumlah=itemView.findViewById(R.id.txt_jumlah_booking);
        }
    }
}
