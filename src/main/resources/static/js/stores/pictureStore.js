var PictureStore = Reflux.createStore({
    listenables: [PictureActions],
    onSavePicture: function (toUpload, uploadURL) {
        console.log("toUpload before ajax: " + toUpload.toString());
        $.ajax({
            url: uploadURL,
            method: 'POST',
            data: {
                picture: toUpload
            },
            success: this.handleFile,
            error: function () {
                console.log("There was an error uploading the picture to the server.");
            }
        });
    },
    handleFile: function (data) {
        this.trigger(data);
    }
});