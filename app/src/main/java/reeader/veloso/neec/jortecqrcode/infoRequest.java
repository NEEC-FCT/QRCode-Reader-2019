package reeader.veloso.neec.jortecqrcode;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Veloso on 24/07/2017.
 */


public class infoRequest extends StringRequest {

    private static final String REGISTER_LOGIN_URL = "http://jortec18app.neec-fct.com/pica.php";
    private Map<String,String> params;

    public infoRequest(String email,Response.Listener<String> listener){
        super(Method.POST,REGISTER_LOGIN_URL,listener,null);
        params = new HashMap<>();

        params.put("email",email);


    }



    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
