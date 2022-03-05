/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import CoupomFormComponent from '@/entities/coupom-form/coupom-form.vue';
import CoupomFormClass from '@/entities/coupom-form/coupom-form.component';
import CoupomFormService from '@/entities/coupom-form/coupom-form.service';

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
  describe('CoupomForm Management Component', () => {
    let wrapper: Wrapper<CoupomFormClass>;
    let comp: CoupomFormClass;
    let coupomFormServiceStub: SinonStubbedInstance<CoupomFormService>;

    beforeEach(() => {
      coupomFormServiceStub = sinon.createStubInstance<CoupomFormService>(CoupomFormService);
      coupomFormServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CoupomFormClass>(CoupomFormComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          coupomFormService: () => coupomFormServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      coupomFormServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCoupomForms();
      await comp.$nextTick();

      // THEN
      expect(coupomFormServiceStub.retrieve.called).toBeTruthy();
      expect(comp.coupomForms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      coupomFormServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeCoupomForm();
      await comp.$nextTick();

      // THEN
      expect(coupomFormServiceStub.delete.called).toBeTruthy();
      expect(coupomFormServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
