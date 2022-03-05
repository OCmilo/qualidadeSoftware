/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import WarrantyUpdateComponent from '@/entities/warranty/warranty-update.vue';
import WarrantyClass from '@/entities/warranty/warranty-update.component';
import WarrantyService from '@/entities/warranty/warranty.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Warranty Management Update Component', () => {
    let wrapper: Wrapper<WarrantyClass>;
    let comp: WarrantyClass;
    let warrantyServiceStub: SinonStubbedInstance<WarrantyService>;

    beforeEach(() => {
      warrantyServiceStub = sinon.createStubInstance<WarrantyService>(WarrantyService);

      wrapper = shallowMount<WarrantyClass>(WarrantyUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          warrantyService: () => warrantyServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.warranty = entity;
        warrantyServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(warrantyServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.warranty = entity;
        warrantyServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(warrantyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundWarranty = { id: 123 };
        warrantyServiceStub.find.resolves(foundWarranty);
        warrantyServiceStub.retrieve.resolves([foundWarranty]);

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
