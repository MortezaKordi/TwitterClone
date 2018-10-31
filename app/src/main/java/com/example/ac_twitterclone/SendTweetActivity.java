package com.example.ac_twitterclone;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SendTweetActivity extends AppCompatActivity {

    private EditText edtTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_tweet);

          edtTweet = findViewById(R.id.edtSendTweet);

    }

    public void sendTweet(View view) {

        ParseObject parseObject = new ParseObject("MyTweet");
        parseObject.put("tweet", edtTweet.getText().toString());
        parseObject.put("user", ParseUser.getCurrentUser().getUsername());
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {

                    FancyToast.makeText(SendTweetActivity.this, ParseUser.getCurrentUser().getUsername() + "'s tweet" + "(" + edtTweet.getText().toString() + ")" + " is saved!!!",
                            Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                } else {
                    FancyToast.makeText(SendTweetActivity.this, e.getMessage(), Toast.LENGTH_SHORT,
                            FancyToast.ERROR, true).show();
                }

                progressDialog.dismiss();
            }
        });


    }
}
