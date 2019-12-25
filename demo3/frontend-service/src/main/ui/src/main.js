import Vue from 'vue'
import vueLogger from 'vue-logger'
import VueResource from 'vue-resource'
Vue.use(vueLogger, {
  prefix: () => new Date(),
  dev: true,
  shortname: true,
  levels: ["log", "warn", "debug", "error", "dir"],
  forceLevels: []
});
Vue.use(require('vue-moment'));
Vue.use(VueResource)

import App from './App.vue'
import Router from './router.js'

Vue.config.productionTip = false;

new Vue({
  render: h => h(App),
  router: Router,
}).$mount('#app');
