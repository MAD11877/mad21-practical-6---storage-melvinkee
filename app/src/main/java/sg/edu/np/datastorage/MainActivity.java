package sg.edu.np.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static String TAB = "WeatherApp";
    private static String FILENAME = "MainActivity.java";

    public String GLOBAL_PREFS = "MyPrefs"; // name of my shared preference file
    public String MY_USERNAME = "MyUserName"; // key in shared preferences file
    public String MY_PASSWORD = "MyPassword"; // key in shred preferences file

    private TextView newUser;
    private Button loginButton;

    // SharedPreferences sharedPreferences;
    MyHandler dbHandler = new MyHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newUser = findViewById(R.id.newUser);
        newUser.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent){
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return false;
            }
        });

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText etUsername = findViewById(R.id.inputUsername);
                EditText etPassword = findViewById(R.id.inputPassword);

                if(isValidCredentials(etUsername.getText().toString(), etPassword.getText().toString())){
                    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Valid Acc", Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid username/password!", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    // This function does some form of validation
    public boolean isValidCredentials(String username, String password){
        /*sharedPreferences = getSharedPreferences(GLOBAL_PREFS, MODE_PRIVATE);
        String sharedUsername = sharedPreferences.getString(MY_USERNAME, "");
        String sharedPassword = sharedPreferences.getString(MY_PASSWORD, "");

        if(sharedUsername.equals(username) && sharedPassword.equals(password)){
            return true;
        }*/

        UserData dbData = dbHandler.findUser(username);
        if (dbData != null){
            if(dbData.getUsername().equals(username) && dbData.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
}