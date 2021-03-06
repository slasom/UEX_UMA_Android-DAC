/**
 * Health Alerts
 * This application allows users to send and receive information about a user's health condition.
 *
 * OpenAPI spec version: 1.0
 * Contact: info@spilab.es
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package com.unexuma.mobile_shoppingcenter.resource;


import android.content.Context;
import android.util.Log;

import com.unexuma.mobile_shoppingcenter.response.StatusResponse;
import com.unexuma.mobile_shoppingcenter.service.MQTTService;
import com.unexuma.mobile_shoppingcenter.service.MqttClient;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;


public class StatusResource {

    private Context context;
    private MqttClient client;

    private static DecimalFormat df2 = new DecimalFormat("0.00",new DecimalFormatSymbols(Locale.ENGLISH));
    private int min=35;
    private int max=40;


    private StatusResponse statusResponse;
    private JSONObject response;

    public StatusResource(Context context) {
        this.context = context;
        client = new MqttClient();
        response= new JSONObject();
    }

  public Exception executeMethod(StatusResponse response) throws MqttException, UnsupportedEncodingException{

        statusResponse=response;

      switch (response.getMethod()){
          case "getBodyTemperature":
           getBodyTemperature();
          break;
          case "getTemperature":
           getTemperature();
          break;
          case "getUser":
           getUser();
          break;
          default:
              client.publishMessage( MQTTService.getClient(), "Error: Not Found Method",1,statusResponse.getSender());
              return new Exception("Not found method.");
      }

      return null;
  }

  /**
  * Gets the user temperature
  * Obtain current user temperature
   * @return Double
  */
  public void getBodyTemperature () throws MqttException, UnsupportedEncodingException{

     //TODO: Process the information, etc.



     //TODO: Return Double on reply.
      client.publishMessage( MQTTService.getClient(), "getBodyTemperature OK",1,statusResponse.getSender());
  }
  /**
  * Gets the environment temperature
  * Obtain current environment temperature
   * @return Double
  */
  public void getTemperature () throws MqttException, UnsupportedEncodingException{

     //TODO: Process the information, etc.



     //TODO: Return Double on body.
      Log.i("ID REQUEST", statusResponse.getIdRequest());

      Random r = new Random();
      double random = min + r.nextDouble() * (max - min);
      double temp = Double.valueOf(df2.format(random));
      Log.i("Tmp: ", String.valueOf(temp));

      try {

          response.put("idRequest",statusResponse.getIdRequest());
          response.put("devices",statusResponse.getDevices());
          response.put("body",temp);
      } catch (JSONException e) {

          e.printStackTrace();
      }

      Log.i("send msg to ",statusResponse.getSender()+"|"+response);
      client.publishMessage(MQTTService.getClient(), String.valueOf(response),1,statusResponse.getSender());
  }
  /**
  * Gets user information
  * Obtain user general information
   * @return User
  */
  public void getUser () throws MqttException, UnsupportedEncodingException{

     //TODO: Process the information, etc.



     //TODO: Return User on reply.
      client.publishMessage( MQTTService.getClient(), "getUser OK",1,statusResponse.getSender());
  }





}
