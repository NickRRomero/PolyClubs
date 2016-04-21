package polyclubs.polyclubs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.bson.Document;

public class Login extends Activity {

    DatabaseManager databaseManager;
    //TextView loginText, passwordText;
    EditText loginText, passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void confirmStudentInformation(View v) {
        Log.d("a","HERERER");
        loginText = (EditText) findViewById(R.id.loginUserName);
        passwordText = (EditText) findViewById(R.id.loginPassword);


        databaseManager = new DatabaseManager("192.168.1.9");
        databaseManager.setDataBaseDestination("StudentDatabase");
        Document testDoc = databaseManager.accessDatabase();
        System.out.println(testDoc.get("email"));
        if (testDoc.get("email").equals(loginText) &&
                testDoc.get("key").equals(passwordText))
        {
            System.out.println("Verification works.");
        }
    }

}
