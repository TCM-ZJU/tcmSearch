package dartie.prefuse.data.tuple;

import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.event.SwingPropertyChangeSupport;

import dartie.prefuse.data.Schema;
import dartie.prefuse.data.Table;
import dartie.prefuse.data.Tuple;
import dartie.prefuse.data.event.EventConstants;
import dartie.prefuse.data.event.TupleSetListener;
import dartie.prefuse.data.expression.Expression;
import dartie.prefuse.data.expression.Predicate;
import dartie.prefuse.data.util.FilterIteratorFactory;
import dartie.prefuse.util.collections.CopyOnWriteArrayList;


/**
 * Abstract base class for TupleSet implementations. Provides mechanisms for
 * generating filtered tuple iterators, maintain listeners, and supporting
 * bound client properties.
 * 
 * @author <a href="http://jheer.org">jeffrey heer</a>
 */
public abstract class AbstractTupleSet implements TupleSet {
    
    /**
     * @see dartie.prefuse.data.tuple.TupleSet#tuples(prefuse.data.expression.Predicate)
     */
    public Iterator tuples(Predicate filter) {
        if ( filter == null ) {
            return tuples();
        } else {
            return FilterIteratorFactory.tuples(this, filter);
        }
    }
    
    // -- TupleSet Methods ----------------------------------------------------
    
    private CopyOnWriteArrayList m_tupleListeners;
    
    /**
     * @see dartie.prefuse.data.tuple.TupleSet#addTupleSetListener(prefuse.data.event.TupleSetListener)
     */
    public void addTupleSetListener(TupleSetListener tsl) {
        if ( m_tupleListeners == null )
            m_tupleListeners = new CopyOnWriteArrayList();
        if ( !m_tupleListeners.contains(tsl) )
            m_tupleListeners.add(tsl);
    }
    
    /**
     * @see dartie.prefuse.data.tuple.TupleSet#removeTupleSetListener(prefuse.data.event.TupleSetListener)
     */
    public void removeTupleSetListener(TupleSetListener tsl) {
        if ( m_tupleListeners != null ) {
            m_tupleListeners.remove(tsl);
        }
    }
    
    /**
     * Fire a Tuple event.
     * @param t the Table on which the event has occurred
     * @param start the first row changed
     * @param end the last row changed
     * @param type the type of event, one of
     * {@link dartie.prefuse.data.event.EventConstants#INSERT} or
     * {@link dartie.prefuse.data.event.EventConstants#DELETE}.
     */
    protected void fireTupleEvent(Table t, int start, int end, int type) {
        if ( m_tupleListeners != null && m_tupleListeners.size() > 0 ) {
            Object[] lstnrs = m_tupleListeners.getArray();
            Tuple[] tuples = new Tuple[end-start+1];
            for ( int i=0, r=start; r <= end; ++r, ++i ) {
                tuples[i] = t.getTuple(r);
            }
            for ( int i=0; i<lstnrs.length; ++i ) {
                TupleSetListener tsl = (TupleSetListener)lstnrs[i];
                if ( type == EventConstants.INSERT ) {
                    tsl.tupleSetChanged(this, tuples, EMPTY_ARRAY);
                } else {
                    tsl.tupleSetChanged(this, EMPTY_ARRAY, tuples);
                }
            }
        }
    }
    
    /**
     * Fire a Tuple event.
     * @param t the tuple that has been added or removed
     * @param type the type of event, one of
     * {@link dartie.prefuse.data.event.EventConstants#INSERT} or
     * {@link dartie.prefuse.data.event.EventConstants#DELETE}.
     */
    protected void fireTupleEvent(Tuple t, int type) {
        if ( m_tupleListeners != null && m_tupleListeners.size() > 0 ) {
            Object[] lstnrs = m_tupleListeners.getArray();
            Tuple[] ts = new Tuple[] {t};
            for ( int i=0; i<lstnrs.length; ++i ) {
                TupleSetListener tsl = (TupleSetListener)lstnrs[i];
                if ( type == EventConstants.INSERT ) {
                    tsl.tupleSetChanged(this, ts, EMPTY_ARRAY);
                } else {
                    tsl.tupleSetChanged(this, EMPTY_ARRAY, ts);
                }
            }
        }
    }
    
