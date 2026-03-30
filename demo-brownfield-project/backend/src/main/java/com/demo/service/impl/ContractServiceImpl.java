package com.demo.service.impl;

import com.demo.common.PageResult;
import com.demo.common.ServiceException;
import com.demo.common.TenantContext;
import com.demo.model.Contract;
import com.demo.service.ContractService;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ContractServiceImpl implements ContractService {

    private final Map<Long, Contract> store = new LinkedHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    @Override
    public PageResult<Contract> queryPage(String contractName, String contractCode,
                                           Integer status, int pageNum, int pageSize) {
        Long tenantId = TenantContext.getTenantId();
        if (tenantId == null) {
            throw new ServiceException(403, "租户上下文缺失");
        }

        List<Contract> filtered = store.values().stream()
            .filter(c -> c.getTenantId().equals(tenantId))
            .filter(c -> c.getDeleted() == 0)
            .filter(c -> contractName == null || c.getContractName().contains(contractName))
            .filter(c -> contractCode == null || c.getContractCode().equals(contractCode))
            .filter(c -> status == null || c.getStatus().equals(status))
            .collect(Collectors.toList());

        long total = filtered.size();
        int from = (pageNum - 1) * pageSize;
        int to = Math.min(from + pageSize, filtered.size());
        List<Contract> page = from < filtered.size() ? filtered.subList(from, to) : Collections.emptyList();

        return new PageResult<>(page, total, pageNum, pageSize);
    }

    @Override
    public void create(Contract contract) {
        Long tenantId = TenantContext.getTenantId();
        if (tenantId == null) {
            throw new ServiceException(403, "租户上下文缺失");
        }
        if (contract.getContractName() == null || contract.getContractName().isBlank()) {
            throw new ServiceException(400, "合同名称不能为空");
        }
        contract.setId(idGen.getAndIncrement());
        contract.setTenantId(tenantId);
        contract.setCreateTime(LocalDateTime.now());
        contract.setUpdateTime(LocalDateTime.now());
        contract.setDeleted(0);
        store.put(contract.getId(), contract);
    }

    @Override
    public void update(Contract contract) {
        Contract existing = store.get(contract.getId());
        if (existing == null) {
            throw new ServiceException(404, "合同不存在");
        }
        Long tenantId = TenantContext.getTenantId();
        if (!existing.getTenantId().equals(tenantId)) {
            throw new ServiceException(403, "无权操作其他租户数据");
        }
        existing.setContractName(contract.getContractName());
        existing.setContractCode(contract.getContractCode());
        existing.setStatus(contract.getStatus());
        existing.setCustomerId(contract.getCustomerId());
        existing.setUpdateTime(LocalDateTime.now());
    }
}
