package db.robot.com.toolbarhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {

    ToolbarHelper toolbarHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        toolbarHelper = new ToolbarHelper(getApplicationContext(), layoutResID);
        Toolbar toolbar = toolbarHelper.getToolBar();
        toolbar.setTitle("Coding");

        setContentView(toolbarHelper.getContentView());
        setSupportActionBar(toolbar);
    }
}
