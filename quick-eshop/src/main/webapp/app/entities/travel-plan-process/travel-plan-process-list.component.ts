import { Component, Vue, Inject } from 'vue-property-decorator';
import { ITravelPlanProcess } from '@/shared/model/travel-plan-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import TravelPlanProcessService from './travel-plan-process.service';

@Component
export default class TravelPlanProcessListComponent extends Vue {
  @Inject('travelPlanProcessService') private travelPlanProcessService: () => TravelPlanProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'TravelPlanProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public travelPlanProcessList: ITravelPlanProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.travelPlanProcessService()
      .retrieve()
      .then(
        res => {
          this.travelPlanProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
