package com.xiaomi.parts.kcal;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.lang.Math;
import java.lang.ProcessBuilder;

public interface Utils {
    String PREF_ENABLED = "kcal_enabled";
    String PREF_SETONBOOT = "set_on_boot";
    String PREF_MINIMUM = "color_minimum";
    String PREF_RED = "color_red";
    String PREF_GREEN = "color_green";
    String PREF_BLUE = "color_blue";
    String PREF_SATURATION = "saturation";
    String PREF_VALUE = "value";
    String PREF_CONTRAST = "contrast";
    String PREF_HUE = "hue";
    String PREF_GRAYSCALE = "grayscale";

    boolean SETONBOOT_DEFAULT = false;
    double SCREEN_BURNIN_AMOUNT = 0.9;
    int MINIMUM_DEFAULT = 35;
    int RED_DEFAULT = getMaxColorValue();
    int GREEN_DEFAULT = getMaxColorValue();
    int BLUE_DEFAULT = getMaxColorValue();
    int SATURATION_DEFAULT = 35;
    int SATURATION_OFFSET = 225;
    int VALUE_DEFAULT = 127;
    int VALUE_OFFSET = 128;
    int CONTRAST_DEFAULT = 127;
    int CONTRAST_OFFSET = 128;
    int HUE_DEFAULT = 0;
    boolean GRAYSCALE_DEFAULT = false;

    String KCAL_ENABLE = "/sys/devices/platform/kcal_ctrl.0/kcal_enable";
    String KCAL_CONT = "/sys/devices/platform/kcal_ctrl.0/kcal_cont";
    String KCAL_HUE = "/sys/devices/platform/kcal_ctrl.0/kcal_hue";
    String KCAL_MIN = "/sys/devices/platform/kcal_ctrl.0/kcal_min";
    String KCAL_RGB = "/sys/devices/platform/kcal_ctrl.0/kcal";
    String KCAL_SAT = "/sys/devices/platform/kcal_ctrl.0/kcal_sat";
    String KCAL_VAL = "/sys/devices/platform/kcal_ctrl.0/kcal_val";

    static boolean isBurnedInPanel() {
        String panel = getPanelName();
        if (panel != null) {
            if (panel.contains("tianma")) {
                return true;
            }
        }
        return false;
    }

    static String getPanelName() {
        StringBuilder cmdReturn = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("sh","-c","(cat /sys/class/graphics/fb0/msm_fb_panel_info | grep panel_name)");
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            int c;
            while ((c = inputStream.read()) != -1) {
                cmdReturn.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cmdReturn.toString();
    }

    public static int getMaxColorValue() {
        return getFinalColorValue(255);
    }

    public static int getFinalColorValue(int value) {
        int safeVal = value;
        if (isBurnedInPanel()) {
            safeVal = (int) Math.round(value * SCREEN_BURNIN_AMOUNT);
        }
        return safeVal;
    }
}
