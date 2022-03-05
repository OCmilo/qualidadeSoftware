/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import CoupomComponent from '@/entities/coupom/coupom.vue';
import CoupomClass from '@/entities/coupom/coupom.component';
import CoupomService from '@/entities/coupom/coupom.service';

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
  describe('Coupom Management Component', () => {
    let wrapper: Wrapper<CoupomClass>;
    let comp: CoupomClass;
    let coupomServiceStub: SinonStubbedInstance<CoupomService>;

    beforeEach(() => {
      coupomServiceStub = sinon.createStubInstance<CoupomService>(CoupomService);
      coupomServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CoupomClass>(CoupomComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          coupomService: () => coupomServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      coupomServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCoupoms();
      await comp.$nextTick();

      // THEN
      expect(coupomServiceStub.retrieve.called).toBeTruthy();
      expect(comp.coupoms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      coupomServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeCoupom();
      await comp.$nextTick();

      // THEN
      expect(coupomServiceStub.delete.called).toBeTruthy();
      expect(coupomServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
