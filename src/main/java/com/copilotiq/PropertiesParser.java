package com.copilotiq;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <h3>PropertiesParser</h3>
 * <p>
 * Resolves key substitution on a properties set; in other words, given:
 * <pre>FOO = foo
 * BAR = bar
 * baz = ${FOO} is ${BAR}
 * </pre>
 * </p>
 * the following code will satisfy the assertion:
 * <pre> Properties props = Properties.load('my.properties');
 *  PropertiesParser parser = new PropertiesParser(props);
 *  parser.parse();
 *  assert("foo is bar".equals(props.getProperty("baz"));
 * </pre>
 */
public class PropertiesParser {


    /**
     * A RegEx pattern for a key subst: ${key}. Using <code>(.*)</code> allows to extract the value directly
     */
    private final String PATTERN = "\\$\\{([^$]+)}";
    private final Properties props;


    public PropertiesParser(Properties props) {
        this.props = props;
    }


    public void parse() {
        for (String key : props.stringPropertyNames()) {
            String value = props.getProperty(key);
            if (hasSubstKey(value)) {
                value = applyPattern(value);
                props.setProperty(key, value);
            }
        }
    }


    /**
     * Executes string substitution for the {@code value}
     *
     * @param value string to evaluate
     * @return the fully substituted value, if possible
     */
    protected String applyPattern(String value) {
        Pattern keyPattern = Pattern.compile(PATTERN);
        Matcher m = keyPattern.matcher(value);
        StringBuilder sb = new StringBuilder();
        int curPos = 0;
        while (m.find()) {
            sb.append(value, curPos, m.start()).append(extractKeyValue(m.group(1)));
            curPos = m.end();
        }
        sb.append(value.substring(curPos));
        return sb.toString();
    }

    /**
     * Replaces the subst key with its value, if available in {@code props}
     *
     * @param key searched subst key
     * @return the value if defined or empty string
     */
    final protected String extractKeyValue(String key) {
        String value = "";
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }


    /**
     * Confirms the value contains a reference to a substitution key
     *
     * @param value validated value
     * @return whether the value contains a reference in the defined format <code>${key}</code>
     */
    final protected boolean hasSubstKey(String value) {
        Pattern keyPattern = Pattern.compile(PATTERN);
        Matcher m = keyPattern.matcher(value);
        return m.find();
    }
}
