<template>
  <div class="edit-blog container">
    <h1>修改博客</h1>
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
            <option v-bind:key="user.id" :value="user.id" selected="selected" v-if="user.id == blog.userId">{{user.nickname}},
              {{user.firstName}} {{user.lastName}}
            </option>
            <option v-bind:key="user.id" :value="user.id" v-if="user.id != blog.userId">{{user.nickname}}, {{user.firstName}}
              {{user.lastName}}
            </option>
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
        <input type="date" class="form-control" id="pubDate" name="pubDate" v-model="blogDisplayPubDate"/>
      </div>
      <!-- 把 id 一并写入 blogP 中 -->
      <input type="hidden" id="id" name="id" v-model="blog.id"/>
      <div class="form-group">
        <a type="button" v-on:click="editBlog" class="btn btn-sm btn-success">提交</a>
      </div>
    </form>
  </div>
</template>

<script>
  import Vue from 'vue'
  import UserService from "../services/UserService"
  import BlogService from "../services/BlogService"
  import moment from 'moment'

  export default {
    name: 'EditBlog',
    data: function () {
      return {
        blog: {},
        userList: [],
        blogDisplayPubDate: ''
      }
    },
    methods: {
      editBlog: function () {
        let newBlog = {
          id: this.blog.id,
          title: this.blog.title,
          userId: this.blog.userId,
          content: this.blog.content,
          pubDate: new Date(this.blogDisplayPubDate).toISOString()
        };
        BlogService.editBlog({blogId: this.blog.id, blog: newBlog}, () => {
          this.$router.push({path: '/blog/list'});
        }, errMsg => {
          Vue.console.error(errMsg)
        })
      },
      getBlog: function () {
        BlogService.getBlogById({blogId: this.$route.params.id}, blog => {
          this.blog = blog;
          this.blogDisplayPubDate = moment(new Date(this.blog.pubDate)).format("YYYY-MM-DD");
        }, errMsg => { Vue.console.error(errMsg); })
      },
      getUserList: function() {
        UserService.getUserList({}, userList => { this.userList = userList; }, errMsg => { Vue.console.error(errMsg); })
      }
    },
    mounted: function() {
      this.getBlog();
      this.getUserList();
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>