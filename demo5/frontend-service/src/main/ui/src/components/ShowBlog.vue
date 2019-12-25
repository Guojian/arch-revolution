<template>
  <div class="show-blog container">
    <h1>博文详情</h1>
    <hr/>
    <table class="table table-bordered table-striped">
      <tbody>
        <tr>
          <th>ID</th>
          <td>{{blog.id}}</td>
        </tr>
        <tr>
          <th>Title</th>
          <td>{{blog.title}}</td>
        </tr>
        <tr>
          <th>Author</th>
          <td>{{blog.nickname}}, {{blog.firstName}} {{blog.lastName}}</td>
        </tr>
        <tr>
          <th>Content</th>
          <td>{{blog.content}}</td>
        </tr>
        <tr>
          <th>Publish Date</th>
          <td>{{ blog.pubDate | moment("YYYY年MM月DD日")}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import Vue from 'vue'
import BlogService from "../services/BlogService";

export default {
  name: 'ShowBlog',
    data: function() {
        return {
            blog: {}
        }
    },
    methods: {
      getBlog: function() {
        BlogService.getBlogById({blogId: this.$route.params.id}, blog => { this.blog = blog; }, errMsg => { Vue.console.error(errMsg); })
      }
    },
    mounted: function() {
      this.getBlog()
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1 {
  margin: 40px 0 0;
}
</style>