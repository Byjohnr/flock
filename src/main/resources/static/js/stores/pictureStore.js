var PictureStore = Reflux.createStore({
    listenables: [PictureActions],
    onSavePicture: function (pictureData, fileName, uploadURL) {
        $.ajax({
            url: uploadURL,
            method: 'POST',
            data: {
                picture: pictureData,
                fileName: fileName
            },
            success: this.returnPicture,
            error: function () {
                console.log("There was an error uploading the picture to the server.");
            }
        });
    },
    onGetPicture: function (getterURL) {
        $.ajax({
            url: getterURL,
            method: 'GET',
            success: this.returnPicture,
            error: function () {
                console.log("There was an error retrieving the picture from the server.");
            }
        });
    },
    returnPicture: function (data) {
        this.trigger(data);
    }
});