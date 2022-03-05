/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import CoupomFormDetailComponent from '@/entities/coupom-form/coupom-form-details.vue';
import CoupomFormClass from '@/entities/coupom-form/coupom-form-details.component';
import CoupomFormService from '@/entities/coupom-form/coupom-form.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('CoupomForm Management Detail Component', () => {
    let wrapper: Wrapper<CoupomFormClass>;
    let comp: CoupomFormClass;
    let coupomFormServiceStub: SinonStubbedInstance<CoupomFormService>;

    beforeEach(() => {
      coupomFormServiceStub = sinon.createStubInstance<CoupomFormService>(CoupomFormService);

      wrapper = shallowMount<CoupomFormClass>(CoupomFormDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { coupomFormService: () => coupomFormServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCoupomForm = { id: 123 };
        coupomFormServiceStub.find.resolves(foundCoupomForm);

        // WHEN
        comp.retrieveCoupomForm(123);
        await comp.$nextTick();

        // THEN
        expect(comp.coupomForm).toBe(foundCoupomForm);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCoupomForm = { id: 123 };
        coupomFormServiceStub.find.resolves(foundCoupomForm);

        // WHEN
        comp.beforeRouteEnter({ params: { coupomFormId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.coupomForm).toBe(foundCoupomForm);
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
