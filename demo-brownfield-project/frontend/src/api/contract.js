import { request, downloadFile } from '@/utils/request';

export function getContractList(params) {
  return request({ url: '/contract/list', method: 'GET', params });
}

export function createContract(data) {
  return request({ url: '/contract', method: 'POST', data });
}

export function updateContract(id, data) {
  return request({ url: `/contract/${id}`, method: 'PUT', data });
}

export function exportContracts(params) {
  // QUIRK: export uses same filter params as list, minus pageNum/pageSize
  const { pageNum, pageSize, ...exportParams } = params;
  return downloadFile('/contract/export', exportParams, `contract_${Date.now()}.xlsx`);
}
