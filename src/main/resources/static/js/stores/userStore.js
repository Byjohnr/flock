var UserStore = Reflux.createStore({
    listenables: [UserActions],
    createUser(data) {
        console.log("create user in store");
        console.log(data);
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
                console.log("error bruh");
            }
        });
    },
    createUserSuccess: function() {
        console.log('success');
        this.trigger('yoloswag');
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
                console.log("error getting user information from the backend");
            }
        });
    },
    returnInformationSuccess: function (data) {
        this.trigger(data);
    },
    onGetOtherUserInfo: function() {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            url: '/api/user/' + id,
            dataType:'json',
            success : this.returnInformationSuccess
        });
    }
});