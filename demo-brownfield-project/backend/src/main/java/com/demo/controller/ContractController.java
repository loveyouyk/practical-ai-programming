package com.demo.controller;

import com.demo.common.PageResult;
import com.demo.common.Response;
import com.demo.common.ServiceException;
import com.demo.model.Contract;
import com.demo.service.ContractService;

public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    // GET /api/contract/list
    public Response<PageResult<Contract>> list(String contractName, String contractCode,
                                                Integer status, int pageNum, int pageSize) {
        try {
            PageResult<Contract> result = contractService.queryPage(
                contractName, contractCode, status, pageNum, pageSize);
            return Response.success(result);
        } catch (ServiceException e) {
            return Response.error(e.getCode(), e.getMessage());
        }
    }

    // POST /api/contract
    public Response<Void> create(Contract contract) {
        try {
            contractService.create(contract);
            return Response.success(null);
        } catch (ServiceException e) {
            return Response.error(e.getCode(), e.getMessage());
        }
    }

    // PUT /api/contract/{id}
    public Response<Void> update(Contract contract) {
        try {
            contractService.update(contract);
            return Response.success(null);
        } catch (ServiceException e) {
            return Response.error(e.getCode(), e.getMessage());
        }
    }
}
