var NavStore = Reflux.createStore({
    listenables: [NavActions],
    onGetUserInfo: function () {
        $.ajax({
            url: '/api/user/info',
            dataType: 'json',
            success: this.handleUserInfo
        })
    },
    handleUserInfo: function (data) {
        console.log(data);
        this.trigger(data);
    }
});