import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const TravelPlan = () => import('@/entities/travel-plan/travel-plan.vue');
// prettier-ignore
const TravelPlanDetails = () => import('@/entities/travel-plan/travel-plan-details.vue');
// prettier-ignore
const TravelPlanProcessDetails = () => import('@/entities/travel-plan-process/travel-plan-process-details.vue');
// prettier-ignore
const TravelPlanProcessList = () => import('@/entities/travel-plan-process/travel-plan-process-list.vue');
// prettier-ignore
const TravelPlanStartFormInit = () => import('@/entities/travel-plan-process/travel-plan-start-form-init.vue');
// prettier-ignore
const Product = () => import('@/entities/product/product.vue');
// prettier-ignore
const ProductDetails = () => import('@/entities/product/product-details.vue');
// prettier-ignore
const PurchaseProcessDetails = () => import('@/entities/purchase-process/purchase-process-details.vue');
// prettier-ignore
const PurchaseProcessList = () => import('@/entities/purchase-process/purchase-process-list.vue');
// prettier-ignore
const PurchaseFormInit = () => import('@/entities/purchase-process/purchase-form-init.vue');
// prettier-ignore
const WarrantyFormInit = () => import('@/entities/purchase-process/warranty-form-init.vue');
// prettier-ignore
const FreightFormInit = () => import('@/entities/purchase-process/freight-form-init.vue');
// prettier-ignore
const Freight = () => import('@/entities/freight/freight.vue');
// prettier-ignore
const FreightUpdate = () => import('@/entities/freight/freight-update.vue');
// prettier-ignore
const FreightDetails = () => import('@/entities/freight/freight-details.vue');
// prettier-ignore
const Warranty = () => import('@/entities/warranty/warranty.vue');
// prettier-ignore
const WarrantyUpdate = () => import('@/entities/warranty/warranty-update.vue');
// prettier-ignore
const WarrantyDetails = () => import('@/entities/warranty/warranty-details.vue');
// prettier-ignore
const CoupomForm = () => import('@/entities/coupom-form/coupom-form.vue');
// prettier-ignore
const CoupomFormUpdate = () => import('@/entities/coupom-form/coupom-form-update.vue');
// prettier-ignore
const CoupomFormDetails = () => import('@/entities/coupom-form/coupom-form-details.vue');
// prettier-ignore
const Coupom = () => import('@/entities/coupom/coupom.vue');
// prettier-ignore
const CoupomUpdate = () => import('@/entities/coupom/coupom-update.vue');
// prettier-ignore
const CoupomDetails = () => import('@/entities/coupom/coupom-details.vue');
// prettier-ignore
const ProductFormInit = () => import('@/entities/purchase-process/product-form-init.vue');
// prettier-ignore
const WarrantyForm = () => import('@/entities/warranty-form/warranty-form.vue');
// prettier-ignore
const WarrantyFormUpdate = () => import('@/entities/warranty-form/warranty-form-update.vue');
// prettier-ignore
const WarrantyFormDetails = () => import('@/entities/warranty-form/warranty-form-details.vue');
// prettier-ignore
const FreightForm = () => import('@/entities/freight-form/freight-form.vue');
// prettier-ignore
const FreightFormUpdate = () => import('@/entities/freight-form/freight-form-update.vue');
// prettier-ignore
const FreightFormDetails = () => import('@/entities/freight-form/freight-form-details.vue');
// prettier-ignore
const PurchaseProcess_CoupomFormDetails = () => import('@/entities/purchase-process/coupom-form/coupom-form-details.vue');
// prettier-ignore
const PurchaseProcess_CoupomFormExecute = () => import('@/entities/purchase-process/coupom-form/coupom-form-execute.vue');
// prettier-ignore
const PurchaseProcess_ProductFormDetails = () => import('@/entities/purchase-process/product-form/product-form-details.vue');
// prettier-ignore
const PurchaseProcess_ProductFormExecute = () => import('@/entities/purchase-process/product-form/product-form-execute.vue');
// prettier-ignore
const PurchaseProcess_WarrantyFormDetails = () => import('@/entities/purchase-process/warranty-form/warranty-form-details.vue');
// prettier-ignore
const PurchaseProcess_WarrantyFormExecute = () => import('@/entities/purchase-process/warranty-form/warranty-form-execute.vue');
// prettier-ignore
const PurchaseProcess_FreightFormDetails = () => import('@/entities/purchase-process/freight-form/freight-form-details.vue');
// prettier-ignore
const PurchaseProcess_FreightFormExecute = () => import('@/entities/purchase-process/freight-form/freight-form-execute.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/travel-plan',
    name: 'TravelPlan',
    component: TravelPlan,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/travel-plan/:travelPlanId/view',
    name: 'TravelPlanView',
    component: TravelPlanDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/instance/:processInstanceId/view',
    name: 'TravelPlanProcessView',
    component: TravelPlanProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/instances',
    name: 'TravelPlanProcessList',
    component: TravelPlanProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/TravelPlanProcess/init',
    name: 'TravelPlanStartFormInit',
    component: TravelPlanStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/product',
    name: 'Product',
    component: Product,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/product/:productId/view',
    name: 'ProductView',
    component: ProductDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/instance/:processInstanceId/view',
    name: 'PurchaseProcessView',
    component: PurchaseProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/instances',
    name: 'PurchaseProcessList',
    component: PurchaseProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/init',
    name: 'PurchaseFormInit',
    component: PurchaseFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/init',
    name: 'WarrantyFormInit',
    component: WarrantyFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/init',
    name: 'FreightFormInit',
    component: FreightFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/freight',
    name: 'Freight',
    component: Freight,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/freight/new',
    name: 'FreightCreate',
    component: FreightUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/freight/:freightId/edit',
    name: 'FreightEdit',
    component: FreightUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/freight/:freightId/view',
    name: 'FreightView',
    component: FreightDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/warranty',
    name: 'Warranty',
    component: Warranty,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/warranty/new',
    name: 'WarrantyCreate',
    component: WarrantyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/warranty/:warrantyId/edit',
    name: 'WarrantyEdit',
    component: WarrantyUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/warranty/:warrantyId/view',
    name: 'WarrantyView',
    component: WarrantyDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupom-form',
    name: 'CoupomForm',
    component: CoupomForm,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupom-form/new',
    name: 'CoupomFormCreate',
    component: CoupomFormUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupom-form/:coupomFormId/edit',
    name: 'CoupomFormEdit',
    component: CoupomFormUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupom-form/:coupomFormId/view',
    name: 'CoupomFormView',
    component: CoupomFormDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupom',
    name: 'Coupom',
    component: Coupom,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupom/new',
    name: 'CoupomCreate',
    component: CoupomUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupom/:coupomId/edit',
    name: 'CoupomEdit',
    component: CoupomUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupom/:coupomId/view',
    name: 'CoupomView',
    component: CoupomDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/init',
    name: 'ProductFormInit',
    component: ProductFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/warranty-form',
    name: 'WarrantyForm',
    component: WarrantyForm,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/warranty-form/new',
    name: 'WarrantyFormCreate',
    component: WarrantyFormUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/warranty-form/:warrantyFormId/edit',
    name: 'WarrantyFormEdit',
    component: WarrantyFormUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/warranty-form/:warrantyFormId/view',
    name: 'WarrantyFormView',
    component: WarrantyFormDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/freight-form',
    name: 'FreightForm',
    component: FreightForm,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/freight-form/new',
    name: 'FreightFormCreate',
    component: FreightFormUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/freight-form/:freightFormId/edit',
    name: 'FreightFormEdit',
    component: FreightFormUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/freight-form/:freightFormId/view',
    name: 'FreightFormView',
    component: FreightFormDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/add_coupom/:taskInstanceId/view',
    name: 'PurchaseProcess_CoupomFormDetails',
    component: PurchaseProcess_CoupomFormDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/add_coupom/:taskInstanceId/execute',
    name: 'PurchaseProcess_CoupomFormExecute',
    component: PurchaseProcess_CoupomFormExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/search_product/:taskInstanceId/view',
    name: 'PurchaseProcess_ProductFormDetails',
    component: PurchaseProcess_ProductFormDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/search_product/:taskInstanceId/execute',
    name: 'PurchaseProcess_ProductFormExecute',
    component: PurchaseProcess_ProductFormExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/add_warranty/:taskInstanceId/view',
    name: 'PurchaseProcess_WarrantyFormDetails',
    component: PurchaseProcess_WarrantyFormDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/add_warranty/:taskInstanceId/execute',
    name: 'PurchaseProcess_WarrantyFormExecute',
    component: PurchaseProcess_WarrantyFormExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/choose_freight/:taskInstanceId/view',
    name: 'PurchaseProcess_FreightFormDetails',
    component: PurchaseProcess_FreightFormDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/choose_freight/:taskInstanceId/execute',
    name: 'PurchaseProcess_FreightFormExecute',
    component: PurchaseProcess_FreightFormExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
