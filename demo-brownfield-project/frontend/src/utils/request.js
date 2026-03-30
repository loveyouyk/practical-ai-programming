/**
 * Existing axios-like request wrapper.
 * All API calls MUST go through this.
 * It adds tenant header, handles 401/403, and wraps errors.
 *
 * QUIRK: The error handler shows a global toast via window.__toast
 * rather than returning error objects. This is legacy behavior.
 */
const BASE_URL = '/api';

export function request(options) {
  const { url, method = 'GET', params, data } = options;

  // Simulated request
  console.log(`[REQUEST] ${method} ${BASE_URL}${url}`, { params, data });

  return new Promise((resolve, reject) => {
    // In production: axios instance with interceptors
    resolve({ code: 200, message: 'success', data: null });
  });
}

export function downloadFile(url, params, fileName) {
  console.log(`[DOWNLOAD] ${BASE_URL}${url}`, { params, fileName });
  // In production: axios with responseType: 'blob'
  // QUIRK: fileName must follow pattern: {module}_{date}_{random}.xlsx
  return Promise.resolve();
}
