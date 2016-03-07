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
    handleFile: function (file) {
        console.log("file returned");
    }
});