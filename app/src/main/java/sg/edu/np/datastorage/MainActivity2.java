package sg.edu.np.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private String TAB = "WeatherApp";
    private String FILENAME = "MainActivity2.java";

    public String GLOBAL_PREFS = "MyPrefs"; // name of my shared preference file
    public String MY_USERNAME = "MyUserName"; // key in shared preferences file
    public String MY_PASSWORD = "MyPassword"; // key in shred preferences file

    private Button createButton;
    private Button cancelButton;
    //SharedPreferences sharedPreferences;

    MyHandler dbHandler = new MyHandler(this, null, null, 1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText etUsername = findViewById(R.id.input_createUsername);
        EditText etPassword = findViewById(R.id.input_createPassword);
        createButton = findViewById(R.id.create);
        cancelButton = findViewById(R.id.cancel);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                /*sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(MY_USERNAME, etUsername.getText().toString());
                editor.putString(MY_PASSWORD, etPassword.getText().toString());
                editor.apply();
                */
                UserData userData = dbHandler.findUser(etUsername.getText().toString());

                if(userData == null)
                {
                    UserData dbUserData = new UserData();
                    dbUserData.setUsername(etUsername.getText().toString());
                    dbUserData.setPassword(etPassword.getText().toString());
                    dbHandler.addUser(dbUserData);
                    Toast.makeText(MainActivity2.this, "New user created!", Toast.LENGTH_SHORT);
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity2.this, "User exists!", Toast.LENGTH_SHORT);
                }

                //Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                //startActivity(intent);
            }
        });
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }

    protected void onStop(){
        super.onStop();
        finish();
    }
}