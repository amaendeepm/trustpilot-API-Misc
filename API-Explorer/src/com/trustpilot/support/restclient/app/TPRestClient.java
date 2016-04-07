package com.trustpilot.support.restclient.app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.trustpilot.support.restclient.model.TrustPilotAPI;
import com.trustpilot.support.restclient.model.TrustPilotAPIDefs;
import com.trustpilot.support.restclient.view.ApiDisplayPanel;
	
public class TPRestClient extends JFrame {
	
	
	TrustPilotAPIDefs f;
	
	
	//static List<TrustPilotAPI> public_apis ;


	public TPRestClient() {		
		
		
		setSize(1500, 600);
		setLocationByPlatform(true);

		
		JTabbedPane tabbedPane = new JTabbedPane();
		//ImageIcon icon = createImageIcon("images/middle.gif");

		JComponent panel1 = makeOAuthPanel();
		tabbedPane.addTab("Get OAuth Token", null, panel1,"Get OAuth Token");	
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeCustomerAPIPanel();
		tabbedPane.addTab("Trustpilot Customer APIs", null, panel2,"Trustpilot Customer APIs");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
//		JComponent panel3 = makeCustomerAPIPanel();
//		tabbedPane.addTab("Trustpilot APIs", null, panel2,"Trustpilot Customer APIs");
//		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		add(tabbedPane);

	}

	
	protected JComponent makePublicAPIPanel() {
		JPanel panel = new JPanel(false);
		JComboBox cmbAPI = new JComboBox();
		ApiDisplayPanel apiPanel = new ApiDisplayPanel();
		JButton btnExecute = new JButton("Execute");
		
		//cmbAPI.addItem(item);
		  
		  
		return panel;
	}
	
	protected JComponent makeCustomerAPIPanel() {
		
		JPanel panel = new JPanel(false);
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS ));
		
		JPanel panelInput = new JPanel(false);
		JPanel panelOutput = new JPanel(false);
		//JComboBox cmbAPI = new JComboBox();
		//GET CustomerAPI List
		//cmbAPI.addItem(arg0);
		
        JComboBox apiSelect = new JComboBox();
        apiSelect.addItem("----------------------------- Select Trustpilot API --------------------------------");
        
        Iterator<TrustPilotAPI> itr = TrustPilotAPIDefs.customer_apis.iterator();
        while(itr.hasNext()) {
        	apiSelect.addItem(itr.next().getName());
        }
        panelInput.add(apiSelect);
        ApiDisplayPanel apiPanel = new ApiDisplayPanel();
        panelInput.add(apiPanel);
        JButton btnExecute = new JButton("Execute");
        btnExecute.addActionListener(this.getButtonListener());
     
        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
              int state = itemEvent.getStateChange();
              if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
            	  System.out.println("New Selected is: " + itemEvent.getItem());
            	  apiPanel.refresh(TrustPilotAPIDefs.getCustomerAPI(itemEvent.getItem().toString()));       	  
              }
            }
          };
          
        apiSelect.addItemListener(itemListener);
		panelInput.add(btnExecute);

		panel.add(panelInput);
		panel.add(panelOutput);	
		return panel;
	}
	
	
	
	protected ActionListener getButtonListener() {
		ActionListener actionExec = new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Button execute pressed");
            }
		};
		
		return actionExec;		
	}
	
	
	protected JComponent makeOAuthPanel() {
		JPanel panel = new JPanel(false);
		//panel.setPreferredSize(new Dimension(80,150));
		
		JLabel email = new JLabel("User Email");
		JTextField userEmail = new JTextField("ami@trustpilot.com"); //DEFAUL_B2B_USER
		email.setBounds(5, 5, 100, 100);
		panel.add(email);
		panel.add(userEmail);
		
		JLabel domain = new JLabel("Password");
		JTextField bizDomain = new JTextField("DEFAUL_B2B_PASSWORD");
		panel.add(domain);
		panel.add(bizDomain);
		
		JLabel unit = new JLabel("Business Unit");
		JTextField bizUnit = new JTextField("DEFAULT_BIZ_UNIT_VALUE");
		panel.add(unit);
		panel.add(bizUnit);
		
		JLabel apiKey = new JLabel("API Key");
		JTextField userAPIKey = new JTextField("DEFAULT_API_KEY_VALUE");
		panel.add(apiKey);
		panel.add(userAPIKey);
		
		JLabel apiSecret = new JLabel("Secret");
		JTextField userAPISecret = new JTextField("DEFAULT_SECRET_VALUE");
		panel.add(apiSecret);
		panel.add(userAPISecret);
		
		JButton btnGetToken = new JButton("Get Token");
		panel.add(btnGetToken);
		
		
		JTextField txtToken = new JTextField("<token-returned>",100);
		txtToken.setEditable(false);
		
		panel.add(txtToken);
		
		btnGetToken.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
                System.out.println("To get OAuthToken type password ... ");
                String token = "";
                try {
                token = getAccessToken(userEmail.getText().trim(),
                		bizDomain.getText().trim(),
                		bizUnit.getText().trim(),
                		userAPIKey.getText().trim(),
                		userAPISecret.getText().trim()             		
                		
                		
                		);
                txtToken.setText(token);
                } catch(JSONException jsone) {
                	jsone.printStackTrace();
                	txtToken.setText("Exception:"+jsone.getMessage());
                }
                catch(IOException ioe) {
                	ioe.printStackTrace();
                	txtToken.setText("Exception:"+ioe.getMessage());
                }
            }
        });  

		//panel.setLayout(new GridLayout(5, 2));
		
		return panel;
	}
	
	
	
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = TPRestClient.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	
	protected String getAccessToken(String userEmail, String domain, String unit, String key, String secret) throws IOException, ProtocolException, JSONException {
		StringBuilder buf = new StringBuilder();
        buf.append(URLEncoder.encode("grant_type", "UTF-8")).append("=").append(URLEncoder.encode("password", "UTF-8"));
        buf.append("&").append(URLEncoder.encode("username", "UTF-8")).append("=").append(URLEncoder.encode(userEmail, "UTF-8"));
        buf.append("&").append(URLEncoder.encode("password", "UTF-8")).append("=").append(URLEncoder.encode(domain, "UTF-8"));
        
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
        System.out.println("RETURNED JSON ---- " + authJson);

        return (String)authJson.get("access_token");
		
	}
	


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			public void run() {
				new TPRestClient();				
			}
			
		});
	}

}
