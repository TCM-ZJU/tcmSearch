package dartie.prefuse.controls;

import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import dartie.prefuse.Display;
import dartie.prefuse.Visualization;
import dartie.prefuse.util.GraphicsLib;
import dartie.prefuse.util.display.DisplayLib;
import dartie.prefuse.util.ui.UILib;
import dartie.prefuse.visual.VisualItem;



/**
 * Zooms a display such that all items within a given group will fit within
 * the display bounds. By default, this achieved by clicking the right
 * mouse button once, with no dragging.
 * 
 * @author <a href="http://jheer.org">jeffrey heer</a>
 */
public class ZoomToFitControl extends ControlAdapter {

    private long m_duration = 2000;
    private int m_margin = 50;
    private int m_button = RIGHT_MOUSE_BUTTON;
    private boolean m_zoomOverItem = true;
    private String m_group = Visualization.ALL_ITEMS;
    
    /**
     * Create a new ZoomToFitControl.
     */
    public ZoomToFitControl() {
    }
    
    /**
     * Create a new ZoomToFitControl.
     * @param group the data group that should fit the Display
     */
    public ZoomToFitControl(String group) {
        this.m_group = group;
    }
    
    /**
     * Create a new ZoomToFitControl.
     * @param button the mouse button used to initiate the zoom-to-fit. One of
     * {@link Control#LEFT_MOUSE_BUTTON}, {@link Control#MIDDLE_MOUSE_BUTTON},
     * or {@link Control#RIGHT_MOUSE_BUTTON}.
     */
    public ZoomToFitControl(int button) {
        this.m_button = button;
    }

    /**
     * Create a new ZoomToFitControl.
     * @param group the data group that should fit the Display
     * @param button the mouse button used to initiate the zoom-to-fit. One of
     * {@link Control#LEFT_MOUSE_BUTTON}, {@link Control#MIDDLE_MOUSE_BUTTON},
     * or {@link Control#RIGHT_MOUSE_BUTTON}.
     */
    public ZoomToFitControl(String group, int button) {
        this.m_group = group;
        this.m_button = button;
    }
    
    /**
     * Create a new ZoomToFitControl.
     * @param group the data group that should fit the Display
     * @param margin the margin, in pixels, desired between the group 
     * and the edge of the display
     * @param duration the duration of the animated zoom
     * @param button the mouse button used to initiate the zoom-to-fit. One of
     * {@link Control#LEFT_MOUSE_BUTTON}, {@link Control#MIDDLE_MOUSE_BUTTON},
     * or {@link Control#RIGHT_MOUSE_BUTTON}.
     */
    public ZoomToFitControl(String group, int margin, 
                            long duration, int button)
    {
        this.m_group = group;
        this.m_margin = margin;
        this.m_duration = duration;
        this.m_button = button;
    }
    
    /**
     * @see dartie.prefuse.controls.Control#itemClicked(prefuse.visual.VisualItem, java.awt.event.MouseEvent)
     */
    public void itemClicked(VisualItem item, MouseEvent e) {
        if ( m_zoomOverItem )
            mouseClicked(e);
    }
    
    /**
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    public void mouseClicked(MouseEvent e) {
        if ( UILib.isButtonPressed(e, m_button) ) {
            Display display = (Display)e.getComponent();
            Visualization vis = display.getVisualization();
            Rectangle2D bounds = vis.getBounds(m_group);
            GraphicsLib.expand(bounds, m_margin/display.getScale());
            DisplayLib.fitViewToBounds(display, bounds, m_duration);
        }
    }
    
    /**
     * Indicates if the zoom control will work while the mouse is
     * over a VisualItem.
     * @return true if the control still operates over a VisualItem
     */
    public boolean isZoomOverItem() {
        return m_zoomOverItem;
    }

    /**
     * Determines if the zoom control will work while the mouse is
     * over a VisualItem
     * @param zoomOverItem true to indicate the control operates
     * over VisualItems, false otherwise
     */
    public void setZoomOverItem(boolean zoomOverItem) {
        this.m_zoomOverItem = zoomOverItem;
    }
    
} // end of class ZoomToFitControl
