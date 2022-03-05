/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import CouponUpdateComponent from '@/entities/coupon/coupon-update.vue';
import CouponClass from '@/entities/coupon/coupon-update.component';
import CouponService from '@/entities/coupon/coupon.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Coupon Management Update Component', () => {
    let wrapper: Wrapper<CouponClass>;
    let comp: CouponClass;
    let couponServiceStub: SinonStubbedInstance<CouponService>;

    beforeEach(() => {
      couponServiceStub = sinon.createStubInstance<CouponService>(CouponService);

      wrapper = shallowMount<CouponClass>(CouponUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          couponService: () => couponServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.coupon = entity;
        couponServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(couponServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.coupon = entity;
        couponServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(couponServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCoupon = { id: 123 };
        couponServiceStub.find.resolves(foundCoupon);
        couponServiceStub.retrieve.resolves([foundCoupon]);

        // WHEN
        comp.beforeRouteEnter({ params: { couponId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.coupon).toBe(foundCoupon);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
