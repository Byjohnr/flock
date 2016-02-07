var CreateEventRightCol = React.createClass({
   // TODO jeffreyh 2-6-16 added photo upload when Thomas gets it working, map when John gets it working
   render: function() {
       return <div>

           RIGHT SIDE!
           <div className="form-horizontal">
               <div className="form-group">
                   <div>
                       <label className="col-sm-2 control-label" htmlFor="address">Address</label>
                   </div>
                   <div className="col-sm-8">
                       <input id="address" className="form-control" rows="3" ref={(ref) => this.address = ref}/>
                   </div>
               </div>
           </div>
       </div>
   }
});