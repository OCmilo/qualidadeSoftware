/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import WarrantyDetailComponent from '@/entities/warranty/warranty-details.vue';
import WarrantyClass from '@/entities/warranty/warranty-details.component';
import WarrantyService from '@/entities/warranty/warranty.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Warranty Management Detail Component', () => {
    let wrapper: Wrapper<WarrantyClass>;
    let comp: WarrantyClass;
    let warrantyServiceStub: SinonStubbedInstance<WarrantyService>;

    beforeEach(() => {
      warrantyServiceStub = sinon.createStubInstance<WarrantyService>(WarrantyService);

      wrapper = shallowMount<WarrantyClass>(WarrantyDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { warrantyService: () => warrantyServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundWarranty = { id: 123 };
        warrantyServiceStub.find.resolves(foundWarranty);

        // WHEN
        comp.retrieveWarranty(123);
        await comp.$nextTick();

        // THEN
        expect(comp.warranty).toBe(foundWarranty);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundWarranty = { id: 123 };
        warrantyServiceStub.find.resolves(foundWarranty);

        // WHEN
        comp.beforeRouteEnter({ params: { warrantyId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.warranty).toBe(foundWarranty);
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
