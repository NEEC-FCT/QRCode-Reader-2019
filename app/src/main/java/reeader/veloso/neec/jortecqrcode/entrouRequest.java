package reeader.veloso.neec.jortecqrcode;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Veloso on 24/07/2017.
 */


public class entrouRequest extends StringRequest {

    private static final String REGISTER_LOGIN_URL = "http://jortec18app.neec-fct.com/entrou.php";
    private Map<String,String> params;

    public entrouRequest(String email,int estado,Response.Listener<String> listener){
        super(Method.POST,REGISTER_LOGIN_URL,listener,null);
        params = new HashMap<>();

        params.put("email",email);
        params.put("estado",Integer.toString(estado));


    }



    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
