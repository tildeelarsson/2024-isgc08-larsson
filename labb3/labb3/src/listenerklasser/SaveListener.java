package listenerklasser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IView;

public class SaveListener implements ActionListener{


		private IView v;

		    public SaveListener (IView vin) {
		        v=vin;
		    }
		    public void actionPerformed(ActionEvent e) {
		      // c.handleSave;
		    	v.relaySave();
		    }
		}