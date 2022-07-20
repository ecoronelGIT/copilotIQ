package com.copilotiq;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <h3>PropertiesParser</h3>
 *
 * Resolves key substitution on a properties set; in other words, given:
 * <pre>FOO = foo
 * BAR = bar
 * baz = ${FOO} is ${BAR}
 * </pre>
 *
 * the following code will satisfy the assertion:
 * <pre> Properties props = Properties.load('my.properties');
 *  PropertiesParser parser = new PropertiesParser(props);
 *  parser.parse();
 *  assert("foo is bar".equals(props.getProperty("baz"));
 * </pre>
 */
public class PropertiesParser {


    /** A RegEx pattern for a key subst: ${key} */


    private final String PATTERN = "${.*}";
    private final Properties props;


    public PropertiesParser(Properties props) {
        this.props = props;
    }


    public void parse() {
        for (String key : props.stringPropertyNames()) {
            String value = props.getProperty(key);
            if (hasSubstKey(value))
                value = applyPattern(value);
        }
    }


    /**
     * Executes string substitution for the {@code value}
     *
     * @param value
     * @return the fully substituted value, if possible
     */
    protected String applyPattern(String value) {
        Pattern keyPattern = Pattern.compile(PATTERN);
        Matcher m = keyPattern.matcher(value);
        StringBuilder sb = new StringBuilder();
        if (m.find()) {
            int curPos = 0;
            sb.append(value.substring(curPos, m.start())).append(extractKeyValue(m.group()));
            sb.append(value.substring(m.end()));
        }
        return sb.toString();
    }


    /** Replaces the subst key with its value, if available in {@code props} */
    // TODO: improve javadoc
    private String extractKeyValue(String key) {
        //TODO("Not yet implemented");
        return null;
    }


    private boolean hasSubstKey(String value) {
        //TODO("Not yet implemented");
        return false;
    }
}
