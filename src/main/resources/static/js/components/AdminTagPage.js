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

    },
    editTag : function(tagId) {

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
                       <button onClick={this.editTag.bind(this, tag.id)}>Edit Name</button>
                       <button onClick={this.deleteTag.bind(this, tag.id)}>Delete</button>
                   </td>
               </tr>
            }, this);

            return <div>
                <NavBar />

                <div class="form-horizontal">
                    <div class="form-group">
                        <label htmlFor="tag_name">Tag Name</label>
                        <input id="tag_name" placeholder="tag name" type="text"/>
                    </div>
                    <div class="check-box">
                        <label htmlFor="tag_enabled">
                            <input id="tag_enabled" type="checkbox"/>
                            Enabled</label>
                    </div>
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