import { Component, Vue, Inject } from 'vue-property-decorator';
import { IPurchaseProcess } from '@/shared/model/purchase-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import PurchaseProcessService from './purchase-process.service';

@Component
export default class PurchaseProcessListComponent extends Vue {
  @Inject('purchaseProcessService') private purchaseProcessService: () => PurchaseProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'PurchaseProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public purchaseProcessList: IPurchaseProcess[] = [];

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
    this.purchaseProcessService()
      .retrieve()
      .then(
        res => {
          this.purchaseProcessList = res.data;
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
