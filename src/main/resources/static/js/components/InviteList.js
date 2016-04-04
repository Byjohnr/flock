var InviteList = React.createClass({
    render: function() {
        var symbol;
        var inviteNodes = this.props.invites.map(function (invite) {
            if (invite.inviteStatus === 1){
                symbol = (<span key={invite.id} className="fa fa-check"/>);
            }
            else if (invite.inviteStatus === 2) {
                symbol = (<span key={invite.id} className="fa fa-question"/>);
            }
            else if (invite.inviteStatus === 3) {
                symbol = (<span key={invite.id} className="fa fa-times"/>);
            }
            else {
                symbol = <span key={invite.id}/>
            }
            return <p key={invite.id}>{invite.userInvited.firstName} {invite.userInvited.lastName} {symbol}</p>
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