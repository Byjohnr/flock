var TagStore = Reflux.createStore({
   listenables: [TagActions],
    onGetTags : function() {
        $.ajax({
            url : '/api/tag/get',
            dataType: 'json',
            success : this.handleTrigger
        });
    },
    onAddTag : function(tagName) {
        
    },
    handleTrigger : function(data) {
        this.trigger(data);
    }
});