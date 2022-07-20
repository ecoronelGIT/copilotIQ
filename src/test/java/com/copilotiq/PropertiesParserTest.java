package com.copilotiq;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesParserTest {

    @Test
    void parse() {
        Properties props = new Properties();
        PropertiesParser parser = new PropertiesParser(props);
        props.setProperty("FOO", "foo");
        props.setProperty("BAR", "bar");
        props.setProperty("baz", "${FOO} is ${BAR}");
        parser.parse();
        assertEquals("foo", props.getProperty("FOO"));
        assertEquals("bar", props.getProperty("BAR"));
        assertEquals("foo is bar", props.getProperty("baz"));
    }

    @Test
    void applyPattern() {
        Properties props = new Properties();
        PropertiesParser parser = new PropertiesParser(props);
        props.setProperty("FOO", "foo");
        props.setProperty("BAR", "bar");
        props.setProperty("baz", "${FOO} is ${BAR}");
        assertEquals("plain text", parser.applyPattern("plain text"));
        assertEquals("foo is bar", parser.applyPattern("${FOO} is ${BAR}"));
    }

    @Test
    void extractKeyValue() {
        Properties props = new Properties();
        props.setProperty("FOO", "foo");
        PropertiesParser parser = new PropertiesParser(props);
        assertEquals("foo", parser.extractKeyValue("FOO"));
        assertEquals("", parser.extractKeyValue("UNK"));
    }

    @Test
    void hasSubstKeyPresent() {
        Properties props = new Properties();
        PropertiesParser parser = new PropertiesParser(props);
        assertTrue(parser.hasSubstKey("References to ${foo}"));
        assertFalse(parser.hasSubstKey("A plain value without substitutions"));
    }
}