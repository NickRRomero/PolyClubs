package polyclubs.polyclubs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class Login extends Activity {

    DatabaseManager databaseManager;
    private static final String incorrectLoginErrorMessage = "Incorrect email and/or password.";
    String loginText; 
    String passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void confirmStudentInformation(View v) {

        String key;
        loginText = (EditText) findViewById(R.id.loginUserName);
        passwordText = (EditText) findViewById(R.id.loginPassword);
        JSONObject userProfile = null;
        databaseManager = new DatabaseManager();
        TextView errorMessage = (TextView) findViewById(R.id.errorMessage);
        /**
         * This needs to be determined by the individual classes.
         */

        databaseManager.setDataBaseDestination("StudentDatabase", loginText.getText().toString(), false);
        try {
            userProfile = databaseManager.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (userProfile != null) {
            try {
                key = userProfile.getString("key");
                Log.e("PASSWORD", key);
            } catch (JSONException e) {
                Log.e("JSON EXCEPTION", e.toString());
            }
            errorMessage.setVisibility(v.INVISIBLE);
        }
        else
        {

            errorMessage.setVisibility(v.VISIBLE);
        }



    }

}
