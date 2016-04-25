var TagList = React.createClass({
    mixins : [Reflux.connect(TagStore, 'tags')],
    getInitialState() {
        return {tags : undefined};
    },
    componentDidMount : function() {
         TagActions.getTags();
    },
    render : function() {
        if (this.state.tags === undefined) {
            return <select className="form-control">

            </select>
        } else {
            var tags = this.state.tags.map(function(tag) {
               return <option key={tag.id} value={tag.id}>{tag.tagName}</option>
            });
        }
        return <select className="form-control"  ref={(ref) => this.tagId = ref}>
            <option value="">Select a Tag</option>
            {tags}
        </select>
    }
});