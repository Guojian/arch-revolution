import Vue from 'vue'

const getBlogList = function(args, successCallback, errorCallback) {
    var memthodName = 'getBlogList'
    Vue.http.get(window.API_GATEWAY_URL + '/api/aggregation/blogs').then(response => {
        if (response.status !== 200) {
            Vue.console.log("status: " + response.status + ", body: " + response.body)
            errorCallback(memthodName + ' request failed')
        } else {
            successCallback(response.body)
        }
    }, response => {
        Vue.console.log("status: " + response.status + ", body: " + response.body)
        errorCallback(memthodName + ' request failed')
    })
}

const getBlogById = function (args, successCallback, errorCallback) {
    var memthodName = 'getBlogById'
    Vue.http.get(window.API_GATEWAY_URL + '/api/aggregation/blogs/' + args.blogId).then(response => {
        if (response.status !== 200) {
            Vue.console.log("status: " + response.status + ", body: " + response.body)
            errorCallback(memthodName + ' request failed')
        } else {
            successCallback(response.body)
        }
    }, response => {
        Vue.console.log("status: " + response.status + ", body: " + response.body)
        errorCallback(memthodName + ' request failed')
    })
}

const editBlog = function (args, successCallback, errorCallback) {
    var memthodName = 'editBlog'
    Vue.http.put(window.API_GATEWAY_URL + '/api/blogs/' + args.blogId, args.blog).then(response => {
        if (response.status !== 200) {
            Vue.console.log("status: " + response.status + ", body: " + response.body)
            errorCallback(memthodName + ' request failed')
        } else {
            successCallback(response.body)
        }
    }, response => {
        Vue.console.log("status: " + response.status + ", body: " + response.body)
        errorCallback(memthodName + ' request failed')
    })
}

const addBlog = function (args, successCallback, errorCallback) {
    var memthodName = 'addBlog'
    Vue.http.post(window.API_GATEWAY_URL + '/api/blogs', args.blog).then(response => {
        if (response.status !== 200) {
            Vue.console.log("status: " + response.status + ", body: " + response.body)
            errorCallback(memthodName + ' request failed')
        } else {
            successCallback(response.body)
        }
    }, response => {
        Vue.console.log("status: " + response.status + ", body: " + response.body)
        errorCallback(memthodName + ' request failed')
    })
}

const deleteBlog = function (args, successCallback, errorCallback) {
    var memthodName = 'deleteBlog'
    Vue.http.delete(window.API_GATEWAY_URL + '/api/blogs/' + args.blogId).then(response => {
        if (response.status !== 200) {
            Vue.console.log("status: " + response.status + ", body: " + response.body)
            errorCallback(memthodName + ' request failed')
        } else {
            successCallback(response.body)
        }
    }, response => {
        Vue.console.log("status: " + response.status + ", body: " + response.body)
        errorCallback(memthodName + ' request failed')
    })
}

export default {
    getBlogList,
    getBlogById,
    editBlog,
    addBlog,
    deleteBlog
}