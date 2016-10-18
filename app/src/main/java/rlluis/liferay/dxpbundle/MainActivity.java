package rlluis.liferay.dxpbundle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.User;

public class MainActivity extends AppCompatActivity
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
        startActivity(new Intent(this, MenuActivity.class));
    }

    @Override
    public void onLoginFailure(Exception e) {
        Snackbar.make(findViewById(android.R.id.content), "Login Failed", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .show();
    }
}
