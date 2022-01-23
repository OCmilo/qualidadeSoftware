/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import TravelPlanDetailComponent from '@/entities/travel-plan/travel-plan-details.vue';
import TravelPlanClass from '@/entities/travel-plan/travel-plan-details.component';
import TravelPlanService from '@/entities/travel-plan/travel-plan.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('TravelPlan Management Detail Component', () => {
    let wrapper: Wrapper<TravelPlanClass>;
    let comp: TravelPlanClass;
    let travelPlanServiceStub: SinonStubbedInstance<TravelPlanService>;

    beforeEach(() => {
      travelPlanServiceStub = sinon.createStubInstance<TravelPlanService>(TravelPlanService);

      wrapper = shallowMount<TravelPlanClass>(TravelPlanDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { travelPlanService: () => travelPlanServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundTravelPlan = { id: 123 };
        travelPlanServiceStub.find.resolves(foundTravelPlan);

        // WHEN
        comp.retrieveTravelPlan(123);
        await comp.$nextTick();

        // THEN
        expect(comp.travelPlan).toBe(foundTravelPlan);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundTravelPlan = { id: 123 };
        travelPlanServiceStub.find.resolves(foundTravelPlan);

        // WHEN
        comp.beforeRouteEnter({ params: { travelPlanId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.travelPlan).toBe(foundTravelPlan);
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
