import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const PurchaseProcessDetails = () => import('@/entities/purchase-process/purchase-process-details.vue');
// prettier-ignore
const PurchaseProcessList = () => import('@/entities/purchase-process/purchase-process-list.vue');
// prettier-ignore
const Purchase = () => import('@/entities/purchase/purchase.vue');
// prettier-ignore
const PurchaseDetails = () => import('@/entities/purchase/purchase-details.vue');
// prettier-ignore
const PurchaseStartFormInit = () => import('@/entities/purchase-process/purchase-start-form-init.vue');
// prettier-ignore
const PurchaseProcess_ChooseProductDetails = () => import('@/entities/purchase-process/choose-product/choose-product-details.vue');
// prettier-ignore
const PurchaseProcess_ChooseProductExecute = () => import('@/entities/purchase-process/choose-product/choose-product-execute.vue');
// prettier-ignore
const PurchaseProcess_AddWarrantyDetails = () => import('@/entities/purchase-process/add-warranty/add-warranty-details.vue');
// prettier-ignore
const PurchaseProcess_AddWarrantyExecute = () => import('@/entities/purchase-process/add-warranty/add-warranty-execute.vue');
// prettier-ignore
const PurchaseProcess_ProceedCheckoutDetails = () => import('@/entities/purchase-process/proceed-checkout/proceed-checkout-details.vue');
// prettier-ignore
const PurchaseProcess_ProceedCheckoutExecute = () => import('@/entities/purchase-process/proceed-checkout/proceed-checkout-execute.vue');
// prettier-ignore
const PurchaseProcess_ChooseFreightDetails = () => import('@/entities/purchase-process/choose-freight/choose-freight-details.vue');
// prettier-ignore
const PurchaseProcess_ChooseFreightExecute = () => import('@/entities/purchase-process/choose-freight/choose-freight-execute.vue');
// prettier-ignore
const PurchaseProcess_AddCouponDetails = () => import('@/entities/purchase-process/add-coupon/add-coupon-details.vue');
// prettier-ignore
const PurchaseProcess_AddCouponExecute = () => import('@/entities/purchase-process/add-coupon/add-coupon-execute.vue');
// prettier-ignore
const Product = () => import('@/entities/product/product.vue');
// prettier-ignore
const ProductUpdate = () => import('@/entities/product/product-update.vue');
// prettier-ignore
const ProductDetails = () => import('@/entities/product/product-details.vue');
// prettier-ignore
const Coupon = () => import('@/entities/coupon/coupon.vue');
// prettier-ignore
const CouponUpdate = () => import('@/entities/coupon/coupon-update.vue');
// prettier-ignore
const CouponDetails = () => import('@/entities/coupon/coupon-details.vue');
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
const PurchaseProcess_AddProductsDetails = () => import('@/entities/purchase-process/add-products/add-products-details.vue');
// prettier-ignore
const PurchaseProcess_AddProductsExecute = () => import('@/entities/purchase-process/add-products/add-products-execute.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
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
    path: '/purchase',
    name: 'Purchase',
    component: Purchase,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/purchase/:purchaseId/view',
    name: 'PurchaseView',
    component: PurchaseDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/init',
    name: 'PurchaseStartFormInit',
    component: PurchaseStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/ChooseProduct/:taskInstanceId/view',
    name: 'PurchaseProcess_ChooseProductDetails',
    component: PurchaseProcess_ChooseProductDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/ChooseProduct/:taskInstanceId/execute',
    name: 'PurchaseProcess_ChooseProductExecute',
    component: PurchaseProcess_ChooseProductExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/AddWarranty/:taskInstanceId/view',
    name: 'PurchaseProcess_AddWarrantyDetails',
    component: PurchaseProcess_AddWarrantyDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/AddWarranty/:taskInstanceId/execute',
    name: 'PurchaseProcess_AddWarrantyExecute',
    component: PurchaseProcess_AddWarrantyExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/ProceedCheckout/:taskInstanceId/view',
    name: 'PurchaseProcess_ProceedCheckoutDetails',
    component: PurchaseProcess_ProceedCheckoutDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/ProceedCheckout/:taskInstanceId/execute',
    name: 'PurchaseProcess_ProceedCheckoutExecute',
    component: PurchaseProcess_ProceedCheckoutExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/ChooseFreight/:taskInstanceId/view',
    name: 'PurchaseProcess_ChooseFreightDetails',
    component: PurchaseProcess_ChooseFreightDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/ChooseFreight/:taskInstanceId/execute',
    name: 'PurchaseProcess_ChooseFreightExecute',
    component: PurchaseProcess_ChooseFreightExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/AddCoupon/:taskInstanceId/view',
    name: 'PurchaseProcess_AddCouponDetails',
    component: PurchaseProcess_AddCouponDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/AddCoupon/:taskInstanceId/execute',
    name: 'PurchaseProcess_AddCouponExecute',
    component: PurchaseProcess_AddCouponExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/product',
    name: 'Product',
    component: Product,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/product/new',
    name: 'ProductCreate',
    component: ProductUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/product/:productId/edit',
    name: 'ProductEdit',
    component: ProductUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/product/:productId/view',
    name: 'ProductView',
    component: ProductDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupon',
    name: 'Coupon',
    component: Coupon,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupon/new',
    name: 'CouponCreate',
    component: CouponUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupon/:couponId/edit',
    name: 'CouponEdit',
    component: CouponUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/coupon/:couponId/view',
    name: 'CouponView',
    component: CouponDetails,
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
    path: '/process-definition/PurchaseProcess/task/add_products/:taskInstanceId/view',
    name: 'PurchaseProcess_AddProductsDetails',
    component: PurchaseProcess_AddProductsDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/PurchaseProcess/task/add_products/:taskInstanceId/execute',
    name: 'PurchaseProcess_AddProductsExecute',
    component: PurchaseProcess_AddProductsExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
