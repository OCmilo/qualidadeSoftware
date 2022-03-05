/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import WarrantyFormComponent from '@/entities/warranty-form/warranty-form.vue';
import WarrantyFormClass from '@/entities/warranty-form/warranty-form.component';
import WarrantyFormService from '@/entities/warranty-form/warranty-form.service';

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
  describe('WarrantyForm Management Component', () => {
    let wrapper: Wrapper<WarrantyFormClass>;
    let comp: WarrantyFormClass;
    let warrantyFormServiceStub: SinonStubbedInstance<WarrantyFormService>;

    beforeEach(() => {
      warrantyFormServiceStub = sinon.createStubInstance<WarrantyFormService>(WarrantyFormService);
      warrantyFormServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<WarrantyFormClass>(WarrantyFormComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          warrantyFormService: () => warrantyFormServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      warrantyFormServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllWarrantyForms();
      await comp.$nextTick();

      // THEN
      expect(warrantyFormServiceStub.retrieve.called).toBeTruthy();
      expect(comp.warrantyForms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      warrantyFormServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeWarrantyForm();
      await comp.$nextTick();

      // THEN
      expect(warrantyFormServiceStub.delete.called).toBeTruthy();
      expect(warrantyFormServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
