package superhumancrew.com.saveus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by dedie on 1/10/18.
 */

public class AdapterSaveUs extends RecyclerView.Adapter<AdapterSaveUs.ViewHolder> {
    private ArrayList<SaveUs> daftarSaveUs;
    private Context context;

    public AdapterSaveUs(ArrayList<SaveUs> daftarSaveUs, Context context) {
        this.daftarSaveUs = daftarSaveUs;
        this.context = context;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTanggal, txtLat, txtKejadian, txtLng, txtNama;
        Button btnMap;

        ViewHolder(View v){
            super(v);
            context = itemView.getContext();

            txtNama = (TextView)v.findViewById(R.id.txtNama);
            txtTanggal = (TextView)v.findViewById(R.id.txtTanggal);
            txtLat = (TextView)v.findViewById(superhumancrew.com.saveus.R.id.txtLat);
            txtKejadian = (TextView)v.findViewById(superhumancrew.com.saveus.R.id.txtKejadian);
            txtLng = (TextView)v.findViewById(superhumancrew.com.saveus.R.id.txtLng);
            btnMap = (Button)v.findViewById(R.id.btnLokasi);

            btnMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context,MapsActivity.class);
                    context.startActivity(i);
                }
            });


        }
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saveus, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return  viewHolder;
    }


    public void onBindViewHolder(ViewHolder holder, final int position) {
        //final String kordinat = daftarSaveUs.get(position).getKejadian();




        final String waktu = daftarSaveUs.get(position).getWaktu();
        final String nama = daftarSaveUs.get(position).getNama();
        final String kejadian = daftarSaveUs.get(position).getKejadian();
        final String lat = daftarSaveUs.get(position).getLat();
        final String lng = daftarSaveUs.get(position).getLng();
        //final String kordinat = "Lat : "+lat+", Long : "+lng+" ";
        holder.txtTanggal.setText(waktu);
        holder.txtNama.setText(nama);
        holder.txtLat.setText("Latitude    : "+lat);
        holder.txtKejadian.setText(kejadian);
        holder.txtLng.setText("Longitude   : "+lng);

        holder.btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context,MapsActivity.class);
                i.putExtra("lat", lat);
                i.putExtra("lng", lng);
                context.startActivity(i);

            }
        });
    }


    public int getItemCount() {
        return daftarSaveUs.size();
    }

}
