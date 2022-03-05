/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import PurchaseComponent from '@/entities/purchase/purchase.vue';
import PurchaseClass from '@/entities/purchase/purchase.component';
import PurchaseService from '@/entities/purchase/purchase.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Purchase Management Component', () => {
    let wrapper: Wrapper<PurchaseClass>;
    let comp: PurchaseClass;
    let purchaseServiceStub: SinonStubbedInstance<PurchaseService>;

    beforeEach(() => {
      purchaseServiceStub = sinon.createStubInstance<PurchaseService>(PurchaseService);
      purchaseServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<PurchaseClass>(PurchaseComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          purchaseService: () => purchaseServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      purchaseServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllPurchases();
      await comp.$nextTick();

      // THEN
      expect(purchaseServiceStub.retrieve.called).toBeTruthy();
      expect(comp.purchases[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
