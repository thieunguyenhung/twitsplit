package io.github.thieunguyenhung.twitsplit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.github.thieunguyenhung.twitsplit.activities.MessagesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread welcomeThread = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(2000);
                } catch (Exception e) {
                    Log.d("Error", e.toString());
                } finally {
                    Intent i = new Intent(MainActivity.this, MessagesActivity.class);
                    //Intent i = new Intent(MainActivity.this, FileActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();

        setContentView(R.layout.activity_main);
    }
}
