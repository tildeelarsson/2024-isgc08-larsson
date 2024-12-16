package listenerklasser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IView;

public class WriteListener implements ActionListener{


		private IView v;

		    public WriteListener (IView vin) {
		        v=vin;
		    }
		    public void actionPerformed(ActionEvent e) {
		      // c.handleWrite;
		    	v.relayWrite();
		    }
		}