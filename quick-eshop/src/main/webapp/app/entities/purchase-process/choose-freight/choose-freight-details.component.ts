import { Component, Vue, Inject } from 'vue-property-decorator';

import ChooseFreightService from './choose-freight.service';
import { ChooseFreightContext } from './choose-freight.model';

@Component
export default class ChooseFreightDetailsComponent extends Vue {
  private chooseFreightService: ChooseFreightService = new ChooseFreightService();
  private taskContext: ChooseFreightContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.chooseFreightService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
