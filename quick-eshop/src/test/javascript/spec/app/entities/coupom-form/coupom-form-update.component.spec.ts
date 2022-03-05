/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import CoupomFormUpdateComponent from '@/entities/coupom-form/coupom-form-update.vue';
import CoupomFormClass from '@/entities/coupom-form/coupom-form-update.component';
import CoupomFormService from '@/entities/coupom-form/coupom-form.service';

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
  describe('CoupomForm Management Update Component', () => {
    let wrapper: Wrapper<CoupomFormClass>;
    let comp: CoupomFormClass;
    let coupomFormServiceStub: SinonStubbedInstance<CoupomFormService>;

    beforeEach(() => {
      coupomFormServiceStub = sinon.createStubInstance<CoupomFormService>(CoupomFormService);

      wrapper = shallowMount<CoupomFormClass>(CoupomFormUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          coupomFormService: () => coupomFormServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.coupomForm = entity;
        coupomFormServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(coupomFormServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.coupomForm = entity;
        coupomFormServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(coupomFormServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCoupomForm = { id: 123 };
        coupomFormServiceStub.find.resolves(foundCoupomForm);
        coupomFormServiceStub.retrieve.resolves([foundCoupomForm]);

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
