var ChatStore = Reflux.createStore({
    listenables: [ChatActions],
    onGetChats: function() {
        $.ajax({
            url: '/api/chat/list',
            dataType: 'json',
            success : this.triggerUpdate
        });
    },
    onHandleResponse(chatUserId, response) {

        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/chat/' + chatUserId + '/respond',
            data: response,
            type: 'POST',
            success: function() {
                console.log('success');
                window.location.reload(true);
            }
        });
    },
    onCreateGroup(groupName) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url : '/api/chat/group/create',
            data: groupName,
            type: 'POST',
            success : function() {
                console.log('success');
                window.location.reload(true);
            }
        });
    },
    triggerUpdate : function(data) {
        this.trigger(data);
    }
});