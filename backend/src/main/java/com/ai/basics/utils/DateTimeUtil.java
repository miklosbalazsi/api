/**
 * DateTimeUtil Component to convert and format date and time values.
 */
package com.ai.basics.utils;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Convert unix milliseconds to formatted date time string.
     * @param unixMillis unix milliseconds
     * @return formatted date time string
     */
    public static String formatUnixMillis(long unixMillis) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(unixMillis), ZoneId.systemDefault());
        return dateTime.format(FORMATTER);
    }

    /**
     * Format uptime in seconds to a human-readable string.
     * @param uptimeSeconds uptime in seconds
     * @return formatted uptime string
     */
    public static String formatUptime(long uptimeSeconds) {
        long hours = uptimeSeconds / 3600;
        long minutes = (uptimeSeconds % 3600) / 60;
        long seconds = uptimeSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
}