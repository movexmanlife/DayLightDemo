package db.robot.com.toolbarhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import db.robot.com.toolbarhelper.R;

/**
 * Created by Administrator on 2016/10/9.
 */
public class CustomActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Coding");
        int left = toolbar.getContentInsetLeft();
        System.out.println(left);
        setSupportActionBar(toolbar);
    }
}
