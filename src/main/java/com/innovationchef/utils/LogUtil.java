package com.innovationchef.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public final class LogUtil {
    private static final Logger LOGGER = LogManager.getLogger(LogUtil.class);

    private LogUtil() {}

    public static void prettyPrint(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder();
            Arrays.stream(arr[i]).forEach(x -> sb.append(x).append("\t"));
            LOGGER.info(sb.toString());
        }
    }
}
