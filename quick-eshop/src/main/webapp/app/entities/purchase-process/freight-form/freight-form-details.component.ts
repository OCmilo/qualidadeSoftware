import { Component, Vue, Inject } from 'vue-property-decorator';

import FreightFormService from './freight-form.service';
import { FreightFormContext } from './freight-form.model';

@Component
export default class FreightFormDetailsComponent extends Vue {
  private freightFormService: FreightFormService = new FreightFormService();
  private taskContext: FreightFormContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.freightFormService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
