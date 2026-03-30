/**
 * Existing business module routes.
 * New business pages should be added here following the same pattern.
 *
 * QUIRK: Route meta.title is used for breadcrumb and tab name.
 * QUIRK: Route meta.permission is the permission key checked by the router guard.
 */
export default [
  {
    path: '/contract',
    name: 'Contract',
    component: () => import('@/views/contract/index.vue'),
    meta: {
      title: '合同管理',
      permission: 'business:contract:view'
    }
  },
  {
    path: '/customer',
    name: 'Customer',
    component: () => import('@/views/customer/index.vue'),
    meta: {
      title: '客户管理',
      permission: 'business:customer:view'
    }
  }
];
