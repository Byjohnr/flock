var RoleStore = Reflux.createStore({
    listenables: [RoleActions],
    init: function () {
    },
    getInitialState() {
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
    onGetUsersAuthenticationLevel: function () {
        $.ajax({
            url: '/api/admin/authentication_levels',
            dataType: 'json',
            type: 'GET',
            success: this.pushRole
        });
    },
    onMakeUserAuthenticationLevelAdmin: function (userId) {
        var outerThis = this;
        $.ajax({
            url: '/api/admin/authentication/' + userId + '/make_admin',
            dataType: 'text',
            type: 'GET',
            success: function (resultingMessage) {
                outerThis.onGetUsersAuthenticationLevel();
            }
        });
    },
    onMakeUserAuthenticationLevelUser: function (userId) {
        var outerThis = this;
        $.ajax({
            url: '/api/admin/authentication/' + userId + '/make_user',
            dataType: 'text',
            type: 'GET',
            success: function (resultingMessage) {
                outerThis.onGetUsersAuthenticationLevel();
            }
        });
    },
    pushRole (data) {
        this.trigger(data);
    }
});