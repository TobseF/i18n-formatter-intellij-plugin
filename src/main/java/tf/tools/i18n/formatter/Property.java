package tf.tools.i18n.formatter;

/**
 * @author Tobias Fritz (tfr@itscope.de)
 */
public class Property implements Comparable<Property>{
   private final String key;
   private final String value;

    public Property(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Property property) {
        return key.toLowerCase().compareTo(property.key.toLowerCase());
    }

    @Override
    public String toString() {
        return key +" = "+value;
    }

    public String getKey() {
        return key;
    }

    public int getKeyLength(){
        return key.length();
    }

    public String getValue() {
        return value;
    }

    public String getGroup(){
        int firstUnderscore = key.indexOf("_");
        if(firstUnderscore == -1){
            return key;
        }
        return key.substring(0, firstUnderscore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Property property = (Property) o;

        if (key != null ? !key.equals(property.key) : property.key != null) { return false; }
        return value != null ? value.equals(property.value) : property.value == null;

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
