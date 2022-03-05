/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import WarrantyComponent from '@/entities/warranty/warranty.vue';
import WarrantyClass from '@/entities/warranty/warranty.component';
import WarrantyService from '@/entities/warranty/warranty.service';

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
  describe('Warranty Management Component', () => {
    let wrapper: Wrapper<WarrantyClass>;
    let comp: WarrantyClass;
    let warrantyServiceStub: SinonStubbedInstance<WarrantyService>;

    beforeEach(() => {
      warrantyServiceStub = sinon.createStubInstance<WarrantyService>(WarrantyService);
      warrantyServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<WarrantyClass>(WarrantyComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          warrantyService: () => warrantyServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      warrantyServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllWarrantys();
      await comp.$nextTick();

      // THEN
      expect(warrantyServiceStub.retrieve.called).toBeTruthy();
      expect(comp.warranties[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      warrantyServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeWarranty();
      await comp.$nextTick();

      // THEN
      expect(warrantyServiceStub.delete.called).toBeTruthy();
      expect(warrantyServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
