package com.demo.service;

import com.demo.common.PageResult;
import com.demo.model.Contract;

public interface ContractService {
    PageResult<Contract> queryPage(String contractName, String contractCode,
                                    Integer status, int pageNum, int pageSize);
    void create(Contract contract);
    void update(Contract contract);
}
