var MainPage = React.createClass({
    mixins: [Reflux.connect(UserStore,'user')],
   render: function() {
       //TODO jeffreyh 2/8/16 still need to create the anonymous user part of the main page
       if(this.state.user != undefined) {

       }
       return(

         <div className="row">
            <div className="col-sm-7"></div>
             <div className="col-sm-5">
                 <RegisterUser />
             </div>
         </div>
       );
   }
});

ReactDOM.render(<MainPage />, document.getElementById("main_page"));