package rlluis.liferay.dxpbundle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;

import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.ddl.form.DDLFormListener;
import com.liferay.mobile.screens.ddl.form.DDLFormScreenlet;
import com.liferay.mobile.screens.ddl.model.DocumentField;
import com.liferay.mobile.screens.ddl.model.Record;

import org.json.JSONObject;

import java.util.Map;

import static com.liferay.mobile.screens.context.LiferayScreensContext.getContext;

public class FormActivity extends AppCompatActivity implements DDLFormListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        SessionContext.createBasicSession("test", "test");

        DDLFormScreenlet ddlFormScreenlet = (DDLFormScreenlet) findViewById(R.id.form_review);
        ddlFormScreenlet.setRecordSetId(getIntent().getLongExtra("recordSetId",0));
        ddlFormScreenlet.setStructureId(getIntent().getLongExtra("structureId",0));
        ddlFormScreenlet.setListener(this);

    }

    @Override
    public void onDDLFormLoaded(Record record) {

    }

    @Override
    public void onDDLFormRecordLoaded(Record record, Map<String, Object> valuesAndAttributes) {

    }


    @Override
    public void onDDLFormRecordAdded(Record record) {
        SessionContext.createBasicSession("test", "test");

        final Snackbar miSnack = Snackbar.make(findViewById(android.R.id.content), "Form Submited!", Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED);

        miSnack.setDuration(2000);
        miSnack.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                miLlamada();
            }
        }, 2000);

    }

    public Snackbar.Callback miLlamada(){
        startActivity(new Intent(this, MenuActivity.class));
        return null;
    }

    @Override
    public void onDDLFormRecordUpdated(Record record) {

    }

    @Override
    public void onDDLFormDocumentUploaded(DocumentField documentField, JSONObject jsonObject) {

    }

    @Override
    public void onDDLFormDocumentUploadFailed(DocumentField documentField, Exception e) {

    }


    @Override
    public void error(Exception e, String userAction) {

    }
}
