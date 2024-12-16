package listenerklasser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IView;

public class ExitListener implements ActionListener{


		private IView v;

		    public ExitListener (IView vin) {
		        v=vin;
		    }
		    public void actionPerformed(ActionEvent e) {
		      // c.handleExit;
		    	v.relayExit();
		    }
		}