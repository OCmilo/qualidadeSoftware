import { Component, Vue, Inject } from 'vue-property-decorator';

import ProductFormService from './product-form.service';
import { ProductFormContext } from './product-form.model';

@Component
export default class ProductFormDetailsComponent extends Vue {
  private productFormService: ProductFormService = new ProductFormService();
  private taskContext: ProductFormContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.productFormService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
