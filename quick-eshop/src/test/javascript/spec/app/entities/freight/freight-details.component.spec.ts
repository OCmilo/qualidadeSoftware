/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import FreightDetailComponent from '@/entities/freight/freight-details.vue';
import FreightClass from '@/entities/freight/freight-details.component';
import FreightService from '@/entities/freight/freight.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Freight Management Detail Component', () => {
    let wrapper: Wrapper<FreightClass>;
    let comp: FreightClass;
    let freightServiceStub: SinonStubbedInstance<FreightService>;

    beforeEach(() => {
      freightServiceStub = sinon.createStubInstance<FreightService>(FreightService);

      wrapper = shallowMount<FreightClass>(FreightDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { freightService: () => freightServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundFreight = { id: 123 };
        freightServiceStub.find.resolves(foundFreight);

        // WHEN
        comp.retrieveFreight(123);
        await comp.$nextTick();

        // THEN
        expect(comp.freight).toBe(foundFreight);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundFreight = { id: 123 };
        freightServiceStub.find.resolves(foundFreight);

        // WHEN
        comp.beforeRouteEnter({ params: { freightId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.freight).toBe(foundFreight);
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
