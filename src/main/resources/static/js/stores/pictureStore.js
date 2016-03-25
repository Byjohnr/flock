var PictureStore = Reflux.createStore({
    listenables: [PictureActions],
    onSavePicture: function (toUpload, fileName, uploadURL) {
        console.log("toUpload before ajax: " + toUpload.toString());
        $.ajax({
            url: uploadURL,
            method: 'POST',
            data: {
                picture: toUpload,
                fileName: fileName
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