/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import FreightFormUpdateComponent from '@/entities/freight-form/freight-form-update.vue';
import FreightFormClass from '@/entities/freight-form/freight-form-update.component';
import FreightFormService from '@/entities/freight-form/freight-form.service';

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
  describe('FreightForm Management Update Component', () => {
    let wrapper: Wrapper<FreightFormClass>;
    let comp: FreightFormClass;
    let freightFormServiceStub: SinonStubbedInstance<FreightFormService>;

    beforeEach(() => {
      freightFormServiceStub = sinon.createStubInstance<FreightFormService>(FreightFormService);

      wrapper = shallowMount<FreightFormClass>(FreightFormUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          freightFormService: () => freightFormServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.freightForm = entity;
        freightFormServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(freightFormServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.freightForm = entity;
        freightFormServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(freightFormServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundFreightForm = { id: 123 };
        freightFormServiceStub.find.resolves(foundFreightForm);
        freightFormServiceStub.retrieve.resolves([foundFreightForm]);

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
