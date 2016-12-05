import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*; 
import java.util.*;
import java.io.IOException; 
import java.util.concurrent.TimeUnit;
public class MQTTSampleX {
  public static void main(String[] args) throws IOException, InterruptedException { 
   
    String a,b,c,d,e; 
   Process p = Runtime.getRuntime().exec("bash rodgegav.sh");
    TimeUnit.SECONDS.sleep(1);
    Scanner wx = new Scanner(new File("systemfile.txt")); 
		
       		    a = wx.nextLine(); 
       		    b = wx.nextLine(); 
           	    c = wx.nextLine(); 
                    d = wx.nextLine(); 
	            e = wx.nextLine();
	
	
        
	
        
    
   
    

	 
    String content = a + " " + b + " " + c + " " + d + " " + e  ;
    	
    String topic        = "Assignment";
    int qos             = 1;
    String broker       = "tcp://m21.cloudmqtt.com:16143";

    //MQTT client id to use for the device. "" will generate a client id automatically
    String clientId     = "ClientId";

    MemoryPersistence persistence = new MemoryPersistence();
    try {
      MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
      mqttClient.setCallback(new MqttCallback() {
        public void messageArrived(String topic, MqttMessage msg)
                  throws Exception {
                      System.out.println("Recived:" + topic);
                      System.out.println("Recived:" + new String(msg.getPayload()));
                }

        public void deliveryComplete(IMqttDeliveryToken arg0) {
                    System.out.println("Delivary complete");
                }

        public void connectionLost(Throwable arg0) {
                    // TODO Auto-generated method stub
                }
      });

      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);
      connOpts.setUserName("viwyteat");
      connOpts.setPassword(new char[]{'9', 'e', 'm', 's', 'F', 'p', 'F', 'O', '4', 'B', 'R','M'});
      mqttClient.connect(connOpts);
      MqttMessage message = new MqttMessage(content.getBytes());
      message.setQos(qos); 
      System.out.println("Publish message: " + message);
      mqttClient.subscribe(topic, qos);
      mqttClient.publish(topic, message);
      mqttClient.disconnect();
      System.exit(0);
    } catch(MqttException me) {
      System.out.println("reason "+me.getReasonCode());
      System.out.println("msg "+me.getMessage());
      System.out.println("loc "+me.getLocalizedMessage());
      System.out.println("cause "+me.getCause());
      System.out.println("excep "+me);
      me.printStackTrace();
    }
  }
}
