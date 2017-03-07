package mobilemechanic.calibraint.com.breakdown;

/**
 * Created by Aradhana on 27-02-2017.
 */
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.lang.String;
import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.util.Log;
import android.preference.PreferenceManager;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    public static final int REQUEST_SIGNUP = 0;
    EditText email;
    EditText pw;
    Button log;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        setContentView(R.layout.activity_signup);

        email = (EditText) findViewById(R.id.input_email);
        pw = (EditText) findViewById(R.id.input_password);
        log = (Button)findViewById(R.id.btn_log);
        signup = (TextView) findViewById(R.id.link_signup);
       log.setOnClickListener(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupPage.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

    }


    public void login() {
        Log.d(TAG, "Login");


        if (!validate()) {
            onLoginFailed();
            return;
        }

        log.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginPage.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String em = email.getText().toString();
        String password = pw.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivityForResult(intent, REQUEST_SIGNUP);
                        finish();
                    }

                    private void onLoginSuccess() {
                    }


                }, 3000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {


                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }
    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        finish();
    }
    public void onLoginSuccess() {
        log.setEnabled(true);
        finish();
    }
    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        log.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String em = email.getText().toString();
        String password = pw.getText().toString();

        if (em.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            pw.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            pw.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        login();

    }
}
