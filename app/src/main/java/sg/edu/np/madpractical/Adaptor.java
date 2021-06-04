package sg.edu.np.madpractical;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptor extends RecyclerView.Adapter<Viewholder> {
    ArrayList<User> data;

    public Adaptor(ArrayList<User> input) {
        this.data = input;
    }

    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitem, parent, false);
        return new Viewholder(item);
    }

    public void onBindViewHolder(Viewholder holder, int position) {
        User list_item = data.get(position);
        holder.txt1.setText(list_item.getName());
        holder.txt2.setText(list_item.getDescription());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.image.getContext());
                builder.setTitle("Profile");
                builder.setMessage(list_item.getName());
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent info = new Intent(holder.image.getContext(), MainActivity.class);
                        info.putExtra("Index", position);
                        holder.image.getContext().startActivity(info);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int getItemCount() {
        return data.size();
    }
}
