var RoleStore = Reflux.createStore({
    listenables: [RoleActions],
    init: function () {
        console.log('Init');
    },
    getInitialState() {
        console.log('Initial State');
    },
    onIsEventAdmin() {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            url: '/api/event/isEventAdmin/' + id,
            type: 'GET',
            dataType: 'text',
            success: this.pushRole
        })
    },
    onGetAdminAuthentication: function () {
        $.ajax({
            url: '/api/admin/authentication',
            type: 'GET',
            success: this.pushRole
        });
    },
    onGetUsersAuthenticationLevel: function (userIds) {
        $.ajax({
            url: '/api/admin/authentication_levels',
            dataType: 'text',
            type: 'POST',
            data: {userIds: userIds},
            success: this.pushRole
        });
    },
    onMakeUserAuthenticationLevelAdmin: function (userId) {
        $.ajax({
            url: '/api/admin/authentication/' + userId + '/make_admin',
            dataType: 'text',
            type: 'GET',
            success: function (resultingMessage) {
                console.log(resultingMessage);
            }
        });
    },
    onMakeUserAuthenticationLevelUser: function (userId) {
        $.ajax({
            url: '/api/admin/authentication/' + userId + '/make_user',
            dataType: 'text',
            type: 'GET',
            success: function (resultingMessage) {
                console.log(resultingMessage);
            }
        });
    },
    pushRole (data) {
        this.trigger(data);
    }
});