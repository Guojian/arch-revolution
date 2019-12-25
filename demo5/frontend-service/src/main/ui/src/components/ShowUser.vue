<template>
  <div class="show-user container">
    <h1>用户详情</h1>
    <hr/>

    <table class="table table-bordered table-striped">
      <tbody>
        <tr>
          <th>ID</th>
          <td>{{user.id}}</td>
        </tr>
        <tr>
          <th>Nickname</th>
          <td>{{user.nickname}}</td>
        </tr>
        <tr>
          <th>First Name</th>
          <td>{{user.firstName}}</td>
        </tr>
        <tr>
          <th>Last Name</th>
          <td>{{user.lastName}}</td>
        </tr>
        <tr>
          <th>Password</th>
          <td>{{user.password}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import Vue from 'vue'
import UserService from "../services/UserService"

export default {
  name: 'ShowUser',
    data: function() {
        return {
            user: {}
        }
    },
    methods: {
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
h1 {
  margin: 40px 0 0;
}
</style>