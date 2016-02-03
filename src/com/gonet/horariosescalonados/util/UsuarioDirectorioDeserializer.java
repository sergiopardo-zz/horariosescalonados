package com.gonet.horariosescalonados.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.gonet.horariosescalonados.bean.BeanUsuarioDirectorio;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class UsuarioDirectorioDeserializer implements JsonDeserializer<BeanUsuarioDirectorio>
{
    @Override
    public BeanUsuarioDirectorio deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
        throws JsonParseException
    {
        JsonElement jsonLdapUserData = je.getAsJsonObject().get("data")
        		.getAsJsonObject().get("customSchemas")
        		.getAsJsonObject().get("ldapUserData");
        
        JsonElement jsonName = je.getAsJsonObject().get("data")
        		.getAsJsonObject().get("name");  
        
        JsonElement jsonExternalId = je.getAsJsonObject().get("data")
        		.getAsJsonObject().get("externalIds");  
        
       String strExternalId = jsonExternalId+"";
       
       
        strExternalId = strExternalId.substring(1, strExternalId.length()-1);
        
       JsonParser parser = new JsonParser();
       
       jsonExternalId = parser.parse(strExternalId);
        
        BeanUsuarioDirectorio empleado = new BeanUsuarioDirectorio();
        
        empleado.setApellido1(jsonLdapUserData.getAsJsonObject().get("apellido1").getAsString());
        empleado.setApellido2(jsonLdapUserData.getAsJsonObject().get("apellido2").getAsString());
        empleado.setCodBancoOficinaPers(jsonLdapUserData.getAsJsonObject().get("codBancoOficinaPers").getAsString());
        empleado.setCodOUNivel10(jsonLdapUserData.getAsJsonObject().get("codOUNivel10").getAsString());
        empleado.setCodOUPadre(jsonLdapUserData.getAsJsonObject().get("codOUPadre").getAsString());
        empleado.setDescCentroCoste(jsonLdapUserData.getAsJsonObject().get("descCentroCoste").getAsString());
        empleado.setDescCentroTrabajo(jsonLdapUserData.getAsJsonObject().get("descCentroTrabajo").getAsString());
        empleado.setDescOUNivel10(jsonLdapUserData.getAsJsonObject().get("descOUNivel10").getAsString());
        empleado.setDescOUPadre(jsonLdapUserData.getAsJsonObject().get("descOUPadre").getAsString());
        empleado.setEmployeeID(jsonExternalId.getAsJsonObject().get("value").getAsString());
        empleado.setGivenName(jsonName.getAsJsonObject().get("givenName").getAsString());
        empleado.setMail(jsonLdapUserData.getAsJsonObject().get("mail").getAsString());
        empleado.setUid(jsonLdapUserData.getAsJsonObject().get("uid").getAsString());
        empleado.setUidJefe(jsonLdapUserData.getAsJsonObject().get("uidJefe").getAsString());
        
        return empleado;
    }
}