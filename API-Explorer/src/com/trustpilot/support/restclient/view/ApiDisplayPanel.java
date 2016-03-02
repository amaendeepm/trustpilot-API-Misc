package com.trustpilot.support.restclient.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import com.trustpilot.support.restclient.model.TrustPilotAPI;

public class ApiDisplayPanel extends JPanel {
	
    private JPanel topPanel;
    private JPanel fieldsPanel;

    private static final int GAP = 5;
    private GridBagConstraints gbc;
    
    //Composition
    private JLabel lblDescription;
    private JLabel lblDoclink;
    private JTextField txtApisyntax;
    private JLabel lblTytpe;
    
    private JLabel[] mandatoryParamNames;
    private JTextField[] mandatoryParams;
    
    private JTextField[] additionalParamNames = new JTextField[5];
    private JTextField[] additionalParams = new JTextField[5];
    
    private JTable paramTable = new JTable();
    //private TableModel paramModel = new TableModel();

    public ApiDisplayPanel() {
        setOpaque(false);
        setLayout(new BorderLayout(GAP, GAP));  
        setBorder(BorderFactory.createEmptyBorder(0,100,100,100));
        topPanel = getSubPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        lblDescription = new JLabel("No Description Available");
        lblDescription.setFont(new Font("Serif", Font.PLAIN, 14));
        
        lblDoclink = new HyperLinkLabel("------------ No Documentation Available ---------------");
        txtApisyntax = new JTextField("---------------  No Syntax Available  ---------------");
        //topPanel.add(apiSelect);
        topPanel.add(lblDescription);
        topPanel.add(lblDoclink);
        topPanel.add(txtApisyntax);
        
        topPanel.add(paramTable);
        //Fields to add here!
        add(topPanel);
    }
    
    public ApiDisplayPanel(TrustPilotAPI api) { //ALMOST NEVER USED, only refresh method used below !
        setOpaque(false);
        setLayout(new BorderLayout(GAP, GAP));  
        setBorder(BorderFactory.createEmptyBorder(0,100,100,100));
        topPanel = getSubPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        
        lblDoclink = new HyperLinkLabel(api.getDocLink());
        txtApisyntax = new JTextField(api.getSyntax());
        //topPanel.add(apiSelect);
        
		lblDescription = new JLabel(api.getDescription());
        topPanel.add(lblDescription);
        topPanel.add(lblDoclink);
        topPanel.add(txtApisyntax);
        //Fields to add here!
        add(topPanel);
    }
    
    public void refresh(TrustPilotAPI api) { // Lambda make me do this cheeky method! Mike my witness :-)
    	
        lblDescription.setText(api.getDescription());
        lblDoclink.setText(api.getDocLink());
        txtApisyntax.setText(api.getSyntax());
    	
    	Pattern p = Pattern.compile("\\{([^}]*)\\}");
        Matcher m = p.matcher(api.getSyntax());
        

		List<String> params = new ArrayList<String>();
		
		while (m.find()) {
			System.out.println("Found a " + m.group() + ".");
			params.add(m.group());
		}
		
		JLabel lbl = new JLabel("Optional Additional Parameters ( Name & Value): ");
		
		
		for(int i=0;i<additionalParamNames.length;i++) {
			System.out.println(" param "+i);
		}
		
        //Fields to add here!
    }

    private JPanel getSubPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        return panel;
    }

}
