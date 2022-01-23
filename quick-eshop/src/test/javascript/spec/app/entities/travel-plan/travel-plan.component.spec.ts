/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import TravelPlanComponent from '@/entities/travel-plan/travel-plan.vue';
import TravelPlanClass from '@/entities/travel-plan/travel-plan.component';
import TravelPlanService from '@/entities/travel-plan/travel-plan.service';

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
  describe('TravelPlan Management Component', () => {
    let wrapper: Wrapper<TravelPlanClass>;
    let comp: TravelPlanClass;
    let travelPlanServiceStub: SinonStubbedInstance<TravelPlanService>;

    beforeEach(() => {
      travelPlanServiceStub = sinon.createStubInstance<TravelPlanService>(TravelPlanService);
      travelPlanServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<TravelPlanClass>(TravelPlanComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          travelPlanService: () => travelPlanServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      travelPlanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllTravelPlans();
      await comp.$nextTick();

      // THEN
      expect(travelPlanServiceStub.retrieve.called).toBeTruthy();
      expect(comp.travelPlans[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
