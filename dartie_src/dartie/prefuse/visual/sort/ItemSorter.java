package dartie.prefuse.visual.sort;

import java.util.Comparator;

import dartie.prefuse.Visualization;
import dartie.prefuse.visual.DecoratorItem;
import dartie.prefuse.visual.VisualItem;


/**
 * ItemSorter instances provide an integer score for each VisualItem;
 * these scores are then used to sort the items in ascending order of score.
 * ItemSorters are used to determine the rendering order of items in a
 * Display.
 * 
 * @author <a href="http://jheer.org">jeffrey heer</a>
 */
public class ItemSorter implements Comparator {

    /**
     * <p>Return an ordering score for an item. The default scoring imparts
     * the following order:
     * hover items > highlighted items > items in the
     * {@link dartie.prefuse.Visualization#FOCUS_ITEMS} set >
     * {@link dartie.prefuse.Visualization#SEARCH_ITEMS} set >
     * DecoratorItem instances > normal VisualItem instances. A zero
     * score is returned for normal items, with scores starting at
     * 1&lt;&lt;27 for other items, leaving the number range beneath that
     * value open for additional nuanced scoring.</p> 
     * 
     * <p>Subclasses can override this method to provide custom sorting
     * criteria.</p>
     * @param item the VisualItem to provide an ordering score
     * @return the ordering score
     */
    public int score(VisualItem item) {
        int score = 0;
        if ( item.isHover() ) {
            score += (1<<29);
        }
        if ( item.isHighlighted() ) {
            score += (1<<28);
        }
        if ( item.isInGroup(Visualization.FOCUS_ITEMS) ) {
            score += (1<<27);
        }
        if ( item.isInGroup(Visualization.SEARCH_ITEMS) ) {
            score += (1<<26);
        }
        if ( item instanceof DecoratorItem ) {
            score += (1<<27);
        }
        return score;
    }
    
    /**
     * Compare two items based on their ordering scores. Calls the
     * {@link #score(VisualItem)} on each item and compares the result.
     * @param v1 the first VisualItem to compare
     * @param v2 the second VisualItem to compare
     * @return -1 if score(v1) &lt; score(v2), 1 if score(v1) &gt; score(v2)
     * and 0 if score(v1) == score(v2).
     */
    public int compare(VisualItem v1, VisualItem v2) {
        int score1 = score(v1);
        int score2 = score(v2);
        return (score1<score2 ? -1 : (score1==score2 ? 0 : 1));
    }
    
    /**
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     * @see #compare(VisualItem, VisualItem)
     */
    public int compare(Object o1, Object o2) {
        if ( !(o1 instanceof VisualItem && o2 instanceof VisualItem) ) {
            throw new IllegalArgumentException();
        }
        return compare((VisualItem)o1, (VisualItem)o2);
    }

} // end of class ItemSorter
