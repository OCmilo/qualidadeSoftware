/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import WarrantyFormDetailComponent from '@/entities/warranty-form/warranty-form-details.vue';
import WarrantyFormClass from '@/entities/warranty-form/warranty-form-details.component';
import WarrantyFormService from '@/entities/warranty-form/warranty-form.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('WarrantyForm Management Detail Component', () => {
    let wrapper: Wrapper<WarrantyFormClass>;
    let comp: WarrantyFormClass;
    let warrantyFormServiceStub: SinonStubbedInstance<WarrantyFormService>;

    beforeEach(() => {
      warrantyFormServiceStub = sinon.createStubInstance<WarrantyFormService>(WarrantyFormService);

      wrapper = shallowMount<WarrantyFormClass>(WarrantyFormDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { warrantyFormService: () => warrantyFormServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundWarrantyForm = { id: 123 };
        warrantyFormServiceStub.find.resolves(foundWarrantyForm);

        // WHEN
        comp.retrieveWarrantyForm(123);
        await comp.$nextTick();

        // THEN
        expect(comp.warrantyForm).toBe(foundWarrantyForm);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundWarrantyForm = { id: 123 };
        warrantyFormServiceStub.find.resolves(foundWarrantyForm);

        // WHEN
        comp.beforeRouteEnter({ params: { warrantyFormId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.warrantyForm).toBe(foundWarrantyForm);
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
