
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

public class OAuthTrustpilotSample {
    
   public static void main(String[] args) {
        
       try {
           
            StringBuilder buf = new StringBuilder();
            buf.append(URLEncoder.encode("grant_type", "UTF-8")).append("=").append(URLEncoder.encode("password", "UTF-8"));
            buf.append("&").append(URLEncoder.encode("username", "UTF-8")).append("=").append(URLEncoder.encode("YOUR_EMAIL", "UTF-8"));
            buf.append("&").append(URLEncoder.encode("password", "UTF-8")).append("=").append(URLEncoder.encode("YOUR_PASSWORD", "UTF-8"));
            
            String key = "YOUR_API_KEY";
            String secret = "YOUR_SECRET";
            
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
            
            System.out.println("Token: "+ (String)authJson.get("access_token"));
            
           
        } catch (UnsupportedEncodingException ex) {
            System.out.println("UnsupportedEncodingException: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
       } catch (JSONException ex) {
            System.out.println("JSONException" + ex.getMessage());
       }
    }
}
