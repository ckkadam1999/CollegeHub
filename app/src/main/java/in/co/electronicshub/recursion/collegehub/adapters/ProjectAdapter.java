package in.co.electronicshub.recursion.collegehub.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.co.electronicshub.recursion.collegehub.R;
import in.co.electronicshub.recursion.collegehub.utils.Project;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Project> projects;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.projects, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.imageView.setImageDrawable(project.getAuthor());
        holder.titleTextView.setText(project.getTitle());
        holder.likesTextView.setText(project.getLikes()+"");
        holder.viewsTextView.setText(project.getViews()+"");

    }

    public ProjectAdapter(Context context, ArrayList<Project> domains) {
        this.context = context;
        this.projects = domains;
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView likesTextView, titleTextView, viewsTextView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.likesTextView = itemView.findViewById(R.id.likesTextView);
            this.viewsTextView = itemView.findViewById(R.id.viewsTextView);
            this.titleTextView = itemView.findViewById(R.id.titleTextView);
            this.imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
