var InviteList = React.createClass({
    render: function() {
        var inviteNodes = this.props.invites.map(function (invite) {
            return <p key={invite.id}>{invite.userInvited.firstName}, {invite.userInvited.lastName}</p>
        });
        if (inviteNodes.length === 0) {
            return (<div className="text-center"> No users invited to the event.</div>)
        }
        return (
            <div>
                {inviteNodes}
            </div>)
    }
})