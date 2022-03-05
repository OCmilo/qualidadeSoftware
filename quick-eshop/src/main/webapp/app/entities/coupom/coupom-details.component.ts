import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICoupom } from '@/shared/model/coupom.model';
import CoupomService from './coupom.service';

@Component
export default class CoupomDetails extends Vue {
  @Inject('coupomService') private coupomService: () => CoupomService;
  public coupom: ICoupom = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.coupomId) {
        vm.retrieveCoupom(to.params.coupomId);
      }
    });
  }

  public retrieveCoupom(coupomId) {
    this.coupomService()
      .find(coupomId)
      .then(res => {
        this.coupom = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
