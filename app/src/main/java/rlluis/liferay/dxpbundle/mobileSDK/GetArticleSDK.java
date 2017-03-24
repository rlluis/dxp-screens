package rlluis.liferay.dxpbundle.mobileSDK;

import com.liferay.mobile.android.service.BaseService;
import com.liferay.mobile.android.service.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by rlluis on 17/10/2016.
 */

public class GetArticleSDK extends BaseService {

    public GetArticleSDK(Session session) {
        super(session);
    }

    public JSONObject getAssetEntry(long entryId) throws Exception {
        JSONObject _command = new JSONObject();

        try {
            JSONObject _params = new JSONObject();

            _params.put("entryId", entryId);
            _params.put("locale", Locale.US);

            _command.put("/screens.screensassetentry/get-asset-entry", _params);
        }
        catch (JSONException _je) {
            throw new Exception(_je);
        }

        JSONArray _result = session.invoke(_command);

        if (_result == null) {
            return null;
        }

        return _result.getJSONObject(0);
    }

    public JSONObject getAssetEntryV(long entryId, String locale) throws Exception {
        JSONObject _command = new JSONObject();

        try {
            JSONObject _params = new JSONObject();

            _params.put("entryId", entryId);
            _params.put("locale", checkNull(locale));

            _command.put("/screens.screensassetentry/get-asset-entry", _params);
        } catch (JSONException _je) {
            throw new Exception(_je);
        }

        JSONArray _result = session.invoke(_command);

        if (_result == null) {
            return null;
        }

        return _result.getJSONObject(0);
    }
}
