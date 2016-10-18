package rlluis.liferay.dxpbundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet;

public class EventsActivity extends AppCompatActivity {

    WebContentDisplayScreenlet webcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        if (getIntent().getStringExtra("articleId")!=null){
            webcontent = (WebContentDisplayScreenlet) findViewById(R.id.web_men);
            webcontent.setArticleId(getIntent().getStringExtra("articleId"));
        }
    }


}
