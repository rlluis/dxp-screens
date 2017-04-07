package rlluis.liferay.dxpbundle.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.push.*;
import com.liferay.mobile.screens.util.LiferayLogger;

import org.json.JSONObject;

import rlluis.liferay.dxpbundle.R;

public class MainActivity extends PushScreensActivity
    implements LoginListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginScreenlet loginListener = (LoginScreenlet) findViewById(R.id.login_screenlet);
        loginListener.setListener(this);

    }

    @Override
    public void onLoginSuccess(User user) {
        startActivity(new Intent(this, ATWebContent.class));
    }

    @Override
    public void onLoginFailure(Exception e) {
        Snackbar.make(findViewById(android.R.id.content), "Login Failed", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .show();
    }

    protected Session getDefaultSession() {
        return null;
    }

    @Override
    protected void onPushNotificationReceived(final JSONObject jsonObject) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LiferayLogger.d("Push notification received! " + jsonObject);
                Snackbar.make(findViewById(android.R.id.content), "Reloading list...", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onErrorRegisteringPush(String message, Exception e) {
        e.printStackTrace();
    }

    @Override
    protected String getSenderId() {
        return getString(R.string.sender_id);
    }

}
