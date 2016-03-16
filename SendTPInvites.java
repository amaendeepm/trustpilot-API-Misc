
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SendTPInvites {
    
   public static void main(String[] args) {
        
       try {
           
            StringBuilder buf = new StringBuilder();
            buf.append(URLEncoder.encode("grant_type", "UTF-8")).append("=").append(URLEncoder.encode("password", "UTF-8"));
            buf.append("&").append(URLEncoder.encode("username", "UTF-8")).append("=").append(URLEncoder.encode("TRUSTPILOT_B2B_EMAIL", "UTF-8"));
            buf.append("&").append(URLEncoder.encode("password", "UTF-8")).append("=").append(URLEncoder.encode("TRUSTPILOT_B2B_PASSWORD", "UTF-8"));
            
            String unit = "TRUSTPILOT_BUSINESS_UNIT_ID";
            String key = "TRUSTPILOT_API_KEY";
            String secret = "TRUSTPILOT_SECRET";
            
            String encoded = Base64.encode((key + ":" + secret).getBytes());
            
            URL u = new URL("https://api.trustpilot.com/v1/oauth/oauth-business-users-for-applications/accesstoken");
            HttpURLConnection conn = (HttpURLConnection)u.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Basic " + encoded);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", Integer.toString(buf.length()));
            
            OutputStream os = conn.getOutputStream();
            os.write(buf.toString().getBytes());
            os.flush();
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            StringBuilder sbuf = new StringBuilder();
            
            String line;
            while((line = rd.readLine()) != null) {
                sbuf.append(line);
            }                        
            os.close();
            rd.close();
            
            conn.disconnect();

            JSONObject authJson = new JSONObject(sbuf.toString());
            
            System.out.println((String)authJson.get("access_token"));
            
            JSONObject json = new JSONObject();
            json.put("recipientEmail", "ami@trustpilot.com");
            json.put("recipientName", "Amandeep Midha");
            json.put("referenceId", "ORDER_ID");
            json.put("templateId", "TRUSTPILOT_KS_TEMPLATE_ID");
            json.put("locale", "en-GB");
            json.put("senderEmail", "noreply.invitations@trustpilot.com");
            json.put("senderName", "MY_NAME");
            json.put("replyTo", "MY_REPLY_TO_ADDRESS"); 
            json.put("preferredSendTime", "2015-03-13T19:12:00");
            json.put("redirectUri", "http://trustpilot.com");
            
        
            System.out.println(json.toString());
            
            u = new URL("https://invitations-api.trustpilot.com/v1/private/business-units/" + unit + "/invitations");
            conn = (HttpURLConnection)u.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + (String)authJson.get("access_token"));
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", Integer.toString(json.toString().length()));
            
            os = conn.getOutputStream();
            os.write(json.toString().getBytes());
            os.flush();

            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            sbuf = new StringBuilder();
            
            while((line = rd.readLine()) != null) {
                sbuf.append(line);
            }

            System.out.println(sbuf.toString());
            
            os.close();
            rd.close();
            conn.disconnect();
           
        } catch (UnsupportedEncodingException ex) {
            System.out.println("UnsupportedEncodingException: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
       } catch (JSONException ex) {
            System.out.println("JSONException" + ex.getMessage());
       }
    }
}
