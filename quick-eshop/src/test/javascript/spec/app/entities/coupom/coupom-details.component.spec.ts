/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import CoupomDetailComponent from '@/entities/coupom/coupom-details.vue';
import CoupomClass from '@/entities/coupom/coupom-details.component';
import CoupomService from '@/entities/coupom/coupom.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Coupom Management Detail Component', () => {
    let wrapper: Wrapper<CoupomClass>;
    let comp: CoupomClass;
    let coupomServiceStub: SinonStubbedInstance<CoupomService>;

    beforeEach(() => {
      coupomServiceStub = sinon.createStubInstance<CoupomService>(CoupomService);

      wrapper = shallowMount<CoupomClass>(CoupomDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { coupomService: () => coupomServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCoupom = { id: 123 };
        coupomServiceStub.find.resolves(foundCoupom);

        // WHEN
        comp.retrieveCoupom(123);
        await comp.$nextTick();

        // THEN
        expect(comp.coupom).toBe(foundCoupom);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCoupom = { id: 123 };
        coupomServiceStub.find.resolves(foundCoupom);

        // WHEN
        comp.beforeRouteEnter({ params: { coupomId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.coupom).toBe(foundCoupom);
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
