<template>
  <div class="edit-user container">
    <h1>更新用户信息</h1>
    <hr/>

    <form method="post" role="form">
      <div class="form-group">
        <label for="firstName">Nickname:</label>
        <input type="text" class="form-control" id="nickname" name="nickname" placeholder="Enter Nickname:"
               v-model="user.nickname"/>
      </div>
      <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter FirstName:"
               v-model="user.firstName"/>
      </div>
      <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter LastName:"
               v-model="user.lastName"/>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="text" class="form-control" id="password" name="password" placeholder="Enter Password:"
               v-model="user.password"/>
      </div>
      <!-- 把 id 一并写入 userP 中 -->
      <input type="hidden" id="id" name="id" v-model="user.id"/>

      <div class="form-group">
        <a v-on:click="editUser" type="button" class="btn btn-sm btn-success" >提交</a>
      </div>
    </form>
  </div>
</template>

<script>
import Vue from 'vue'
import UserService from "../services/UserService"

export default {
  name: 'EditUser',
    data: function() {
        return {
            user: {}
        }
    },
    methods: {
        editUser: function () {
            let newUser = {
                id: this.user.id,
                nickname: this.user.nickname,
                firstName: this.user.firstName,
                lastName: this.user.lastName,
                password: this.user.password
            };
            UserService.editUser({userId: this.user.id, user: newUser}, () => {this.$router.push( { path: '/user/list' });}, errMsg => {Vue.console.error(errMsg)})
        },
        getUser: function () {
          UserService.getUserById({userId: this.$route.params.id}, user => { this.user = user; }, errMsg => { Vue.console.error(errMsg); })
        }
    },
    mounted: function() {
        this.getUser()
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>