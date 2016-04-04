var ChatListPage = React.createClass({
    mixins: [Reflux.connect(ChatStore, 'chatList')],
    getInitialState : function () {
        return({chatList : undefined, groupName: undefined});
    },
    componentDidMount : function() {
        ChatActions.getChats();
    },
    handleAccept : function(chatUserId) {
        ChatActions.handleResponse(chatUserId, 'accept');
    },
    handleRejectOrDelete : function(chatUserId) {
        ChatActions.handleResponse(chatUserId, 'remove');
    },
    handleNameChange: function(input) {
        this.setState({groupName : input.target.value})
    },
    createGroup : function() {
        ChatActions.createGroup(this.state.groupName);
    },
    render : function() {
        if (this.state.chatList == undefined) {
            return <div> Loading </div>
        } else {
            var chatList = this.state.chatList.map(function(chatUser) {
                var actions;
                if (chatUser.status == 1) {
                    actions = (
                        <td>
                            <button className="btn btn-success" onClick={this.handleAccept.bind(this, chatUser.id)}>Accept Chat Invite</button>
                            <button className="btn btn-danger" onClick={this.handleRejectOrDelete.bind(this, chatUser.id)}>Reject Chat Invite</button>
                        </td>
                    );
                } else if (chatUser.status == 2) {
                    actions = (
                        <td>
                            <a className="btn btn-default" href={"/chat/group/" + chatUser.chatGroup.id}>Go to chat</a>
                            <button className="btn btn-danger" onClick={this.handleRejectOrDelete.bind(this, chatUser.id)}>Remove Chat</button>
                        </td>
                    );
                }
                return (
                    <tr key={chatUser.chatGroup.id}>
                        <td>{chatUser.chatGroup.chatName}</td>
                        <td>{chatUser.chatGroup.lastUpdated}</td>
                        {actions}
                    </tr>
               );
            }, this);
            return(
                <div>
                <NavBar/>
                <h1>Chat Groups</h1><button className="btn btn-primary" data-toggle="modal" data-target="#createGroupModal">Create New Chat Group</button>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td>Chat Name</td>
                            <td>Last Updated</td>
                            <td>Actions</td>
                        </tr>
                        </thead>
                    <tbody>
                        {chatList}
                    </tbody>
                </table>
                    <div className="modal fade" id="createGroupModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div className="modal-dialog" role="document">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 className="modal-title" id="myModalLabel">Create Chat Group</h4>
                                </div>
                                <div className="modal-body">
                                    <div className="form-group">
                                        <label className="control-label" htmlFor="groupName">Name of Chat Group</label>
                                        <input id="groupName" className="form-control" type="text" value={this.state.groupName} onChange={this.handleNameChange}/>
                                    </div>
                                </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" className="btn btn-primary" onClick={this.createGroup}>Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>);
        }
    }
});
ReactDOM.render(<ChatListPage />, document.getElementById("chat_list"));