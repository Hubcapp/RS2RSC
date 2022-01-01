package com.jagex.runescape.rs2rsc;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;
import java.util.Properties;

public class Settings
{
    public static class Dir {
        public static String JAR;
        public static String CACHE;
        public static String RSC_CACHE;
    }

    private static boolean debug = false;
    private static int brightness = 1;
    private static boolean splitPrivateChat = false;

    private static String baseDir;
    private static File settingsFile;

    public static void Init()
    {
        String protocol = Settings.class.getResource("").getProtocol();

        // Resolve directories
        Dir.JAR = ".";
        if (Objects.equals(protocol, "jar")) {
            try {
                Dir.JAR = Settings.class.getProtectionDomain().getCodeSource().getLocation().getFile();
                System.out.println(Dir.JAR);
                Dir.JAR = Settings.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                int indexFileSep1 = Dir.JAR.lastIndexOf('/');
                int indexFileSep2 = Dir.JAR.lastIndexOf('\\');
                int index = (indexFileSep1 > indexFileSep2) ? indexFileSep1 : indexFileSep2;
                if (index != -1)
                    Dir.JAR = Dir.JAR.substring(0, index);
            } catch (Exception e) {}
        }

        Dir.CACHE = Dir.JAR + "/cache";
        Dir.RSC_CACHE = Dir.JAR + "/rsc_cache";

        System.out.println(Dir.JAR);

        // Get settings file
        settingsFile = new File(Dir.JAR + "/settings.ini");

        Load();
    }

    public static void Save()
    {
        Properties props = new Properties();
        props.setProperty("Debug", Boolean.toString(debug));
        props.setProperty("Brightness", Integer.toString(brightness));
        props.setProperty("SplitPrivateChat", Boolean.toString(splitPrivateChat));

        try {
            FileOutputStream out = new FileOutputStream(settingsFile);
            props.store(out, "---317refactor config---");
            out.close();
        } catch (Exception e) {}
    }

    public static void Load()
    {
        if (!settingsFile.exists())
            Save();

        // Load settings
        Properties props = new Properties();
        try {
            FileInputStream in = new FileInputStream(settingsFile);
            props.load(in);
            in.close();
        } catch (Exception e) {
            return;
        }

        debug = getPropBoolean(props, "Debug", debug);
        brightness = getPropInt(props, "Brightness", brightness);
        splitPrivateChat = getPropBoolean(props, "SplitPrivateChat", splitPrivateChat);

        sanitizeConfigValues();
    }

    public static boolean getSplitPrivateChat()
    {
        return splitPrivateChat;
    }

    public static boolean getDebug()
    {
        return debug;
    }

    public static int getBrightness()
    {
        return brightness;
    }

    public static void setSplitPrivateChat(boolean enable)
    {
        splitPrivateChat = enable;
        updateConfigChange();
    }

    public static void setDebug(boolean enable)
    {
        debug = enable;
        updateConfigChange();
    }

    public static void setBrightness(int val)
    {
        brightness = val;
        updateConfigChange();
    }

    private static int clampValue(int value, int min, int max)
    {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    private static int getPropInt(Properties props, String key, int defaultProp) {
        String value = props.getProperty(key);
        if (value == null)
            return defaultProp;

        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultProp;
        }
    }

    private static boolean getPropBoolean(Properties props, String key, boolean defaultProp) {
        String value = props.getProperty(key);
        if (value == null)
            return defaultProp;

        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return defaultProp;
        }
    }

    private static void sanitizeConfigValues()
    {
        brightness = clampValue(brightness, 0, 3);
    }

    private static void updateConfigChange()
    {
        sanitizeConfigValues();
        Save();
    }
}
