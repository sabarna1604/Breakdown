package mobilemechanic.calibraint.com.breakdown;

/**
 * Created by Aradhana on 27-02-2017.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupPage extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    public static final int REQUEST_SIGNUP =0;
    EditText name;
    EditText email;
    EditText pw;
    EditText pho;
    Button signup;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        name = (EditText) findViewById(R.id.input_name);
        email = (EditText) findViewById(R.id.input_email);
        pw = (EditText) findViewById(R.id.input_password);
        pho =(EditText)findViewById(R.id.input_phonenumber);
        signup= (Button) findViewById(R.id.btn_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }
    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupPage.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String nam =name.getText().toString();
        String em = email.getText().toString();
        String password = pw.getText().toString();
        String phone =pho.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();


                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivityForResult(intent,REQUEST_SIGNUP );
                        finish();


                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        signup.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signup.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String nam =name.getText().toString();
        String em = email.getText().toString();
        String password = pw.getText().toString();
        String phone =pho.getText().toString();

        if (nam.isEmpty() || name.length() < 3) {
            name.setError("at least 3 characters");
            valid = false;
        } else {
            name.setError(null);
        }

        if (em.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(em).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (password.isEmpty() || pw.length() < 4 || pw.length() > 10) {
            pw.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            pw.setError(null);
        }
        if (phone.isEmpty()||pho.length()<10||pho.length()>10)
        {
            pho.setError("only 10 numbers");
            valid= false;
        }
        else{
            pho.setError(null);
        }
        return valid;
    }

}
