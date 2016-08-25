package tf.tools.i18n.formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author Tobias Fritz (tfr@itscope.de)
 */
public class MessagePropertyFormatter {

    private final List<String> lines;
    private final List<String> sorted;

    public List<String> sort() {
        Map<String, List<Property>> groups = lines.stream().filter(this::isProperty).map(this::parseProperty).collect(groupingBy(Property::getGroup));

        groups.keySet().stream().sorted(naturalOrder()).map(groups::get).forEach(this::writeGroup);
        sorted.remove(sorted.size() - 1);
        return sorted;
    }

    public MessagePropertyFormatter(List<String> lines)  {
        this.lines = lines;
        sorted = new ArrayList<>(lines.size());
    }

    public boolean isProperty(String line) {
        return line.trim().length() > 3 && line.indexOf('=')> 0 ;
    }

    public void writeGroup(List<Property> properties) {
        int maxKeyLength = properties.stream().sorted(comparing(Property::getKeyLength).reversed()).findFirst().get().getKeyLength();
        properties.stream().sorted().forEach(property -> writeProperty(property, maxKeyLength));
        sorted.add("");
    }

    public void writeProperty(Property property, int tabLenght) {
        String key = String.format("%-" + tabLenght + "s", property.getKey());
        sorted.add(key + " = " + property.getValue());
    }

    public Property parseProperty(String line) {
        String[] split = line.split("=",2);
        if(split.length == 2) {
            return new Property(split[0].trim(), split[1].trim());
        }else{
            return null;
        }
    }

}
