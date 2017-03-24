package rlluis.liferay.dxpbundle.mobileSDK;

import com.liferay.mobile.android.service.BaseService;
import com.liferay.mobile.android.service.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by rlluis on 22/02/2017.
 */

public class GetUserSegment extends BaseService {

    public GetUserSegment(Session session) {
        super(session);
    }

    public JSONObject getUserSegments(long userId, boolean userSegmentActive) throws Exception {
        JSONObject _command = new JSONObject();

        try {
            JSONObject _params = new JSONObject();

            _params.put("userId", userId);
            _params.put("active", userSegmentActive);

            _command.put("/ct.anonymoususerusersegment/get-user-segments-by-user-id", _params);
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

    public boolean userInSegment(long userId, String userSegmentId, boolean userSegmentActive) throws Exception{

        JSONObject _command = new JSONObject();
        boolean resultado=false;

        try {
            JSONObject _params = new JSONObject();

            _params.put("userId", userId);
            _params.put("active", userSegmentActive);

            _command.put("/ct.anonymoususerusersegment/get-user-segments-by-user-id", _params);
        }
        catch (JSONException _je) {
            throw new Exception(_je);
        }

        JSONArray _result = session.invoke(_command);

        if (_result == null) {
            resultado=false;
        }else{
            String segmento="";
            for (int i = 0; i < _result.length(); i++) {

                JSONObject jsonObject = _result.getJSONArray(i).getJSONObject(0);
                segmento= jsonObject.getString("userSegmentId").toString();

                if (segmento.equals(userSegmentId)){
                    resultado=true;
                }
            }
        }

        return resultado;

    }

}
