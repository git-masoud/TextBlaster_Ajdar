package de.uni_weimar.mis.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import info.debatty.java.stringsimilarity.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Cosine  l = new Cosine();
       System.out.print(l.distance("The distance between two strings is defined as the L1",
                "The distance between two strings is defined as the L2"));
    }
}
