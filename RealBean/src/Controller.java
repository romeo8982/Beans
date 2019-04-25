import java.beans.*;
public class Controller implements VetoableChangeListener{

	@Override
	public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
		Integer newVal = (Integer) e.getNewValue();
		int val = newVal.intValue();
		if(val == 37)
			throw new PropertyVetoException("Niedopuszczalna zmiana wartości", e);
	}
}
