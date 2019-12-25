<template>
  <div class="add-user container">
    <h1>添加用户</h1>
    <hr/>
    <form method="post" role="form">
      <div class="form-group">
        <label for="firstName">Nickname:</label>
        <input type="text" class="form-control" id="nickname" name="nickname" placeholder="Enter Nickname:" v-model="user.nickname"/>
      </div>
      <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter FirstName:" v-model="user.firstName"/>
      </div>
      <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter LastName:" v-model="user.lastName"/>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="text" class="form-control" id="password" name="password" placeholder="Enter Password:" v-model="user.password"/>
      </div>
      <div class="form-group">
        <a v-on:click="addUser" type="button" class="btn btn-sm btn-success" >提交</a>
      </div>
    </form>
  </div>
</template>

<script>
import Vue from 'vue'
import UserService from "../services/UserService"

export default {
  name: 'AddUser',
  data: function() {
      return {
          user: {
              nickname: '',
              firstName: '',
              lastName: '',
              password: ''
          }
      }
  },
  methods: {
      addUser: function () {
          let newUser = {
              nickname: this.user.nickname,
              firstName: this.user.firstName,
              lastName: this.user.lastName,
              password: this.user.password
          };
          UserService.addUser({user: newUser}, () => {this.$router.push( { path: '/user/list' });}, errMsg => {Vue.console.error(errMsg)})
      }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>