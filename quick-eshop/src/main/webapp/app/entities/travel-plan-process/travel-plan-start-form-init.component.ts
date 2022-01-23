import { Component, Vue, Inject } from 'vue-property-decorator';

import { ITravelPlanProcess, TravelPlanProcess } from '@/shared/model/travel-plan-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { TravelPlan } from '@/shared/model/travel-plan.model';
import TravelPlanProcessService from './travel-plan-process.service';

const validations: any = {
  travelPlanProcess: {
    travelPlan: {
      name: {},
      startDate: {},
      endDate: {},
    },
  },
};

@Component({
  validations,
})
export default class TravelPlanStartFormInitComponent extends Vue {
  @Inject('travelPlanProcessService') private travelPlanProcessService: () => TravelPlanProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'TravelPlanProcess';
  public travelPlanProcess: ITravelPlanProcess = new TravelPlanProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initTravelPlanStartForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.travelPlanProcessService()
      .create(this.travelPlanProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('quickeshopApp.travelPlanStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initTravelPlanStartForm(): void {
    this.travelPlanProcess.travelPlan = new TravelPlan();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.travelPlanProcess.processInstance = new ProcessInstance();
      this.travelPlanProcess.processInstance.processDefinition = res;
    });
  }
}
