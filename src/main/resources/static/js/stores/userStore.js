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
    onGetUserInfo : function() {
        $.ajax({
            url: '/api/user/info',
            dataType:'json',
            success : this.handleUserInfo
        })
    },
    handleUserInfo: function(data) {
        console.log(data);
        this.trigger(data);
    },
    onGetUserInformation: function (data) {
        console.log("getting user information with data:");
        console.log(data);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/user/get/' + data,
            type: 'GET',
            success: this.returnInformationSuccess,
            error: function () {
                console.log("error getting user information from the backend");
            }
        });
    },
    returnInformationSuccess: function (data) {
        this.trigger(data);
    }
});