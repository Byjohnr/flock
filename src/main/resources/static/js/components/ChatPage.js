var ChatPage = React.createClass({
    mixins : [Reflux.connect(ChatStore, 'chatGroup')],
    getInitialState : function() {
        return {chatGroup : undefined, message : undefined};
    },
    componentDidMount : function() {
        ChatActions.getChatGroup();
        $("#message_btn").attr("disabled", true);
        //stompClient.register([
        //    {route: '/topic/newMessage', callback: this.refreshCurrentPage}
        //]);
        this.connect();
    },
    connect : function() {
        var chatId = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);

        var handleReturn = this.handleReturn;
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            //stompClient.setConnected(true);
            console.log(frame.headers);
            console.log(frame.headers['user-name']);
            stompClient.subscribe('/topic/message/' + chatId, function(message) {
                console.log("HIT!");
                handleReturn(JSON.parse(message.body));
            });
        })
    },
    handleReturn : function(message) {
        var newList = this.state.chatGroup.chatMessages;
        var newState = this.state.chatGroup;
        newList.push(message);
        newState.chatMessages = newList;
        this.setState({chatGroup: newState});
        var elem = document.getElementById('chat_area');
        elem.scrollTop = elem.scrollHeight;
    },
    handleMessage : function(e) {
        var chatId = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);

        if(e.keyCode == undefined || e.keyCode == 13 && this.state.message != "") {
            stompClient.send("/add/" + chatId, {}, JSON.stringify({message : this.state.message}));
            //ChatActions.sendMessage(this.state.message);
            $("#message_btn").attr("disabled", true);
            $("#chat_input").val('');
            this.setState({message: ''});
        }
    },
    handleInvite : function(connection) {
        ChatActions.inviteConnection(connection.id);
    },
    handleChange : function(input) {
        if(input.target.value != "") {
            $("#message_btn").attr("disabled", false);
        } else {
            $("#message_btn").attr("disabled", true);
        }
        this.setState({message : input.target.value});
    },
    handleNameChange: function(input) {
        var newState = this.state.chatGroup;
        newState.chatName = input.target.value;
        this.setState({chatGroup : newState})
    },
    changeGroupName : function() {
      ChatActions.editGroupName(this.state.chatGroup.chatName);
    },
    render : function() {
        if(this.state.chatGroup === undefined) {
            return <div>Loading...</div>
        } else {
            var messages = this.state.chatGroup.chatMessages.map(function(message) {
                return (
                    <div key={message.id}>
                        <p><strong>
                            {message.messageSender.user.firstName} {message.messageSender.user.lastName}:
                        </strong>
                            &nbsp;{message.message}
                        </p>
                    </div>
                );
            });
            var users = this.state.chatGroup.chatUsers.map(function(user) {
                var userAdded = <td> </td>;
                if(user.status == 1) {
                    userAdded = <td>Invited</td>
                }
                console.log(user);
                console.log(user.user.email);
                return (
                    <tr key={user.id}>
                        <td>{user.user.firstName} {user.user.lastName}</td>
                        {userAdded}
                    </tr>
                );
            });
            var modal = <div id="chatNameModal" className="modal fade" tabIndex="-1" role="dialog">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 className="modal-title">Chat Group Name</h4>
                        </div>
                        <div className="modal-body">
                            <div className="form-group">
                                <label className="control-label" htmlFor="groupName">Name of Connection Group</label>
                                <input id="groupName" className="form-control" type="text" value={this.state.chatGroup.chatName} onChange={this.handleNameChange}/>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-primary" data-dismiss="modal" onClick={this.changeGroupName}>Save Group</button>
                        </div>
                    </div>
                </div>
            </div>;
            return(
                <div>
                    <NavBar />
                    <h2 className="text-center">{this.state.chatGroup.chatName}
                        &nbsp;<button className="btn btn-primary" data-toggle="modal" data-target="#chatNameModal">Edit Group Name</button>
                    </h2>
                    {modal}
                    <div className="container">
                        <div className="row">
                            <div id="chat" className="col-md-8">
                                <div id="chat_area">
                                    {messages}
                                </div>
                                <div id="chat_bar">
                                    <input id="chat_input" className="form-control" type="text"
                                           onKeyDown={this.handleMessage} onChange={this.handleChange} />
                                    <button id="message_btn" className="btn btn-success" onClick={this.handleMessage}>
                                        Send
                                    </button>
                                </div>
                            </div>
                            <div className="col-md-3">
                                <div className="panel panel-default">
                                    <div className="panel-heading">Chat Group Users</div>
                                    <table className="table">
                                        <tbody>
                                        {users}
                                        </tbody>
                                    </table>
                                </div>
                                <ConnectionList actionId="connection" actionName="Invite to Chat"
                                                buttonName="Invite to Chat" modalId="chatInvite"
                                                handleInvite={this.handleInvite} type="chat" />
                            </div>
                        </div>
                    </div>
                </div>
            )
        }
    }
});
ReactDOM.render(<ChatPage />, document.getElementById('group_chat'));