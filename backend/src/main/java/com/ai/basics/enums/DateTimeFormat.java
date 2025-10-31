/**
 * Enum call for DateTime formats
 */
package com.ai.basics.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;   

@AllArgsConstructor
@Getter
public enum DateTimeFormat {

    // Standard date time format
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),

    // Standard with timezone
    YYYY_MM_DD_HH_MM_SS_ZZZ("yyyy-MM-dd HH:mm:ss ZZZ");


    private final String pattern;

}