import { Component, Vue, Inject } from 'vue-property-decorator';

import CoupomFormService from './coupom-form.service';
import { CoupomFormContext } from './coupom-form.model';

@Component
export default class CoupomFormDetailsComponent extends Vue {
  private coupomFormService: CoupomFormService = new CoupomFormService();
  private taskContext: CoupomFormContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.coupomFormService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
