package in.co.electronicshub.recursion.collegehub.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import in.co.electronicshub.recursion.collegehub.R;
import in.co.electronicshub.recursion.collegehub.activities.LoginActivity;
import in.co.electronicshub.recursion.collegehub.activities.MainActivity;
import in.co.electronicshub.recursion.collegehub.adapters.DomainAdapter;
import in.co.electronicshub.recursion.collegehub.utils.CustomToast;
import in.co.electronicshub.recursion.collegehub.utils.Domain;
import in.co.electronicshub.recursion.collegehub.utils.Network;
import in.co.electronicshub.recursion.collegehub.utils.SessionManager;

public class Home  extends Fragment implements Network.ResponseListener {

    private RecyclerView recyclerView;
     @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);
      return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        HashMap<String, String> data = new HashMap<>();
        Network network = new Network(getContext());
        network.setMessage("Loading... Please wait");
        network.setPage("get-domains.php");
        network.setResponseListener(this);
        network.execute(data);

    }

    @Override
    public void responseReceived(String page, String data) {
        if(data!=null) {
            try {
                ArrayList<Domain> domains = new ArrayList<>();
                JSONArray jsons = new JSONArray(data);
                for(int i=0; i<jsons.length();i++){
                    JSONObject json = jsons.getJSONObject(i);
                    domains.add(new Domain(json.getString("image"),json.getString("title"),json.getInt("count"),json.getInt("id")));
                }

                DomainAdapter domainAdapter = new DomainAdapter(getContext(), domains);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(domainAdapter);

            } catch (JSONException je) {
                je.printStackTrace();
                CustomToast.show(getContext(), "There was a problem");
            }
        }
    }
}
