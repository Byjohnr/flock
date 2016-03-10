var Picture = React.createClass({
    mixins: [Reflux.connect(PictureStore, 'savePicture')],
    getInitialState: function () {
        return {picture: undefined};
    },
    handleFile: function (e) {
        var self = this;
        var reader = new FileReader();
        var file = e.target.files[0];
        console.log("File: " + file);

        reader.onload = function (upload) {
            var fileDataUrl = upload.target.result;
            //alert("File data url: " + fileDataUrl.toString());
            PictureStore.onSavePicture(fileDataUrl);
        };
        reader.readAsDataURL(file);
        this.render();
    },
    render: function () {
        if (this.state.picture !== undefined) {
            return (
                <div className="text-center">
                    <img src={this.state.picture}/>
                    <span className="btn-sm btn-default btn-file text-center">Edit Picture<input type="file"
                                                                                                 multiple={false}
                                                                                                 onChange={this.handleFile}/>
                    </span>
                </div>
            );
        } else {
            return (
                <div className="btn btn-default btn-file -align-center">Upload Picture<input type="file"
                                                                                             multiple={false}
                                                                                             onChange={this.handleFile}/>
                </div>
            )
        }
    }
});

Picture.styles = {
        btn-file: {
    position: relative,
    overflow: hidden
},
 btn-file-input: {
    position: absolute,
    top: 0,
    right: 0,
    min-width: 100%,
    min-height: 100%,
    font-size: 100px,
    text-align: right,
    filter: alpha(opacity=0),
    opacity: 0,
    outline: none,
    background: white,
    cursor: inherit,
    display: block
}
};