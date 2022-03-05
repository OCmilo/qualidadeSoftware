/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import CoupomUpdateComponent from '@/entities/coupom/coupom-update.vue';
import CoupomClass from '@/entities/coupom/coupom-update.component';
import CoupomService from '@/entities/coupom/coupom.service';

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
  describe('Coupom Management Update Component', () => {
    let wrapper: Wrapper<CoupomClass>;
    let comp: CoupomClass;
    let coupomServiceStub: SinonStubbedInstance<CoupomService>;

    beforeEach(() => {
      coupomServiceStub = sinon.createStubInstance<CoupomService>(CoupomService);

      wrapper = shallowMount<CoupomClass>(CoupomUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          coupomService: () => coupomServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.coupom = entity;
        coupomServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(coupomServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.coupom = entity;
        coupomServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(coupomServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCoupom = { id: 123 };
        coupomServiceStub.find.resolves(foundCoupom);
        coupomServiceStub.retrieve.resolves([foundCoupom]);

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
