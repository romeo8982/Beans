import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

public class Bean extends JComponent implements Externalizable {

  private int beanValue;
  private Color beanColor;
  private String text; 
  
  protected PropertyChangeSupport propertySupporter = new PropertyChangeSupport(this);
  protected VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

  protected EventListenerList listenerList = new EventListenerList();

  public Bean() {
    beanValue = 0;
    beanColor = Color.black;
    text = "Bean ";
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(beanColor);
    g.drawString(text + beanValue, 30, 30);
  }
  
  public synchronized  void setBeanValue(int newValue) throws PropertyVetoException {
    int oldValue = beanValue;
    vetoableChangeSupport.fireVetoableChange("value", oldValue, newValue);

    beanValue = newValue;
    propertySupporter.firePropertyChange("value", new Integer(oldValue), new Integer(newValue));
  }

  public int getBeanValue() {
    return beanValue;
  }
  
  public synchronized void setBeanColor(Color newColor) {
    Color oldColor = beanColor;
    beanColor = newColor;
    
    propertySupporter.firePropertyChange("color", oldColor, newColor);
  }

  public Color getBeanColor() {
    return beanColor;
  }

  public synchronized void setBeanString(String newString) {
    text = newString;
  }

  public String getBeanString() {
    return text;
  }

  public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
    propertySupporter.addPropertyChangeListener(l);
  }

  public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
    propertySupporter.removePropertyChangeListener(l);
  }

  public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
    vetoableChangeSupport.addVetoableChangeListener(l);
  }

  public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
    vetoableChangeSupport.removeVetoableChangeListener(l);
  }

  public synchronized void addChangeListener(ChangeListener l) {
    listenerList.add(ChangeListener.class, l);
  }

  public synchronized void removeChangeListener(ChangeListener l) {
    listenerList.remove(ChangeListener.class, l);
  }


  public void writeExternal(ObjectOutput out) throws IOException {
    out.writeObject(new Font(Font.SERIF, Font.PLAIN,  40));
    out.writeObject(new Dimension(150, 100));
    out.writeInt(beanValue);
    out.writeObject(beanColor);
    out.writeObject(text);
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    try {
	  setBeanValue(in.readInt());
    } catch (PropertyVetoException pve) {
      System.out.println("Value vetoed..");
    }
    setBeanColor((Color) in.readObject());
    setBeanString((String) in.readObject());
  }
}
