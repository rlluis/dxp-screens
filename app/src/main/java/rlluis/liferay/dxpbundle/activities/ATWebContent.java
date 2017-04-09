package rlluis.liferay.dxpbundle.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liferay.mobile.android.callback.typed.JSONArrayCallback;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.util.LiferayLogger;
import com.liferay.mobile.screens.webcontent.display.WebContentDisplayScreenlet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rlluis.liferay.dxpbundle.mobileSDK.GetUserSegment;
import rlluis.liferay.dxpbundle.R;

public class ATWebContent extends AppCompatActivity implements View.OnClickListener{

    WebContentDisplayScreenlet webcontent;
    Button  buttonContinue;

    private Button _continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atweb_content);

        _continueButton = (Button) findViewById(R.id.continue_button);
        _continueButton.setOnClickListener(this);

        Session session = SessionContext.createSessionFromCurrentSession();
        User user = SessionContext.getCurrentUser();
        GetUserSegment service = new GetUserSegment(session);
        final boolean userSegmentActive=true;
        session.setCallback(new JSONArrayCallback() {
        @Override
        public void onFailure(Exception exception) {
            LiferayLogger.e(exception.toString());
        }

        @Override
        public void onSuccess(JSONArray result) {
            String userSegmentId=getString(R.string.user_segment);
            String articulo = calculoArticulo(result,userSegmentId);
            webcontent = (WebContentDisplayScreenlet) findViewById(R.id.web_atarticle);
            webcontent.setArticleId(articulo);
            webcontent.load();
            if (articulo==getString(R.string.webcontent_usersegment)){_continueButton.setVisibility(View.VISIBLE);}
        }
        private String calculoArticulo(JSONArray result,String userSegmentId) {
            Boolean resultado = false;
            String articulo = "";
            try {
                if (result != null) {
                    String segmento = "";
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject jsonObject = result.getJSONObject(i);
                        segmento = jsonObject.getString("userSegmentId").toString();
                        if (segmento.equals(userSegmentId)) {
                            resultado = true;
                        }
                    }
                }

                if (resultado) {
                    articulo = getString(R.string.webcontent_usersegment);
                } else {
                    articulo = getString(R.string.webcontent_normal);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return articulo;
        }});

        try {
            service.getUserSegments(user.getId(), userSegmentActive);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, SurveyActivity.class));
    }
}
