import Vue from 'vue'

const getUserList = function(args, successCallback, errorCallback) {
    var memthodName = 'getUserList'
    Vue.http.get(window.API_GATEWAY_URL + '/api/users').then(response => {
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

const getUserById = function (args, successCallback, errorCallback) {
    var memthodName = 'getUserById'
    Vue.http.get(window.API_GATEWAY_URL + '/api/users/' + args.userId).then(response => {
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

const editUser = function (args, successCallback, errorCallback) {
    var memthodName = 'getUserById'
    Vue.http.put(window.API_GATEWAY_URL + '/api/users/' + args.userId, args.user).then(response => {
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

const addUser = function (args, successCallback, errorCallback) {
    var memthodName = 'getUserById'
    Vue.http.post(window.API_GATEWAY_URL + '/api/users', args.user).then(response => {
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

const deleteUser = function (args, successCallback, errorCallback) {
    var memthodName = 'deleteUser'
    Vue.http.delete(window.API_GATEWAY_URL + '/api/aggregation/users/' + args.userId).then(response => {
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
    getUserList,
    getUserById,
    editUser,
    addUser,
    deleteUser
}