/**
 * Created by chasekoehler on 2/24/16.
 */
var InviteList = React.createClass({
    render: function() {
        var symbol = this.props.invites.map(function (invite) {
            if (invite.inviteStatus === 1){
                return (<span key={invite.id} className="fa fa-check"/>);
            }
            if (invite.inviteStatus === 2) {
                return (<span key={invite.id} className="fa fa-question"/>);
            }
            if (invite.inviteStatus === 3) {
                return (<span key={invite.id} className="fa fa-times"/>);
            }
        });
        var inviteNodes = this.props.invites.map(function (invite) {
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