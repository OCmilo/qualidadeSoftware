import { Component, Vue, Inject } from 'vue-property-decorator';

import WarrantyFormService from './warranty-form.service';
import { WarrantyFormContext } from './warranty-form.model';

@Component
export default class WarrantyFormDetailsComponent extends Vue {
  private warrantyFormService: WarrantyFormService = new WarrantyFormService();
  private taskContext: WarrantyFormContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.warrantyFormService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
