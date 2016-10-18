package rlluis.liferay.dxpbundle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liferay.mobile.android.service.Session;
import com.liferay.mobile.android.v7.ddlrecordset.DDLRecordSetService;
import com.liferay.mobile.screens.context.LiferayServerContext;
import com.liferay.mobile.screens.context.SessionContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaFormularios extends AppCompatActivity implements AdapterView.OnItemClickListener {

	private List<String> strings = new ArrayList<>();
	private List<JSONObject> objects = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_formularios);

		ListView list = (ListView) findViewById(R.id.list);
		final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);

		new Thread(new Runnable() {
			@Override
			public void run() {

				Session session = SessionContext.createSessionFromCurrentSession();
				DDLRecordSetService service = new DDLRecordSetService(session);
				JSONArray jsonArray = new JSONArray();
				jsonArray.put(LiferayServerContext.getGroupId());
				try {
					JSONArray array = service.getRecordSets(jsonArray);

					for (int i = 0; i < array.length(); i++) {
						JSONObject jsonObject = array.getJSONObject(i);
						strings.add(jsonObject.getString("nameCurrentValue"));
						objects.add(jsonObject);
					}
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							adapter.notifyDataSetChanged();
						}
					});

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(ListaFormularios.this, FormActivity.class);

		JSONObject jsonObject = objects.get(position);
		try {
			intent.putExtra("recordSetId", jsonObject.getLong("recordSetId"));
			intent.putExtra("structureId", jsonObject.getLong("DDMStructureId"));
			startActivity(intent);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
