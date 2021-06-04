package sg.edu.np.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static String TAB = "MAD Practical";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent data = getIntent();
        int index = data.getIntExtra("Index", 0);
        User myself = ListActivity.userList.get(index);
        Button follow = findViewById(R.id.follow);
        follow.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                boolean status = myself.isFollowed();
                status = !status;
                if (!status){
                    follow.setText("Follow");
                    myself.setFollowed(false);
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                else if (status){
                    follow.setText("Unfollow");
                    myself.setFollowed(true);
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
                else {
                    System.out.println("You messed up somewhere bro");
                }
            }
        });

        TextView nameText = findViewById(R.id.name);
        TextView descText = findViewById(R.id.description);

        nameText.setText(myself.getName());
        descText.setText(myself.getDescription());

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TAB, "Start!");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TAB, "Resume!");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAB, "Pause!");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAB, "Stop!");
    }
}