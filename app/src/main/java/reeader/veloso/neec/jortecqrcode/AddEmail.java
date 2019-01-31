package reeader.veloso.neec.jortecqrcode;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddEmail extends StringRequest {

    private static final String REGISTER_LOGIN_URL = "https://jortec18app.neec-fct.com/jortec2019/addemail.php";
    private Map<String,String> params;

    public AddEmail(String email,  Response.Listener<String> listener){
        super(Method.POST,REGISTER_LOGIN_URL,listener,null);
        params = new HashMap<>();

        params.put("email",email);



    }



    @Override
    public Map<String, String> getParams() {
        return params;
    }
}