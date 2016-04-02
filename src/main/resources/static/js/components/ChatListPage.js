var ChatListPage = React.createClass({
    mixins: [Reflux.connect(ChatStore, 'chatList')],
    getInitialState : function () {
        this.setState({chatList : undefined});
    },
    componentDidMount : function() {

    },
    render : function() {
        if (this.state.chatList == undefined) {
            return <div> Loading </div>
        } else {
            var chatList = this.state.chatList.map(function(chatGroup) {
                return (
                    <tr key={chatGroup.id}>
                        <td>{chatGroup.chatName}</td>
                        <td>{chatGroup.lastUpdated}</td>
                        <td><a href=P{"/chat/group/" + chatGroup.id}>Go to chat</a></td>
                    </tr>
               );
            });
            return(<div>
                <h1>Chat Groups</h1>
                <table>
                    <tbody>
                        <tr>
                            <td>Chat Name</td>
                            <td>Last Updated</td>
                            <td>Actions</td>
                        </tr>
                    </tbody>
                </table>
            </div>);
        }
    }
});