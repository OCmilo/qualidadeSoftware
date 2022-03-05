/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import FreightUpdateComponent from '@/entities/freight/freight-update.vue';
import FreightClass from '@/entities/freight/freight-update.component';
import FreightService from '@/entities/freight/freight.service';

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
  describe('Freight Management Update Component', () => {
    let wrapper: Wrapper<FreightClass>;
    let comp: FreightClass;
    let freightServiceStub: SinonStubbedInstance<FreightService>;

    beforeEach(() => {
      freightServiceStub = sinon.createStubInstance<FreightService>(FreightService);

      wrapper = shallowMount<FreightClass>(FreightUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          freightService: () => freightServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.freight = entity;
        freightServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(freightServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.freight = entity;
        freightServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(freightServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundFreight = { id: 123 };
        freightServiceStub.find.resolves(foundFreight);
        freightServiceStub.retrieve.resolves([foundFreight]);

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
