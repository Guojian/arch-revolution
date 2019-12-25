<template>
  <div class="index">
    <h1>这里是Demo首页</h1>

    <h1>功能列表</h1>
    <ul>
      <li><router-link to="/user/list">用户管理</router-link></li>
      <li><router-link to="/blog/list">博文管理</router-link></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Index'
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>