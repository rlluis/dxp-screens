package rlluis.liferay.dxpbundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liferay.mobile.android.callback.typed.JSONObjectCallback;
import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.screens.asset.AssetEntry;
import com.liferay.mobile.screens.asset.list.AssetListScreenlet;
import com.liferay.mobile.screens.base.list.BaseListListener;
import com.liferay.mobile.screens.base.list.BaseListScreenlet;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.util.LiferayLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListaEventos extends AppCompatActivity implements BaseListListener<AssetEntry> {

    private AssetListScreenlet assetListScreenlet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_eventos);

        assetListScreenlet = (AssetListScreenlet) findViewById(R.id.asset_list_screenlet);
        assetListScreenlet.setListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        assetListScreenlet.loadPage(0);
    }

    @Override
    public void onListPageFailed(int startRow, Exception e) {

    }

    @Override
    public void onListPageReceived(int startRow, int endRow, List<AssetEntry> entries, int rowCount) {

    }

    @Override
    public void onListItemSelected(AssetEntry element, View view) {

        //tengo entryId, necesito articleId
        Long valor;
        Session sessionFromCurrentSession = SessionContext.createSessionFromCurrentSession();

        GetArticleSDK pref= new GetArticleSDK(sessionFromCurrentSession);
        sessionFromCurrentSession.setCallback(new JSONObjectCallback() {
            @Override
            public void onFailure(Exception exception) {
                LiferayLogger.e(exception.toString());
            }

            @Override
            public void onSuccess(JSONObject result) {
                String articleId=null;
                JSONObject data,data2 = null;
                //Map<String,Object> map = new LinkedHashMap<String, Object>();

                try {
                    data = (JSONObject)result.get("object");
                    data2 = (JSONObject)data.get("modelAttributes");
                    //map = (Map<String, Object>)data2.get("modelAttributes");
                    articleId = data2.get("articleId").toString();

                    Intent intento = new Intent(ListaEventos.this, EventsActivity.class);
                    intento.putExtra("articleId",articleId);
                    startActivity(intento);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        try {

            valor=Long.valueOf(element.getValues().get("entryId").toString());
            pref.getAssetEntryV(valor,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void error(Exception e, String userAction) {

    }
}
