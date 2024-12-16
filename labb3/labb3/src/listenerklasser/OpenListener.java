package listenerklasser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IView;

public class OpenListener implements ActionListener{


		private IView v;

		    public OpenListener (IView vin) {
		        v=vin;
		    }
		    public void actionPerformed(ActionEvent e) {
		      // c.handleOpen;
		    	v.relayOpen();
		    }
		}


