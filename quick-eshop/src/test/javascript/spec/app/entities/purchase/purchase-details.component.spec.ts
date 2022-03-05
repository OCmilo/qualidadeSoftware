/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import PurchaseDetailComponent from '@/entities/purchase/purchase-details.vue';
import PurchaseClass from '@/entities/purchase/purchase-details.component';
import PurchaseService from '@/entities/purchase/purchase.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Purchase Management Detail Component', () => {
    let wrapper: Wrapper<PurchaseClass>;
    let comp: PurchaseClass;
    let purchaseServiceStub: SinonStubbedInstance<PurchaseService>;

    beforeEach(() => {
      purchaseServiceStub = sinon.createStubInstance<PurchaseService>(PurchaseService);

      wrapper = shallowMount<PurchaseClass>(PurchaseDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { purchaseService: () => purchaseServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundPurchase = { id: 123 };
        purchaseServiceStub.find.resolves(foundPurchase);

        // WHEN
        comp.retrievePurchase(123);
        await comp.$nextTick();

        // THEN
        expect(comp.purchase).toBe(foundPurchase);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundPurchase = { id: 123 };
        purchaseServiceStub.find.resolves(foundPurchase);

        // WHEN
        comp.beforeRouteEnter({ params: { purchaseId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.purchase).toBe(foundPurchase);
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
