package de.uni_weimar.mis.ajdar;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;
import info.debatty.java.stringsimilarity.Cosine;

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
    TextView tvSentence;
    EditText txtSentence;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_board);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        final Button btnShoot = (Button) findViewById(R.id.btnShoot);
        assert btnShoot != null;
        gson = new GsonBuilder().create();
        btnShoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitSentence();
            }
        });
        tvSentence = (TextView) findViewById(R.id.tvSentences);
        txtSentence = (EditText) findViewById(R.id.txtSentence);
        txtSentence.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ((Activity) BoardActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(BoardActivity.this, tvSentence.getText().toString()).show();
                    }
                });

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // showMessage(tvSentence.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvName = (TextView) findViewById(R.id.txtName);
        tvError = (TextView) findViewById(R.id.txtError);
        tvHealth = (TextView) findViewById(R.id.txtHealth);
        tvWPN = (TextView) findViewById(R.id.txtWeapon);
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
                            showMessage(e.getMessage());
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 50000 ms
        getNewSentence();
    }

    protected void submitSentence() {
        String originSentence = tvSentence.getText().toString();
        tvSentence.setText("");
        String userSentence = txtSentence.getText().toString();
        txtSentence.setText("");
        Cosine cosineSimilarity=new Cosine();

        double cer = cosineSimilarity.distance(originSentence, userSentence); //0.5720
        showMessage(cer + " = cer");
        try {
            AsyncHttpClient client = new AsyncHttpClient();
            client.get(MainActivity.serverAddress + "Shoot?boardId=" + MainActivity.gameBoard.ID+ "&&userName=" + MainActivity.userName + "&&cer=" + cer , new TextHttpResponseHandler() {

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    showMessage(throwable.getMessage());
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {

                    showMessage("Fired");
                    getNewSentence();

                }
            });
        } catch (Exception ex) {
            showMessage(ex.getLocalizedMessage());
        }

    }

    Sentence sentence = new Sentence();

    protected void getNewSentence() {
        try {
            AsyncHttpClient clientCheckIsStarted = new AsyncHttpClient();

            clientCheckIsStarted.get(MainActivity.serverAddress + "GetNewSentence", new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    showMessage("Fail:" + throwable.getMessage());
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    if (!responseString.isEmpty() || responseString != "null") {
                        sentence = gson.fromJson(responseString, Sentence.class);
                        tvSentence.setText(sentence.Text);
                    }
                }
            });
        }
        catch (Exception ex)
        {
            showMessage( ex.getLocalizedMessage());
        }
    }

    protected void finishGame() {

    }

    protected void updateStatus() {

        AsyncHttpClient clientCheckIsStarted = new AsyncHttpClient();
        clientCheckIsStarted.get(MainActivity.serverAddress + "GetUserStatus?name=" + MainActivity.userName + "&&boardId=" + MainActivity.gameBoard.ID, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showMessage("Fail:" + throwable.getMessage());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (!responseString.isEmpty() || responseString != "null") {
                    user = gson.fromJson(responseString, AjdarUser.class);
                    tvName.setText(user.Name + "");
                    tvError.setText("Errors:" + user.Errors + "");
                    tvHealth.setText("Health:" + user.Health + "");
                    tvWPN.setText("Weapons:" + user.WPN + "");
                }
            }
        });
    }

    protected void showMessage(final String message) {
        ((Activity) BoardActivity.this).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BoardActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}

class Sentence {
    public int Id;
    public String Text;
}
