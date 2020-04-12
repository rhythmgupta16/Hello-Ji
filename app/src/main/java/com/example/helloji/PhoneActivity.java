package com.example.helloji;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PhoneActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView tvUserEmail;
    private EditText etPhone;
    private Button btnLogOut;
    private Button btnContinue;
    private Button btnfb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        firebaseAuth = FirebaseAuth.getInstance();



        //initializing views
        tvUserEmail = (TextView) findViewById(R.id.tvUserEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnfb = (Button) findViewById(R.id.login_button);

        String way = getIntent().getStringExtra("method");
        if(firebaseAuth.getCurrentUser() != null)
        {
            way = ""+0;
        }
        if(Integer.parseInt(way) == 0)
        {
            btnfb.setVisibility(View.INVISIBLE);

            //initializing firebase authentication object
            firebaseAuth = FirebaseAuth.getInstance();

            //if the user is not logged in
            //that means current user will return null
            if(firebaseAuth.getCurrentUser() == null){
                //closing this activity
                finish();
                //starting login activity
                startActivity(new Intent(this, LoginActivity.class));
            }

            //getting current user
            FirebaseUser user = firebaseAuth.getCurrentUser();



            //displaying logged in user name
            tvUserEmail.setText("Welcome "+user.getEmail());
        }
        if(Integer.parseInt(way) == 1 )
        {
            btnLogOut.setVisibility(View.INVISIBLE);
        }


        //adding listener to button
        btnContinue.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        btnfb.setOnClickListener(this);

    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                AccessToken currentAccessToken) {
            if (currentAccessToken == null) {
                //write your code here what to do when user clicks on facebook logout
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onClick(View view) {

        if(view == btnContinue){
            String mobile = etPhone.getText().toString().trim();

            if(mobile.isEmpty() || mobile.length() < 10) {
                etPhone.setError("Enter a valid mobile");
                etPhone.requestFocus();
                return;
            }
            Intent intent = new Intent(PhoneActivity.this, VerifyPhoneActivity.class);
            intent.putExtra("mobile", mobile);
            startActivity(intent);
        }

        //if logout is pressed
        if(view == btnLogOut){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}