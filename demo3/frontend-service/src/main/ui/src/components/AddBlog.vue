<template>
  <div class="add-blog container">
    <h1>添加博客</h1>
    <hr/>
    <form method="post" role="form">
      <div class="form-group">
        <label for="title">Title:</label>
        <input type="text" class="form-control" id="title" name="title" placeholder="Enter Title:"
               v-model="blog.title"/>
      </div>
      <div class="form-group">
        <label for="userByUserId.id">Author:</label>
        <select class="form-control" id="userByUserId.id" name="userByUserId.id" v-model="blog.userId">
          <template v-for="user in userList">
            <option v-bind:key="user.id" :value="user.id">{{user.nickname}}, {{user.firstName}} {{user.lastName}}</option>
          </template>
        </select>
      </div>
      <div class="form-group">
        <label for="content">Content:</label>
        <textarea class="form-control" id="content" name="content" rows="3"
                  placeholder="Please Input Content" v-model="blog.content"></textarea>
      </div>
      <div class="form-group">
        <label for="pubDate">Publish Date:</label>
        <input type="date" class="form-control" id="pubDate" name="pubDate" v-model="blog.pubDate"/>
      </div>
      <div class="form-group">
        <a v-on:click="addBlog" type="button" class="btn btn-sm btn-success" >提交</a>
      </div>
    </form>
  </div>
</template>

<script>
  import Vue from 'vue'
  import UserService from "../services/UserService"
  import BlogService from "../services/BlogService";

  export default {
    name: 'AddBlog',
    data: function () {
      return {
        blog: {
          title: '',
          userId: -1,
          content: '',
          pubDate: ''
        },
        userList: []
      }
    },
    methods: {
      addBlog: function () {
        let newBlog = {
          title: this.blog.title,
          userId: this.blog.userId,
          content: this.blog.content,
          pubDate: this.blog.pubDate
        };
        BlogService.addBlog({blog: newBlog}, () => {
          this.$router.push({path: '/blog/list'});
        }, errMsg => {
          Vue.console.error(errMsg)
        })
      },
      getUserList: function() {
        UserService.getUserList({}, userList => { this.userList = userList; }, errMsg => { Vue.console.error(errMsg); })
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