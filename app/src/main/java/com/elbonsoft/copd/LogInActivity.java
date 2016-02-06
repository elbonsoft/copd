package com.elbonsoft.copd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    String loginId;
    String loginPw;
    Boolean bAutoLogin = false;

    EditText loginIdET;
    EditText loginPwET;
    CheckBox bAutoCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        SharedPreferences sf = getSharedPreferences("loginInfo", 0);
        bAutoLogin = sf.getBoolean("bAuto", false);
        loginId = sf.getString("loginId", "");
        loginPw = sf.getString("loginPw", "");

        loginIdET = (EditText)findViewById(R.id.loginIdEditText);
        loginPwET = (EditText)findViewById(R.id.loginPwEditText);
        bAutoCB = (CheckBox)findViewById(R.id.loginAutoCheckBox);

        if (bAutoLogin) {

            bAutoCB.setChecked(true);
            loginIdET.setText(loginId);
            loginPwET.setText(loginPw);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    public void onLoginButton(View v) {

        if (bAutoLogin) {

            SharedPreferences sf = getSharedPreferences("loginInfo", 0);
            SharedPreferences.Editor editor = sf.edit();

            String strId = loginIdET.getText().toString();
            String strPw = loginPwET.getText().toString();

            editor.putString("loginId", strId);
            editor.putString("loginPw", strPw);
            editor.putBoolean("bAuto", true);

            editor.commit();
        }
        else {

            SharedPreferences sf = getSharedPreferences("loginInfo", 0);
            SharedPreferences.Editor editor = sf.edit();

            editor.putString("loginId", "");
            editor.putString("loginPw", "");
            editor.putBoolean("bAuto", false);

            editor.commit();

        }

    }

    public void onAutoCheckBox(View v) {

        if (bAutoCB.isChecked()) {

            bAutoLogin = true;
        }
        else {
            bAutoLogin = false;
        }
    }
}
