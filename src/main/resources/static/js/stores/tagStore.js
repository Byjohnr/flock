var TagStore = Reflux.createStore({
   listenables: [TagActions],
    init : function() {
      this.tags = [];
    },
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
            success: this.updateStoreState
        })
    },
    onAddTag : function(tagName, enabled) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/tag/add',
            data: {tagName: tagName, enabled : enabled},
            success : this.addToStoreState
        });
    },
    onUpdateTagName : function(tagId, tagName) {
        $.ajax({
            url : '/api/tag/update/' + tagId,
            data : tagName,
            success : this.updateTag
        });
    },
    onToggleTag : function(tagId) {
        
    },
    onDeletetag : function(tagId) {
        
    },
    updateTag : function(updatedTag) {
        var newList = this.tags.filter(function(tag) {
            return tag.id != updatedTag.id;
        });
        newList.push(updatedTag);
        this.tags = newList;
        this.handleTrigger(this.tags);
    },
    updateStoreState : function(tags) {
        this.tags = tags;
        this.handleTrigger(this.tags);
    },
    addToStoreState : function(tag) {
        this.tags.push(tag);
        this.handleTrigger(this.tags);
    },
    handleTrigger : function(data) {
        this.trigger(data);
    }
});