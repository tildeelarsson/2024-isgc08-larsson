package listenerklasser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.IView;

public class SaveAsListener implements ActionListener{


		private IView v;

		    public SaveAsListener (IView vin) {
		        v=vin;
		    }
		    public void actionPerformed(ActionEvent e) {
		      // c.handleSaveAs;
		    	v.relaySaveAs();
		    }
		}