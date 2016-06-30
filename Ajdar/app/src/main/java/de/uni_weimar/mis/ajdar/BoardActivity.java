package de.uni_weimar.mis.ajdar;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

/**
 * Created by matpa on 6/22/2016.
 */
public class BoardActivity extends AppCompatActivity {
    Timer timer;
    TimerTask doAsynchronousTask;
    AjdarUser user;
    boolean finishGame;
    TextView tvName;
    TextView tvHealth;
    TextView tvWPN;
    TextView tvError;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        final Button btnShoot = (Button) findViewById(R.id.btnShoot);
        assert btnShoot != null;
        gson = new GsonBuilder().create();
        btnShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get(MainActivity.serverAddress + "Shoot?boardId" + MainActivity.gameBoard.ID + "&&userName=" + MainActivity.userName, new TextHttpResponseHandler() {

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Toast.makeText(BoardActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT);
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            Toast.makeText(BoardActivity.this, "Fired", Toast.LENGTH_SHORT);

                        }
                    });
                }
                catch (Exception ex) {
                    Toast.makeText(BoardActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);

                }
            }
        });

        tvName=(TextView)findViewById(R.id.txtName);
        tvError=(TextView)findViewById(R.id.txtError);
        tvHealth=(TextView)findViewById(R.id.txtHealth);
        tvWPN=(TextView)findViewById(R.id.txtWeapon);
        final Handler handler = new Handler();
        timer = new Timer();
        doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            updateStatus();
                            if (finishGame) {
                                doAsynchronousTask.cancel();
                                timer.cancel();
                                finishGame();
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 50000 ms

    }

protected void finishGame()
{

}
    protected void updateStatus() {

        AsyncHttpClient clientCheckIsStarted = new AsyncHttpClient();
        clientCheckIsStarted.get(MainActivity.serverAddress + "GetUserStatus?name="+MainActivity.userName+"&&boardId=" + MainActivity.gameBoard.ID, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(BoardActivity.this, "Fail:" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                user = gson.fromJson(responseString, AjdarUser.class);
                tvName.setText(user.Name);
                tvError.setText(user.Errors);
                tvHealth.setText(user.Health);
                tvWPN.setText(user.WPN);
            }
        });
    }

}