    /**
     * Fire a Tuple event.
     * @param added array of Tuples that have been added, can be null
     * @param removed array of Tuples that have been removed, can be null
     */
    protected void fireTupleEvent(Tuple[] added, Tuple[] removed) {
        if ( m_tupleListeners != null && m_tupleListeners.size() > 0 ) {
            Object[] lstnrs = m_tupleListeners.getArray();
            added = added==null ? EMPTY_ARRAY : added;
            removed = removed==null ? EMPTY_ARRAY : removed;
            for ( int i=0; i<lstnrs.length; ++i ) {
                TupleSetListener tsl = (TupleSetListener)lstnrs[i];
                tsl.tupleSetChanged(this, added, removed);
            }
        }
    }
    
    // -- Data Field Methods --------------------------------------------------
    
    /**
     * False by default.
     * @see dartie.prefuse.data.tuple.TupleSet#isAddColumnSupported()
     */
    public boolean isAddColumnSupported() {
        return false;
    }

    /**
     * @see dartie.prefuse.data.tuple.TupleSet#addColumns(prefuse.data.Schema)
     */
    public void addColumns(Schema schema) {
        if ( isAddColumnSupported() ) {
            for ( int i=0; i<schema.getColumnCount(); ++i ) {
                try {
                    addColumn(schema.getColumnName(i), 
                              schema.getColumnType(i),
                              schema.getDefault(i));
                } catch ( IllegalArgumentException iae ) {}
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * Unsupported by default.
     * @see dartie.prefuse.data.tuple.TupleSet#addColumn(java.lang.String, java.lang.Class, java.lang.Object)
     */
    public void addColumn(String name, Class type, Object defaultValue) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported by default.
     * @see dartie.prefuse.data.tuple.TupleSet#addColumn(java.lang.String, java.lang.Class)
     */
    public void addColumn(String name, Class type) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported by default.
     * @see dartie.prefuse.data.tuple.TupleSet#addColumn(java.lang.String, prefuse.data.expression.Expression)
     */
    public void addColumn(String name, Expression expr) {
        throw new UnsupportedOperationException();
    }

    /**
     * Unsupported by default.
     * @see dartie.prefuse.data.tuple.TupleSet#addColumn(java.lang.String, java.lang.String)
     */
    public void addColumn(String name, String expr) {
        throw new UnsupportedOperationException();
    }
    
    // -- Client Properties ---------------------------------------------------
    
    private HashMap m_props;
    private SwingPropertyChangeSupport m_propSupport;
    
    /**
     * @see dartie.prefuse.data.tuple.TupleSet#addPropertyChangeListener(java.beans.PropertyChangeListener)
     */
    public void addPropertyChangeListener(PropertyChangeListener lstnr) {
        if ( lstnr == null ) return;
        if ( m_propSupport == null )
            m_propSupport = new SwingPropertyChangeSupport(this);
        m_propSupport.addPropertyChangeListener(lstnr);
    }
    
    /**
     * @see dartie.prefuse.data.tuple.TupleSet#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
     */
    public void addPropertyChangeListener(String key, 
                                          PropertyChangeListener lstnr)
    {
        if ( lstnr == null ) return;
        if ( m_propSupport == null )
            m_propSupport = new SwingPropertyChangeSupport(this);
        m_propSupport.addPropertyChangeListener(key, lstnr);
    }
    
    /**
     * @see dartie.prefuse.data.tuple.TupleSet#removePropertyChangeListener(java.beans.PropertyChangeListener)
     */
    public void removePropertyChangeListener(PropertyChangeListener lstnr) {
        if ( lstnr == null ) return;
        if ( m_propSupport == null ) return;
        m_propSupport.removePropertyChangeListener(lstnr);
    }
    
    /**
     * @see dartie.prefuse.data.tuple.TupleSet#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
     */
    public void removePropertyChangeListener(String key,
                                             PropertyChangeListener lstnr)
    {
        if ( lstnr == null ) return;
        if ( m_propSupport == null ) return;
        m_propSupport.removePropertyChangeListener(key, lstnr);
    }
    
    /**
     * @see dartie.prefuse.data.tuple.TupleSet#putClientProperty(java.lang.String, java.lang.Object)
     */
    public void putClientProperty(String key, Object value) {
        Object prev = null;
        if ( m_props == null && value == null ) {
            // nothing to do
            return;
        } else if ( value == null ) {
            prev = m_props.remove(key);
        } else {
            if ( m_props == null )
                m_props = new HashMap(2);
            prev = m_props.put(key, value);
        }
        if ( m_propSupport != null )
            m_propSupport.firePropertyChange(key, prev, value);
    }
    
    /**
     * @see dartie.prefuse.data.tuple.TupleSet#getClientProperty(java.lang.String)
     */
    public Object getClientProperty(String key) {
        return ( m_props == null ? null : m_props.get(key) );
    }
    
} // end of class AbstractTupleSet
