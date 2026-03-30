package com.demo.service;

import java.util.*;

/**
 * Simulates existing dictionary service.
 * Contract status uses this. Any new status fields should check here first.
 *
 * QUIRK: dict keys are strings, not ints. This is a legacy decision.
 * The API returns dict entries with string keys even though DB stores int values.
 * Callers must handle the string-to-int conversion themselves.
 */
public class DictService {

    private static final Map<String, Map<String, String>> DICTS = new HashMap<>();

    static {
        Map<String, String> contractStatus = new LinkedHashMap<>();
        contractStatus.put("1", "草稿");
        contractStatus.put("2", "生效");
        contractStatus.put("3", "已完成");
        contractStatus.put("4", "已取消");
        DICTS.put("contract_status", contractStatus);

        Map<String, String> projectStatus = new LinkedHashMap<>();
        projectStatus.put("1", "规划中");
        projectStatus.put("2", "进行中");
        projectStatus.put("3", "已完成");
        projectStatus.put("4", "已暂停");
        projectStatus.put("5", "已取消");
        DICTS.put("project_status", projectStatus);
    }

    public Map<String, String> getDictByKey(String dictKey) {
        return DICTS.getOrDefault(dictKey, Collections.emptyMap());
    }

    public String getDictLabel(String dictKey, String value) {
        return DICTS.getOrDefault(dictKey, Collections.emptyMap()).getOrDefault(value, "未知");
    }
}
