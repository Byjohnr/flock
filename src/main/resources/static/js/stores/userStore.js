var UserStore = Reflux.createStore({
    listenables: [UserActions],
    onCreateUser : function(data) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/user/create',
            dataType: 'text',
            type: 'POST',
            data: JSON.stringify(data),
            success: this.createUserSuccess,
            error: function () {
                console.log("Error creating user");
            }
        });
    },
    createUserSuccess: function () {
        this.trigger('success');
    },
    returnInformationSuccess: function (data) {
        this.trigger(data);
    },
    onGetUserInformation: function () {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/user/info',
            type: 'GET',
            success: this.returnInformationSuccess,
            error: function () {
                console.log("Error getting user information");
            }
        });
    },
    onGetOtherUserInfo: function () {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            url: '/api/user/' + id,
            dataType: 'json',
            success: this.returnInformationSuccess
        });
    },
    onGetAllUsers: function () {
        $.ajax({
            url: '/api/admin/list_all_users',
            dataType: 'json',
            success: this.returnInformationSuccess
        });
    }
});