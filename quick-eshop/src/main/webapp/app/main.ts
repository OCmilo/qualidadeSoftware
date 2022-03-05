// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.common with an alias.
import Vue from 'vue';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import App from './app.vue';
import Vue2Filters from 'vue2-filters';
import { ToastPlugin } from 'bootstrap-vue';
import router from './router';
import * as config from './shared/config/config';
import * as bootstrapVueConfig from './shared/config/config-bootstrap-vue';
import JhiItemCountComponent from './shared/jhi-item-count.vue';
import JhiSortIndicatorComponent from './shared/sort/jhi-sort-indicator.vue';
import InfiniteLoading from 'vue-infinite-loading';
import HealthService from './admin/health/health.service';
import MetricsService from './admin/metrics/metrics.service';
import LogsService from './admin/logs/logs.service';
import ConfigurationService from '@/admin/configuration/configuration.service';
import ActivateService from './account/activate/activate.service';
import RegisterService from './account/register/register.service';
import UserManagementService from '@/admin/user-management/user-management.service';
import LoginService from './account/login.service';
import AccountService from './account/account.service';

import Akip from 'akip-vue-community';
import VueMarkdown from 'vue-markdown';
// jhipster-pais-needle-add-component-to-main-import - JHipster/Camunda will import components here

import '../content/scss/vendor.scss';
import TranslationService from '@/locale/translation.service';

import UserOAuth2Service from '@/entities/user/user.oauth2.service';
/* tslint:disable */

import TravelPlanService from '@/entities/travel-plan/travel-plan.service';
import TravelPlanProcessService from '@/entities/travel-plan-process/travel-plan-process.service';
import ProductService from '@/entities/product/product.service';
import PurchaseProcessService from '@/entities/purchase-process/purchase-process.service';
import FreightService from '@/entities/freight/freight.service';
import WarrantyService from '@/entities/warranty/warranty.service';
import CoupomFormService from '@/entities/coupom-form/coupom-form.service';
import CoupomService from '@/entities/coupom/coupom.service';
import WarrantyFormService from '@/entities/warranty-form/warranty-form.service';
import FreightFormService from '@/entities/freight-form/freight-form.service';
// jhipster-needle-add-entity-service-to-main-import - JHipster will import entities services here

/* tslint:enable */
Vue.config.productionTip = false;
config.initVueApp(Vue);
config.initFortAwesome(Vue);
bootstrapVueConfig.initBootstrapVue(Vue);
Vue.use(Vue2Filters);
Vue.use(ToastPlugin);
Vue.component('font-awesome-icon', FontAwesomeIcon);
Vue.component('jhi-item-count', JhiItemCountComponent);
Vue.component('jhi-sort-indicator', JhiSortIndicatorComponent);
Vue.component('infinite-loading', InfiniteLoading);

Vue.use(Akip);
Vue.component('vue-markdown', VueMarkdown);
// jhipster-pais-needle-add-component-to-main - JHipster/Camunda will register components here

const i18n = config.initI18N(Vue);
const store = config.initVueXStore(Vue);

const translationService = new TranslationService(store, i18n);
const loginService = new LoginService();
const accountService = new AccountService(store, translationService, router);

router.beforeEach((to, from, next) => {
  if (!to.matched.length) {
    next('/not-found');
  }

  if (to.meta && to.meta.authorities && to.meta.authorities.length > 0) {
    accountService.hasAnyAuthorityAndCheckAuth(to.meta.authorities).then(value => {
      if (!value) {
        sessionStorage.setItem('requested-url', to.fullPath);
        next('/forbidden');
      } else {
        next();
      }
    });
  } else {
    // no authorities, so just proceed
    next();
  }
});

/* tslint:disable */
new Vue({
  el: '#app',
  components: { App },
  template: '<App/>',
  router,
  provide: {
    loginService: () => loginService,
    activateService: () => new ActivateService(),
    registerService: () => new RegisterService(),
    userService: () => new UserManagementService(),
    healthService: () => new HealthService(),
    configurationService: () => new ConfigurationService(),
    logsService: () => new LogsService(),
    metricsService: () => new MetricsService(),

    userOAuth2Service: () => new UserOAuth2Service(),
    translationService: () => translationService,
    travelPlanService: () => new TravelPlanService(),
    travelPlanProcessService: () => new TravelPlanProcessService(),
    productService: () => new ProductService(),
    purchaseProcessService: () => new PurchaseProcessService(),
    freightService: () => new FreightService(),
    warrantyService: () => new WarrantyService(),
    coupomFormService: () => new CoupomFormService(),
    coupomService: () => new CoupomService(),
    warrantyFormService: () => new WarrantyFormService(),
    freightFormService: () => new FreightFormService(),
    // jhipster-needle-add-entity-service-to-main - JHipster will import entities services here
    accountService: () => accountService,
  },
  i18n,
  store,
});
