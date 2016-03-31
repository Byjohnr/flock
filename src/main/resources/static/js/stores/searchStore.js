var SearchStore = Reflux.createStore({
   listenables : [SearchActions],
    onSearch : function(query) {
        if(query == undefined || query == "") {
            query = " ";
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url : '/api/home/search',
            type: 'POST',
            data : query,
            dataType: 'json',
            success: this.triggerState,
            error : function() {
                console.log("ya dun goofed");
            }
        });
    },
    triggerState : function(searchResults) {
        this.trigger(searchResults);
        console.log(searchResults);
    }


});