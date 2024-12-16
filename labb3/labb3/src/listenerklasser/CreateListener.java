package listenerklasser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IView;

public class CreateListener implements ActionListener{


		private IView v;

		    public CreateListener (IView vin) {
		        v=vin;
		    }
		    public void actionPerformed(ActionEvent e) {
		      // c.handleCreate;
		    	v.relayCreate();
		    }
		}