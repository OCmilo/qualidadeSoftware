import { Component, Vue, Inject } from 'vue-property-decorator';

import AddWarrantyService from './add-warranty.service';
import { AddWarrantyContext } from './add-warranty.model';

@Component
export default class AddWarrantyDetailsComponent extends Vue {
  private addWarrantyService: AddWarrantyService = new AddWarrantyService();
  private taskContext: AddWarrantyContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.addWarrantyService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
