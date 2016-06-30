package de.uni_weimar.mis.ajdar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cz.msebera.android.httpclient.Header;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ListView lvBoards;
    ListView lvUsers;
    public static String userName = "";
    GameBoardAdapter gameBoardAdapter;
    UserAdapter userAdapter;
    EditText etBoardName;
    ProgressDialog dialog;
    ProgressDialog waitingDialog;
    public static String serverAddress;
    Button btnStart;
    LinearLayout layoutUsers;
    LinearLayout layoutAdmin;
    LinearLayout layoutCreating;
    Timer timer;
    TimerTask doAsynchronousTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Should goes to installation
        SharedPreferences.Editor editor = getSharedPreferences("AjdarConfig", MODE_WORLD_READABLE).edit();
        editor.putString("ServerAddress", "http://ajdar.exsait.net//api/AjdarApi/");
        editor.commit();

        SharedPreferences sharedPref = getSharedPreferences("AjdarConfig", MODE_WORLD_WRITEABLE);
        serverAddress = sharedPref.getString("ServerAddress", "DEFAULT");

        showInputBox();
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading. Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        lvBoards = (ListView) findViewById(R.id.lvBoards);
        etBoardName = (EditText) findViewById(R.id.etBoardName);
        lvBoards = (ListView) findViewById(R.id.lvBoards);
        lvUsers = (ListView) findViewById(R.id.lvUsers);
        btnStart = (Button) findViewById(R.id.btnStart);
        layoutCreating = (LinearLayout) findViewById(R.id.layoutCreating);
        layoutAdmin = (LinearLayout) findViewById(R.id.layoutAdmin);
        layoutUsers = (LinearLayout) findViewById(R.id.layoutUsers);
        ArrayList<GameBoard> arrayOfBoards = new ArrayList<GameBoard>();
        ArrayList<AjdarUser> arrayOfUsers = new ArrayList<AjdarUser>();
        gameBoardAdapter = new GameBoardAdapter(this, arrayOfBoards);
        userAdapter = new UserAdapter(this, arrayOfUsers);

        lvBoards.setAdapter(gameBoardAdapter);
        lvBoards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                GameBoard gameBoard = gameBoardAdapter.getItem(position);
                joinToABoard(gameBoard);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get(serverAddress + "RequestToStartGame?boardId=" + gameBoard.ID, new TextHttpResponseHandler() {
                        @Override
                        public void onStart() {
                            dialog.show();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable t) {
                            System.out.println(t.getMessage());
                            dialog.cancel();
                            showConnectionError();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            btnStart.setTextSize(015);
                            btnStart.setText("Open board in browsert to start.");
                            btnStart.setBackgroundColor(Color.GRAY);
                            doAsynchronousTask.cancel();
                            timer.cancel();
                            final Handler handler = new Handler();
                            timer = new Timer();
                            doAsynchronousTask = new TimerTask() {
                                @Override
                                public void run() {
                                    handler.post(new Runnable() {
                                        public void run() {
                                            try {

                                                checkIsStartedGame();
                                                if (startGame) {
                                                    doAsynchronousTask.cancel();
                                                    timer.cancel();
                                                    startGame();
                                                }
                                            } catch (Exception e) {
                                                // TODO Auto-generated catch block
                                            }
                                        }
                                    });
                                }
                            };
                            timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 50000 ms

                            dialog.cancel();
                        }
                    });
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
                }
            }
        });
        final Button btnCreateBoard = (Button) findViewById(R.id.btnCreateBoard);
        btnCreateBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    etBoardName = ((EditText) findViewById(R.id.etBoardName));
                    String boardName = etBoardName.getText().toString();
                    AsyncHttpClient client = new AsyncHttpClient();
                    client.get(serverAddress + "MakeBoard?name=" + boardName + "&adminName=" + userName, new TextHttpResponseHandler() {
                        @Override
                        public void onStart() {
                            dialog.show();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String res) {
                            //layoutCreating.setVisibility(View.INVISIBLE);
                            gameBoard = new GameBoard();
                            gameBoard.ID = Integer.parseInt(res);
                            gameBoard.Name = etBoardName.getText().toString();
                            refreshList();
                            etBoardName.setEnabled(false);
                            btnCreateBoard.setVisibility(View.INVISIBLE);
                            dialog.cancel();
                            layoutAdmin.setVisibility(View.VISIBLE);
                            layoutUsers.setVisibility(View.INVISIBLE);
                            lvUsers.setAdapter(userAdapter);
                            final Handler handler = new Handler();
                            timer = new Timer();
                            doAsynchronousTask = new TimerTask() {
                                @Override
                                public void run() {
                                    handler.post(new Runnable() {
                                        public void run() {
                                            try {
                                                if (!startGame)
                                                    updateUsersList();
                                            } catch (Exception e) {
                                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                                            }
                                        }
                                    });
                                }
                            };
                            timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 50000 ms


                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                            // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                            System.out.println(t.getMessage());
                            dialog.cancel();
                            showConnectionError();
                        }
                    });
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
                }
            }
        });

        refreshList();
    }

    public static GameBoard gameBoard;
    boolean startGame = false;

    protected void joinToABoard(GameBoard _gameBoard) {
        gameBoard = _gameBoard;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(serverAddress + "JoinUser?name=" + userName + "&boardId=" + gameBoard.ID, new TextHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        dialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        waitingDialog = new ProgressDialog(MainActivity.this);
                        waitingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        waitingDialog.setMessage("Please wait for starting the game ...");
                        waitingDialog.setIndeterminate(true);
                        waitingDialog.setCanceledOnTouchOutside(false);
                        final Handler handler = new Handler();
                        timer = new Timer();
                        doAsynchronousTask = new TimerTask() {
                            @Override
                            public void run() {
                                handler.post(new Runnable() {
                                    public void run() {
                                        try {
                                            checkIsStartedGame();
                                            if (startGame) {
                                                doAsynchronousTask.cancel();
                                                timer.cancel();
                                                startGame();
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

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        System.out.println(t.getMessage());
                        dialog.cancel();
                        showConnectionError();
                    }
                }
        );
    }

    protected void updateUsersList() {
        AsyncHttpClient clientGetUsers = new AsyncHttpClient();
        clientGetUsers.get(serverAddress + "/GetUsers?ID=" + gameBoard.ID, new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                //dialog.show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Fail:" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                try {

                    Gson gson = new GsonBuilder().create();
                    // Define Response class to correspond to the JSON response returned
                    AjdarUser[] ajdarUsers = gson.fromJson(responseString, AjdarUser[].class);
                    userAdapter.clear();
                    gameBoard.Users.clear();
                    for (AjdarUser ajdarUser : ajdarUsers) {
                        userAdapter.add(ajdarUser);
                        gameBoard.Users.add(ajdarUser);
                    }


                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                dialog.cancel();
            }
        });
    }

    protected void checkIsStartedGame() {
        try {
            AsyncHttpClient clientCheckIsStarted = new AsyncHttpClient();
            clientCheckIsStarted.get(serverAddress + "IsGameStarted?boardId=" + gameBoard.ID, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                    Toast.makeText(MainActivity.this, "Fail:" + throwable.getMessage(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {

                    if (responseString.equals("true")) {
                        startGame = true;
                    }
                }
            });
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
        }
    }

    protected void startGame() {
        try
        {
        Intent myIntent = new Intent(MainActivity.this, BoardActivity.class);
        // myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent);
        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
        }
    }

    protected void showInputBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("What's your name?");
        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userName = input.getText().toString();
            }
        });
        builder.show();
    }

    protected void showConnectionError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Connecting to server failed.!\r\nTry Again!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    protected void refreshList() {
        try{
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(serverAddress + "GetBoards", new TextHttpResponseHandler() {
                    @Override
                    public void onStart() {
                        dialog.show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        try {
                            Gson gson = new GsonBuilder().create();
                            // Define Response class to correspond to the JSON response returned
                            GameBoard[] gameBoards = gson.fromJson(res, GameBoard[].class);
                            gameBoardAdapter.clear();
                            for (GameBoard gameBoard : gameBoards)
                                gameBoardAdapter.add(gameBoard);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());

                        }
                        dialog.cancel();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        System.out.println(t.getMessage());
                        dialog.cancel();
                        showConnectionError();
                    }
                }
        );
        }
        catch (Exception ex)
        {
            Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT);
        }
    }
}
