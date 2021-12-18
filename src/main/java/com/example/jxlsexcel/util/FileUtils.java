package com.example.jxlsexcel.util;

public class FileUtils {
    private FileUtils() {}

    public static String getExcelFileName(String fileName) {
        return getFileName(fileName, Extenstion.XLSX.name().toLowerCase());
    }

    private static String getFileName(String fileName, String extension) {
        return String.format("attachment; filename=%s.%s", fileName, extension);
    }

    private enum Extenstion {
        XLSX;
    }
}
