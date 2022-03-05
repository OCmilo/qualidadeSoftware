/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import FreightFormComponent from '@/entities/freight-form/freight-form.vue';
import FreightFormClass from '@/entities/freight-form/freight-form.component';
import FreightFormService from '@/entities/freight-form/freight-form.service';

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
  describe('FreightForm Management Component', () => {
    let wrapper: Wrapper<FreightFormClass>;
    let comp: FreightFormClass;
    let freightFormServiceStub: SinonStubbedInstance<FreightFormService>;

    beforeEach(() => {
      freightFormServiceStub = sinon.createStubInstance<FreightFormService>(FreightFormService);
      freightFormServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<FreightFormClass>(FreightFormComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          freightFormService: () => freightFormServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      freightFormServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllFreightForms();
      await comp.$nextTick();

      // THEN
      expect(freightFormServiceStub.retrieve.called).toBeTruthy();
      expect(comp.freightForms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      freightFormServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeFreightForm();
      await comp.$nextTick();

      // THEN
      expect(freightFormServiceStub.delete.called).toBeTruthy();
      expect(freightFormServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
