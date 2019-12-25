<template>
  <div class="container">
    <h1>博客系统-博客管理</h1>
    <hr/>

    <h3>所有博客 <router-link to="/blog/add" type="button" class="btn btn-primary btn-sm">添加</router-link></h3>

    <!-- 如果用户列表为空 -->
    <div class="alert alert-warning" role="alert" v-if="blogList.length == 0">
      <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>Blog表为空，请<router-link to="/blog/add" type="button" class="btn btn-primary btn-sm">添加</router-link>
    </div>

    <!-- 如果用户列表非空 -->
    <table class="table table-bordered table-striped" v-if="blogList.length > 0">
      <tbody>
        <tr>
          <th>ID</th>
          <th>标题</th>
          <th>作者</th>
          <th>发布日期</th>
          <th>操作</th>
        </tr>

        <template v-for="blog in blogList">
          <tr v-bind:key="blog.id">
            <td>{{blog.id}}</td>
            <td>{{blog.title}}</td>
            <td>{{blog.nickname}}, {{blog.firstName}} {{blog.lastName}}</td>
            <td>{{ blog.pubDate | moment("YYYY年MM月DD日") }}</td>
            <td>
              <router-link :to="'/blog/show/' + blog.id" type="button" class="btn btn-sm btn-success">详情</router-link> &nbsp;
              <router-link :to="'/blog/edit/' + blog.id" type="button" class="btn btn-sm btn-warning">修改</router-link> &nbsp;
              <a v-on:click="deleteBlog(blog.id)" type="button" class="btn btn-sm btn-danger" >删除</a>
            </td>
          </tr>
        </template>
      </tbody>
    </table>
  </div>
</template>

<script>
import Vue from 'vue'
import BlogService from "../services/BlogService";

export default {
  name: 'ListUser',
  methods: {
      deleteBlog: function (blogId) {
          BlogService.deleteBlog({blogId: blogId}, () => {this.getBlogList();}, errMsg => {Vue.console.error(errMsg)})
      },
      getBlogList: function() {
          BlogService.getBlogList({}, blogList => { this.blogList = blogList; }, errMsg => { Vue.console.error(errMsg); })
      }
  },
  data: function() {
    return {
      blogList: []
    }
  },
  mounted: function() {
    this.getBlogList();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>