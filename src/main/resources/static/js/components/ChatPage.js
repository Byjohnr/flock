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
        var handleReturn = this.handleReturn;
        var socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            //stompClient.setConnected(true);
            console.log(frame.headers);
            console.log(frame.headers['user-name']);
            //var userName = frame.headers['user-name'].replace("@", "_").replace(".", "_");
            //console.log(userName);
            //$("#chat" + userName).append("(Online)");
            stompClient.subscribe('/topic/message', function(message) {
                console.log("HIT!");
                handleReturn(JSON.parse(message.body));
            });
        })
    },
    handleReturn : function(message) {
        var newList = this.state.chatGroup.chatMessages;
        var newState = this.state.chatGroup;
        console.log(newState);
        newList.push(message);
        console.log(newList);
        newState.chatMessages = newList;
        console.log(newState);
        this.setState({chatGroup: newState});
    },
    handleMessage : function(e) {
        if(e.keyCode == undefined || e.keyCode == 13 && this.state.message != "") {
            stompClient.send("/add", {}, JSON.stringify({message : this.state.message}));
            //ChatActions.sendMessage(this.state.message);
            $("#message_btn").attr("disabled", true);
            $("#chat_input").val('');
            this.setState({message: ''});
            var elem = document.getElementById('chat_area');
            elem.scrollTop = elem.scrollHeight;
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
                //var id = user.user.email.replace("@", "_").replace(".", "_");
                return (
                    <tr key={user.id}>
                        <td>{user.user.firstName} {user.user.lastName}</td>
                        {userAdded}
                    </tr>
                );
            });
            return(
                <div>
                    <NavBar />
                    <h2 className="text-center">{this.state.chatGroup.chatName}</h2>
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