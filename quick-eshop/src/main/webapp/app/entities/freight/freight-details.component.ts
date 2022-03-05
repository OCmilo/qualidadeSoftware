import { Component, Vue, Inject } from 'vue-property-decorator';

import { IFreight } from '@/shared/model/freight.model';
import FreightService from './freight.service';

@Component
export default class FreightDetails extends Vue {
  @Inject('freightService') private freightService: () => FreightService;
  public freight: IFreight = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.freightId) {
        vm.retrieveFreight(to.params.freightId);
      }
    });
  }

  public retrieveFreight(freightId) {
    this.freightService()
      .find(freightId)
      .then(res => {
        this.freight = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
