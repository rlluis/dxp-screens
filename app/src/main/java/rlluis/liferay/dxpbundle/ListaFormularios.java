package rlluis.liferay.dxpbundle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v7.ddlrecordset.DDLRecordSetService;
import com.liferay.mobile.screens.asset.AssetEntry;
import com.liferay.mobile.screens.asset.list.AssetListScreenlet;
import com.liferay.mobile.screens.base.list.BaseListListener;
import com.liferay.mobile.screens.context.SessionContext;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class ListaFormularios extends AppCompatActivity implements BaseListListener<AssetEntry> {

    AssetListScreenlet ddlListScreenlet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_formularios);

        ddlListScreenlet = (AssetListScreenlet) findViewById(R.id.asset_list_forms);
        ddlListScreenlet.setListener(this);
        HashMap<String,Object> map = new HashMap<>();
        map.put("visible","false");
        ddlListScreenlet.setCustomEntryQuery(map);

    }

    @Override
    protected void onResume() {
        super.onResume();

        ddlListScreenlet.loadPage(0);
    }


    @Override
    public void onListPageFailed(int startRow, Exception e) {

    }

    @Override
    public void onListPageReceived(int startRow, int endRow, List<AssetEntry> entries, int rowCount) {

    }

    @Override
    public void onListItemSelected(final AssetEntry element, View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Session session = SessionContext.createSessionFromCurrentSession();
                DDLRecordSetService service = new DDLRecordSetService(session);
                try {
                    JSONObject jsonObject = service.getRecordSet(element.getEntryId());

                    Intent intent = new Intent(ListaFormularios.this, FormActivity.class);

                    intent.putExtra("recordSetId", element.getEntryId());
                    //intent.putExtra("structureId", jsonObject.getString("DDMStructureId"));
                    intent.putExtra("structureId",element.getEntryId()-4);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }

    @Override
    public void error(Exception e, String userAction) {

    }
}
