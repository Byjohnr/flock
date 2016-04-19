var TagStore = Reflux.createStore({
   listenables: [TagActions],
    onGetTags : function() {
        $.ajax({
            url : '/api/tag/get',
            dataType: 'json',
            success : this.handleTrigger
        });
    },
    onGetAllTags : function() {
        $.ajax({
            url : '/api/tag/get/all',
            dataType : 'json',
            success: this.handleTrigger
        })
    },
    onAddTag : function(tagName) {
        
    },
    onUpdateTagName : function(tagId, tagName) {
        
    },
    onToggleTag : function(tagId) {
        
    },
    onDeletetag : function(tagId) {
        
    },
    handleTrigger : function(data) {
        this.trigger(data);
    }
});