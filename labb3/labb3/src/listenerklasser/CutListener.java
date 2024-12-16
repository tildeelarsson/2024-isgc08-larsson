package listenerklasser;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IView;

public class CutListener implements ActionListener{


		private IView v;

		    public CutListener (IView vin) {
		        v=vin;
		    }
		    public void actionPerformed(ActionEvent e) {
		      // c.handleCut;
		    	v.relayCut();
		    }
		}