<template>
  <div class="container list-user">
    <h1>博客系统-用户管理</h1>
    <hr/>

    <h3>所有用户 <router-link to="/user/add" type="button" class="btn btn-primary btn-sm">添加</router-link></h3>

    <!-- 如果用户列表为空 -->
    <div class="alert alert-warning" role="alert" v-if="userList.length == 0">
      <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>User表为空，请<router-link to="/user/add" type="button" class="btn btn-primary btn-sm">添加</router-link>
    </div>

    <!-- 如果用户列表非空 -->
      <table class="table table-bordered table-striped" v-if="userList.length > 0">
        <tbody>
          <tr>
            <th>ID</th>
            <th>昵称</th>
            <th>姓名</th>
            <th>密码</th>
            <th>操作</th>
          </tr>
          <template v-for="user in userList">
            <tr v-bind:key="user.id">
              <td>{{user.id}}</td>
              <td>{{user.nickname}}</td>
              <td>{{user.firstName}} {{user.lastName}}</td>
              <td>{{user.password}}</td>
              <td>
                <router-link :to="'/user/show/' + user.id" type="button" class="btn btn-sm btn-success">详情</router-link> &nbsp;
                <router-link :to="'/user/edit/' + user.id" type="button" class="btn btn-sm btn-warning">修改</router-link> &nbsp;
                <a v-on:click="deleteUser(user.id)" type="button" class="btn btn-sm btn-danger" >删除</a>
              </td>
            </tr>
          </template>
        </tbody>
      </table>
  </div>
</template>

<script>
import Vue from 'vue'
import UserService from '../services/UserService.js'

export default {
  name: 'ListUser',
  methods: {
      deleteUser: function (userId) {
          UserService.deleteUser({userId: userId}, () => {this.getUserList();}, errMsg => {Vue.console.error(errMsg)})
      },
      getUserList: function() {
          UserService.getUserList({}, userList => { this.userList = userList; }, errMsg => { Vue.console.error(errMsg); })
      }
  },
  data: function() {
    return {
      userList: []
    }
  },
  mounted: function() {
    this.getUserList();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>