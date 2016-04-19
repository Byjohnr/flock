React.createClass({
   mixins: [Reflux.connect(TagStore, 'tags')],
    getInitialState : function() {
        return {tags : undefined}
    },
    componentDidMount : function() {
        TagActions.getAllTags();
    },
    render : function() {
        if (this.state.tags === undefined) {
            return "";
        } else {
            var tags = this.state.tags.map(function(tag) {
                var toggleButton;
                if(tag.enabled) {
                    toggleButton = <button>Disable</button>
                } else {
                    toggleButton = <button>Enable</button>
                }
               return <tr key={tag.id}>
                   <td>{tag.tagName}</td>
                   <td>
                       <button>Edit Name</button>
                       <button>Delete</button>
                   </td>
               </tr> 
            });
        }
    }
});