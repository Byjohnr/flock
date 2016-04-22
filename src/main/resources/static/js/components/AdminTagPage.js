var AdminTagPage = React.createClass({
   mixins: [Reflux.connect(TagStore, 'tags')],
    getInitialState : function() {
        return {tags : undefined}
    },
    componentDidMount : function() {
        TagActions.getAllTags();
    },
    toggleTag : function(tagId) {
        TagActions.toggleTag(tagId);
        var buttonName = $("#" + tagId + "btn").html();
        if (buttonName == "Disable") {
            $("#" + tagId + "btn").removeClass('btn-warning').addClass('btn-success').html('Enable');
        } else {
            $("#" + tagId + "btn").removeClass('btn-success').addClass('btn-warning').html('Disable');

        }
        console.log(buttonName);
    },
    deleteTag : function(tagId) {
        console.log('DELETE');
        console.log(tagId);
    //    TODO jeffreyh 4/21/16 add a delete confirmation modal someday in the future
        TagActions.deleteTag(tagId);
    },
    addTag: function() {
        var tagName = $('#tag_name').val();
        var isEnabled = $('#tag_enabled').val();
        console.log(isEnabled);

        TagActions.addTag(tagName, isEnabled);
    },
    editTag : function(tagId, tagName) {
        $('#editModal').modal('show');
        this.setState({tagId : tagId});
        $('#tagName').val(tagName);
    },
    editTagAction : function() {
        var tagName = $('#tagName').val();
        TagActions.updateTagName(this.state.tagId, tagName);
        $('#editModal').modal('hide');
    },
    render : function() {
        if (this.state.tags === undefined) {
            return <div>Loading</div>;
        } else {
            var tags = this.state.tags.map(function(tag) {
                var toggleButton;
                if(tag.enabled) {
                    toggleButton = <button className="btn btn-warning" id={tag.id + "btn"} onClick={this.toggleTag.bind(this, tag.id)}>Disable</button>
                } else {
                    toggleButton = <button className="btn btn-success" id={tag.id + "btn"} onClick={this.toggleTag.bind(this, tag.id)}>Enable</button>
                }
               return <tr key={tag.id}>
                   <td>{tag.tagName}</td>
                   <td>
                       {toggleButton}
                       <button className="btn btn-default" onClick={this.editTag.bind(this, tag.id, tag.tagName)}>Edit Name</button>
                       <button className="btn btn-danger" onClick={this.deleteTag.bind(this, tag.id)}>Delete</button>
                   </td>
               </tr>
            }, this);

            return <div>
                <NavBar />
                <div className="modal fade" id="editModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 className="modal-title" id="myModalLabel">Edit Tag Name</h4>
                            </div>
                            <div className="modal-body">
                                <div className="form-group">
                                    <label className="control-label" htmlFor="tagName">Name of Tag</label>
                                    <input id="tagName" className="form-control" type="text" />
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" className="btn btn-primary" onClick={this.editTagAction}>Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="form-inline">
                    <div className="form-group">
                        <label htmlFor="tag_name">Tag Name</label>
                        <input className="form-control" id="tag_name" placeholder="tag name" type="text"/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="tag_enabled">
                            <input classNames="form-control" id="tag_enabled" type="checkbox"/>
                            Enabled </label>
                    </div>
                    <button className="btn btn-default" onClick={this.addTag}> Add Tag</button>
                </div>
                <table className="table table-condensed table-striped">
                    <tbody>
                    <tr>
                        <th>Name of Tag</th>
                        <th>Actions</th>
                    </tr>
                    {tags}
                    </tbody>
                </table>
            </div>
        }
    }
});
ReactDOM.render(<AdminTagPage />, document.getElementById('tag_content'));