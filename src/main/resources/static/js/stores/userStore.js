/**
 * Created by jeffrey on 2/8/16.
 */
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
            success: this.handleSuccess,
            error: function () {
                console.log("error bruh");
            }
        });
    },
    getUserInformation: function (data) {
        console.log("getting user information with data:");
        console.log(data);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/user/get/' + data,
            type: 'GET',
            success: function (result) {
                returnInformationSuccess(result);
            },
            error: function () {
                console.log("error getting user information from the backend");
            }
        });
    },
    returnInformationSuccess(result) {
        this.trigger(result);
        //ViewAccount.setState();
    },
    handleSuccess: function () {
        console.log('success');
        this.trigger('yoloswag');
    }
});