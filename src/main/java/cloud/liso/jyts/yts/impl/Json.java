package cloud.liso.jyts.yts.impl;

import cloud.liso.jyts.entities.Response;
import cloud.liso.jyts.entities.ResponseDeserializer;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Json {
    private GsonBuilder gson;
    private ResponseDeserializer responseDeserializer;

    @Autowired
    public Json(GsonBuilder gson, ResponseDeserializer responseDeserializer) {
        this.gson = gson;
        this.responseDeserializer = responseDeserializer;
        gson.registerTypeAdapter(Response.class, responseDeserializer);
    }

    public Response parse(String json) {
       return gson.create().fromJson(json, Response.class);
    }
}
