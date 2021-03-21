package in.co.electronicshub.recursion.collegehub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import in.co.electronicshub.recursion.collegehub.R;
import in.co.electronicshub.recursion.collegehub.adapters.ProjectAdapter;
import in.co.electronicshub.recursion.collegehub.utils.Project;

public class ProjectsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        recyclerView = findViewById(R.id.recyclerView);
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project(getResources().getDrawable(R.drawable.ic_user), "Sample App Development",1 , 300, 15));
        projects.add(new Project(getResources().getDrawable(R.drawable.ic_user), "Sample Web Development",2, 150,10));
        projects.add(new Project(getResources().getDrawable(R.drawable.ic_user), "Block Chain",3, 200,40));
        ProjectAdapter projectAdapter = new ProjectAdapter(this, projects);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(projectAdapter);
    }
}