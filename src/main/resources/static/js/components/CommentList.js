var CommentList = React.createClass({
    render: function() {
        var commentNodes = this.props.comments.map(function (comment) {
            var date = moment(comment.dateCreated);
            console.log(date);
            return <p key={comment.id}>{comment.owner.firstName}: {comment.comment}, {date.toString()}</p>
        });
        if (commentNodes.length === 0) {
            return (<div className="text-center"> No Comments. Add yours now.</div>);
        }
        return (
            <div>
                {commentNodes}
            </div>);
    }
});