import { Component, Vue, Inject } from 'vue-property-decorator';

import ChooseProductService from './choose-product.service';
import { ChooseProductContext } from './choose-product.model';

@Component
export default class ChooseProductDetailsComponent extends Vue {
  private chooseProductService: ChooseProductService = new ChooseProductService();
  private taskContext: ChooseProductContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.chooseProductService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
