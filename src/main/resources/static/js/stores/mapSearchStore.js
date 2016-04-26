var MapSearchStore = Reflux.createStore({
    listenables: [SearchActions],
    onSearch : function() {
        $.ajax({
            url : '/api/map/search',
            type: 'POST',
            dataType: 'json',
            success : this.handleTrigger
        });
    },
    handleTrigger : function(data) {
        this.trigger(data);
    }
});