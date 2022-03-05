/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import FreightFormDetailComponent from '@/entities/freight-form/freight-form-details.vue';
import FreightFormClass from '@/entities/freight-form/freight-form-details.component';
import FreightFormService from '@/entities/freight-form/freight-form.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('FreightForm Management Detail Component', () => {
    let wrapper: Wrapper<FreightFormClass>;
    let comp: FreightFormClass;
    let freightFormServiceStub: SinonStubbedInstance<FreightFormService>;

    beforeEach(() => {
      freightFormServiceStub = sinon.createStubInstance<FreightFormService>(FreightFormService);

      wrapper = shallowMount<FreightFormClass>(FreightFormDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { freightFormService: () => freightFormServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundFreightForm = { id: 123 };
        freightFormServiceStub.find.resolves(foundFreightForm);

        // WHEN
        comp.retrieveFreightForm(123);
        await comp.$nextTick();

        // THEN
        expect(comp.freightForm).toBe(foundFreightForm);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundFreightForm = { id: 123 };
        freightFormServiceStub.find.resolves(foundFreightForm);

        // WHEN
        comp.beforeRouteEnter({ params: { freightFormId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.freightForm).toBe(foundFreightForm);
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
