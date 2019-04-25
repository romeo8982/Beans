import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class BeanInfo extends SimpleBeanInfo  {

	public PropertyDescriptor[] getPropertyDescriptors() {
        try {

            return new PropertyDescriptor[] {
                    new PropertyDescriptor("text", Bean.class, "getBeanString", "setBeanString"),
                    new PropertyDescriptor("beanValue", Bean.class, "getBeanValue", "setBeanValue"),
                    new PropertyDescriptor("beanColor", Bean.class, "getBeanColor", "setBeanColor")
            };
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
}
}
