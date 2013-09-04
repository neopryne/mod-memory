package net.vhati.modmanager.ui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


/**
 * A panel that consumes all mouse/keyboard events, for use as a glass pane.
 */
public class InertPanel extends JPanel {

	private KeyEventDispatcher nullDispatcher;


	public InertPanel() {
		super();

		nullDispatcher = new KeyEventDispatcher() {
			@Override
			public boolean dispatchKeyEvent( KeyEvent e ) {
				return InertPanel.this == ( (JFrame)SwingUtilities.getWindowAncestor( (Component)e.getSource() ) ).getGlassPane();
			}
		};

    this.addMouseListener(new MouseAdapter() {
			@Override
      public void mousePressed( MouseEvent e ) {e.consume();}
			@Override
      public void mouseReleased( MouseEvent e ) {e.consume();}
    });

		this.setCursor(Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ));
		this.setOpaque( false );
	}


	@Override
	public void setVisible( boolean b ) {
		super.setVisible( b );
		if ( b ) {
			KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher( nullDispatcher );
		} else {
			KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher( nullDispatcher );
		}
	}
}