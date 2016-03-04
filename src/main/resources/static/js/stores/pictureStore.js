var PictureStore = Reflux.createStore({
    listenables: [PictureActions],
    savePicture(data) {
        console.log(data);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: 'http://requestb.in/10hi8gy1',
            dataType: 'text',
            type: 'POST',
            data: JSON.stringify(data),
            success: this.handleFile,
            error: function () {
                console.log("There was an error uploading the picture to the server.");
            }
        });
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
        })
    }
});