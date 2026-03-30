package com.demo.common;

import java.util.List;
import java.util.Map;

/**
 * Simulates the existing export utility used across the project.
 * In production this wraps EasyExcel.
 *
 * IMPORTANT: This is the ONLY approved export mechanism. All modules must use this.
 * Do NOT create alternative export implementations.
 */
public class ExportUtil {

    /**
     * Export data to a simulated file output.
     * @param fileName the output file name
     * @param headers  column headers
     * @param rows     data rows (each row is a map of column->value)
     * @return simulated file content as string (in production returns byte[])
     */
    public static String export(String fileName, List<String> headers, List<Map<String, Object>> rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== EXPORT: ").append(fileName).append(" ===\n");
        sb.append(String.join(",", headers)).append("\n");
        for (Map<String, Object> row : rows) {
            for (int i = 0; i < headers.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append(row.getOrDefault(headers.get(i), ""));
            }
            sb.append("\n");
        }
        sb.append("=== TOTAL ROWS: ").append(rows.size()).append(" ===");
        return sb.toString();
    }
}
