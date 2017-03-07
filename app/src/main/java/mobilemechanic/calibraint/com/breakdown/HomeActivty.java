package mobilemechanic.calibraint.com.breakdown;

/**
 * Created by Aradhana on 27-02-2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class HomeActivty extends AppCompatActivity  {
    public static final int REQUEST_SIGNUP =0;
    Button signup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        login = (Button)findViewById(R.id.button);
        signup =(Button) findViewById(R.id.but);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupPage.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }
        });
    }

}
