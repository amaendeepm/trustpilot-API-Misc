package com.trustpilot.support.restclient.view;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.JLabel;

public class HyperLinkLabel extends JLabel{

	public HyperLinkLabel(String strURL)  
	{  

		setText(strURL);  

		addMouseListener(new MouseAdapter() {  
			public void mouseEntered(MouseEvent me) {  
				setCursor(new Cursor(Cursor.HAND_CURSOR));  
			}  
			public void mouseExited(MouseEvent me) {  
				setCursor(Cursor.getDefaultCursor());  
			}  
			public void mouseClicked(MouseEvent me)  
			{  
				System.out.println("Clicked on DocLink ..." + strURL);  
				try {  
					Desktop.getDesktop().browse(new URI(strURL)); 
				}  
				catch(Exception e) {  
					System.out.println(e);  
				}  
			}  
		}); 
	}
	
	@Override
	public void setText(String strURL) {
		super.setText("<html><a href=\" " + strURL + "\"> "+strURL+" </a></html>");  
	}
}
