var PictureStore = Reflux.createStore({
    listenables: [PictureActions],
    onSavePicture: function (toUpload) {
        console.log("toUpload: " + toUpload.toString());
        $.ajax({
            url: '/api/picture_upload/profile_picture',
            method: 'POST',
            dataType: 'text',
            processData: false,
            cache: false,
            contentType: false,
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
        //var self = this;
        //var reader = new FileReader();
        console.log("handleFile file: " + data);
        //reader.onload = function (upload) {
        //    var fileDataUrl = upload.target.result;
        //    console.log("handleFile fileDataUrl: " + fileDataUrl);
        //    self.trigger(fileDataUrl);
        //};
        //reader.readAsDataURL(data);
        this.trigger(data);
    }
});