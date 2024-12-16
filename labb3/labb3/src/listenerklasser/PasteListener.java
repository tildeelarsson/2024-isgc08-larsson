package listenerklasser;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IView;

public class PasteListener implements ActionListener{


		private IView v;

		    public PasteListener(IView vin) {
		        v=vin;
		    }
		    public void actionPerformed(ActionEvent e) {
		      // c.handlePaste;
		    	v.relayPaste();
		    }
		}
