import { Component, Vue, Inject } from 'vue-property-decorator';

import AddProductsService from './add-products.service';
import { AddProductsContext } from './add-products.model';

@Component
export default class AddProductsDetailsComponent extends Vue {
  private addProductsService: AddProductsService = new AddProductsService();
  private taskContext: AddProductsContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.addProductsService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
