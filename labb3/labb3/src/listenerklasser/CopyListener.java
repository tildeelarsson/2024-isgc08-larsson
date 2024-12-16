package listenerklasser;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IView;

public class CopyListener implements ActionListener{


		private IView v;

		    public CopyListener (IView vin) {
		        v=vin;
		    }
		    public void actionPerformed(ActionEvent e) {
		      // c.handleCopy;
		    	v.relayCopy();
		    }
		}
