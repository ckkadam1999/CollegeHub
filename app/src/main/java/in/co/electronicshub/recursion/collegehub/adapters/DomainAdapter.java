package in.co.electronicshub.recursion.collegehub.adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GenericRequestBuilder;

import java.util.ArrayList;

import in.co.electronicshub.recursion.collegehub.R;
import in.co.electronicshub.recursion.collegehub.utils.Domain;

public class DomainAdapter extends RecyclerView.Adapter<DomainAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Domain> domains;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.domains, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Domain domain = domains.get(position);
        Glide.with(context).load(domain.getIcon()).into(holder.imageView);
        holder.titleTextView.setText(domain.getTitle());
        holder.countTextView.setText("Total Projects: "+domain.getCount());
        holder.itemView.setTag(domain.getId());
    }

    public DomainAdapter(Context context, ArrayList<Domain> domains) {
        this.context = context;
        this.domains = domains;
    }

    @Override
    public int getItemCount() {
        return domains.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView countTextView, titleTextView;
        public ImageView imageView;
        public View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.countTextView = itemView.findViewById(R.id.countTextView);
            this.titleTextView = itemView.findViewById(R.id.titleTextView);
            this.imageView = itemView.findViewById(R.id.imageView);
            this.itemView = itemView;
        }
    }
}
