import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPurchaseProcess, PurchaseProcess } from '@/shared/model/purchase-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Product } from '@/shared/model/product.model';
import PurchaseProcessService from './purchase-process.service';

const validations: any = {
  purchaseProcess: {
    product: {
      name: {},
      quantity: {},
    },
  },
};

@Component({
  validations,
})
export default class ProductFormInitComponent extends Vue {
  @Inject('purchaseProcessService') private purchaseProcessService: () => PurchaseProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'PurchaseProcess';
  public purchaseProcess: IPurchaseProcess = new PurchaseProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initProductForm();
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

    this.purchaseProcessService()
      .create(this.purchaseProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('quickeshopApp.productForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initProductForm(): void {
    this.purchaseProcess.product = new Product();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.purchaseProcess.processInstance = new ProcessInstance();
      this.purchaseProcess.processInstance.processDefinition = res;
    });
  }
}
