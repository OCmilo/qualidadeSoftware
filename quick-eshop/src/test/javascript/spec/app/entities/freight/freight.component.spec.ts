/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import FreightComponent from '@/entities/freight/freight.vue';
import FreightClass from '@/entities/freight/freight.component';
import FreightService from '@/entities/freight/freight.service';

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
  describe('Freight Management Component', () => {
    let wrapper: Wrapper<FreightClass>;
    let comp: FreightClass;
    let freightServiceStub: SinonStubbedInstance<FreightService>;

    beforeEach(() => {
      freightServiceStub = sinon.createStubInstance<FreightService>(FreightService);
      freightServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<FreightClass>(FreightComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          freightService: () => freightServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      freightServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllFreights();
      await comp.$nextTick();

      // THEN
      expect(freightServiceStub.retrieve.called).toBeTruthy();
      expect(comp.freights[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      freightServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeFreight();
      await comp.$nextTick();

      // THEN
      expect(freightServiceStub.delete.called).toBeTruthy();
      expect(freightServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
