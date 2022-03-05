import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICoupomForm } from '@/shared/model/coupom-form.model';
import CoupomFormService from './coupom-form.service';

@Component
export default class CoupomFormDetails extends Vue {
  @Inject('coupomFormService') private coupomFormService: () => CoupomFormService;
  public coupomForm: ICoupomForm = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.coupomFormId) {
        vm.retrieveCoupomForm(to.params.coupomFormId);
      }
    });
  }

  public retrieveCoupomForm(coupomFormId) {
    this.coupomFormService()
      .find(coupomFormId)
      .then(res => {
        this.coupomForm = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
