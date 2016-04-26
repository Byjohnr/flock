var Reflux = require('reflux');
var TestActions = require('../actions/TestActions');

var TestStore = Reflux.createStore({
    listenables:[TestActions],
    init: function() {
        this.onFetchUsers();
    },
    onDeleteUser: function(user) {
        $.ajax({
            url:"/test",
            datatype: 'json',
            data: user,
            type: 'POST'
        })
    },
    onFetchUsers: function() {
        $.ajax({
            url:"/test",
            datatype: 'json',
            data: user,
            type: 'GET',
            success: function(data) {
            }
        })
    }
});

module.exports = TestStore;