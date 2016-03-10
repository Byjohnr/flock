var PictureStore = Reflux.createStore({
    listenables: [PictureActions],
    onSavePicture: function (toUpload) {
        console.log("toUpload: " + toUpload);
        //var reader = new FileReader();
        //var toUpload = e.target.files[0];
        //reader.onload = function (upload) {
        //    var fileDataUrl = upload.target.result;
        //    alert("File data url: " + fileDataUrl.toString());
        //};
        //reader.readAsDataURL(toUpload);
        $.ajax({
            //headers: {
            //    'Accept': 'application/json',
            //    'Content-Type': 'application/json'
            //},
            url: '/api/picture_upload/profile_picture',
            dataType: 'json',
            method: 'POST',
            data: {
                pictureData: toUpload
            },
            success: function (file) {
                alert("success");
                this.handleFile
            }
            ,
            error: function () {
                console.log("There was an error uploading the picture to the server.");
            }
        })
        ;
    },
    handleFile: function (file) {
        alert("handleFile file: " + file);
        this.trigger(file);
    }
});