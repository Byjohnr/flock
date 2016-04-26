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
        console.log(enabled);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/tag/add',
            type: 'POST',
            data: JSON.stringify({tagName: tagName, enabled : enabled == 'on'}),
            success : this.addToStoreState
        });
    },
    onUpdateTagName : function(tagId, tagName) {
        $.ajax({
            url : '/api/tag/update/' + tagId,
            type : 'POST',
            contentType : 'application/json',
            data : tagName,
            success : this.updateTag
        });
    },
    onToggleTag : function(tagId) {
        $.ajax({
            url : '/api/tag/toggle/' + tagId,
            type : 'POST'
        });
    },
    onDeleteTag : function(tagId) {
        console.log('delete tag store');
        $.ajax({
            url : '/api/tag/delete',
            type : 'POST',
            data : JSON.stringify(tagId),
            contentType : 'application/json',
            success : this.removeTagFromList(tagId),
            error : function() {
                console.log('ERR.');
            }
        });
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
    removeTagFromList : function(tagId) {
      this.tags = this.tags.filter(function(tag) {
          return tag.id != tagId;
      }) ;
        this.handleTrigger(this.tags);
    },
    handleTrigger : function(data) {
        this.trigger(data);
    }
});