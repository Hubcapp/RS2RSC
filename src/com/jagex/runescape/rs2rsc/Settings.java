package com.jagex.runescape.rs2rsc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
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
    private static BigInteger serverModulus = new BigInteger("7112866275597968156550007489163685737528267584779959617759901583041864787078477876689003422509099353805015177703670715380710894892460637136582066351659813");
    private static BigInteger serverExponent = new BigInteger("65537");
    private static String serverIP = "game.openrsc.com";
    private static int serverPort = 43596;
    private static String rememberUsername = "<disabled>";
    private static String rememberPassword = "<disabled>";
    private static boolean enterToLogin = false;
    private static boolean localAnimations = true;

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
        props.setProperty("ServerModulus", serverModulus.toString());
        props.setProperty("ServerExponent", serverExponent.toString());
        props.setProperty("ServerIP", serverIP);
        props.setProperty("ServerPort", Integer.toString(serverPort));
        props.setProperty("RememberUsername", rememberUsername);
        props.setProperty("RememberPassword", rememberPassword);
        props.setProperty("EnterToLogin", Boolean.toString(enterToLogin));
        props.setProperty("LocalAnimations", Boolean.toString(localAnimations));

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
        serverExponent = getPropBigInteger(props, "ServerExponent", serverExponent);
        serverModulus = getPropBigInteger(props, "ServerModulus", serverModulus);
        serverIP = getPropString(props, "ServerIP", serverIP);
        serverPort = getPropInt(props, "ServerPort", serverPort);
        rememberUsername = getPropString(props, "RememberUsername", rememberUsername);
        rememberPassword = getPropString(props, "RememberPassword", rememberPassword);
        enterToLogin = getPropBoolean(props, "EnterToLogin", enterToLogin);
        localAnimations = getPropBoolean(props, "LocalAnimations", localAnimations);

        sanitizeConfigValues();
    }

    public static BigInteger getServerExponent()
    {
        return serverExponent;
    }

    public static BigInteger getServerModulus()
    {
        return serverModulus;
    }

    public static boolean getRememberPassword()
    {
        return !rememberPassword.equals("<disabled>");
    }

    public static boolean getRememberUsername()
    {
        return !rememberUsername.equals("<disabled>");
    }

    public static String getRememberedPassword()
    {
        if (!getRememberPassword())
            return "";
        return rememberPassword;
    }

    public static String getRememberedUsername()
    {
        if (!getRememberUsername())
            return "";
        return rememberUsername;
    }

    public static String getServerIP()
    {
        return serverIP;
    }

    public static int getServerPort()
    {
        return serverPort;
    }

    public static boolean getSplitPrivateChat()
    {
        return splitPrivateChat;
    }

    public static boolean getDebug()
    {
        return debug;
    }

    public static boolean getEnterToLogin()
    {
        return enterToLogin;
    }

    public static boolean getLocalAnimations()
    {
        return localAnimations;
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

    public static void setRememberUsername(String val)
    {
        if (val == null)
            val = "<disabled>";
        rememberUsername = val;
        updateConfigChange();
    }

    public static void setRememberPassword(String val)
    {
        if (val == null)
            val = "<disabled>";
        rememberPassword = val;
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

    private static BigInteger getPropBigInteger(Properties props, String key, BigInteger defaultProp) {
        String value = props.getProperty(key);
        if (value == null)
            return defaultProp;

        try {
            return new BigInteger(value);
        } catch (Exception e) {
            return defaultProp;
        }
    }

    private static String getPropString(Properties props, String key, String defaultProp) {
        String value = props.getProperty(key);
        if (value == null)
            return defaultProp;

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
