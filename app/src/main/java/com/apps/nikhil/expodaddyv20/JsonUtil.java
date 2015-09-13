package com.apps.nikhil.expodaddyv20;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nikhil on 29-08-2015.
 */
public class JsonUtil {

    public static String toJSon(Person person) {

        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("name", person.getName()); // Set the first name/pair
       //     jsonObj.put("surname", person.getSurname());
            jsonObj.put("email", person.getEmail());
            jsonObj.put("password", person.getPassword());
            jsonObj.put("age", person.getAge());
            jsonObj.put("gender",person.getGender());

            {
                JSONArray jsonArr = new JSONArray();

                JSONObject pnObj = new JSONObject();
                pnObj.put("phone", person.getNumber());
//                pnObj.put("type", pn.getType());
                jsonArr.put(pnObj);

                jsonObj.put("phoneNumber", jsonArr);
            }

            jsonObj.put("zip", person.getZip());

            {
                JSONArray jsonArr = new JSONArray();

                JSONObject locObj = new JSONObject();
                locObj.put("latitude", person.getLat());
                locObj.put("longitude", person.getLong());
                jsonArr.put(locObj);

                jsonObj.put("location", jsonArr);
            }

           /* {
                JSONObject jsonAdd = new JSONObject(); // we need another object to store the address
                jsonAdd.put("address", person.getAddress());
                jsonAdd.put("city",person.getCity());

                // We add the object to the main object
                jsonObj.put("address", jsonAdd);
            } */


            return jsonObj.toString();
        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        return null;
    }


}
