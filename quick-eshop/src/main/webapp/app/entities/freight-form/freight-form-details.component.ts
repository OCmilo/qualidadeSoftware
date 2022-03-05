import { Component, Vue, Inject } from 'vue-property-decorator';

import { IFreightForm } from '@/shared/model/freight-form.model';
import FreightFormService from './freight-form.service';

@Component
export default class FreightFormDetails extends Vue {
  @Inject('freightFormService') private freightFormService: () => FreightFormService;
  public freightForm: IFreightForm = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.freightFormId) {
        vm.retrieveFreightForm(to.params.freightFormId);
      }
    });
  }

  public retrieveFreightForm(freightFormId) {
    this.freightFormService()
      .find(freightFormId)
      .then(res => {
        this.freightForm = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
