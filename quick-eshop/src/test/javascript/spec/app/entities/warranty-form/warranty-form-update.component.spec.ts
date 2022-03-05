/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import WarrantyFormUpdateComponent from '@/entities/warranty-form/warranty-form-update.vue';
import WarrantyFormClass from '@/entities/warranty-form/warranty-form-update.component';
import WarrantyFormService from '@/entities/warranty-form/warranty-form.service';

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
  describe('WarrantyForm Management Update Component', () => {
    let wrapper: Wrapper<WarrantyFormClass>;
    let comp: WarrantyFormClass;
    let warrantyFormServiceStub: SinonStubbedInstance<WarrantyFormService>;

    beforeEach(() => {
      warrantyFormServiceStub = sinon.createStubInstance<WarrantyFormService>(WarrantyFormService);

      wrapper = shallowMount<WarrantyFormClass>(WarrantyFormUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          warrantyFormService: () => warrantyFormServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.warrantyForm = entity;
        warrantyFormServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(warrantyFormServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.warrantyForm = entity;
        warrantyFormServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(warrantyFormServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundWarrantyForm = { id: 123 };
        warrantyFormServiceStub.find.resolves(foundWarrantyForm);
        warrantyFormServiceStub.retrieve.resolves([foundWarrantyForm]);

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
