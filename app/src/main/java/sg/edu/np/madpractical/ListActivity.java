package sg.edu.np.madpractical;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private static String TAB = "MAD Practical";
    public static ArrayList<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        for (int i = 0; i < 20; i++){
            objCreator();
            userList.add(objCreator());
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Adaptor customAdaptor = new Adaptor(userList);
        LinearLayoutManager cLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(cLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customAdaptor);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TAB, "Start");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TAB, "Resume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAB, "Pause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAB, "Stop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(TAB, "Destroy");
    }

    private int randomGen(){
        Random ran = new Random();
        int otp = ran.nextInt();
        return otp;
    }

    private boolean randomBool(){
        Random ran = new Random();
        int upperbound = 2;
        int randomBool = ran.nextInt(upperbound);
        if (randomBool == 0) {
            return false;
        }
        else if (randomBool == 1){
            return true;
        }
        else {
            return true;
        }
    }

    private User objCreator(){
        User newUser = new User();
        int newID = randomGen();
        String newDesc = String.valueOf(randomGen());
        boolean newStatus = randomBool();
        newUser.setName("Name" + String.valueOf(newID));
        newUser.setId(newID);
        newUser.setDescription("Description " + newDesc);
        newUser.setFollowed(newStatus);
        return newUser;
    }
}