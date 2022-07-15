import React, { useEffect, useState } from 'react';
import "./css/App.css";


function App() {
  const [data, setData] = useState(null);
 
  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(`/stocktrack/timeseries`);
      const newData = await response.json();
      setData(newData);
      console.log(newData)
    };

    fetchData();
  });

  // React.useEffect(() => {
  //   fetch('/stocktrack/sayhello?name=Java')
  //     .then((res) => res.json())
  //     .then((data) => setData(data));
  // }, []);

  return (
    <div className="App">
      <header className="App-header">
      
        <p>{!data ? "Loading..." : ""}</p>
        <h1 className = "text-center"> Time Series</h1>

            <table className = "table table-striped">
                <thead>
                    <tr>
                        <th>Ticker</th>
                        <th> Date/Time</th>
                        <th> Open</th>
                        <th> Close</th>
                        <th> High</th>
                        <th> Low</th>
                    </tr>

                </thead>
               {data && (<tbody>
                    {
                        data.map(
                                row =>
                                <tr key = {row.id}>
                                    <td> {row.ticker }</td>
                                    <td> {row.date }</td>
                                    <td> {row.open }</td>
                                    <td> {row.close }</td>    
                                    <td> {row.high }</td>
                                    <td> {row.low }</td>
                                </tr>

                        )
                    }

                </tbody>)}


            </table>
      </header>
    </div>
  );
}

 export default App;

// import React, { Component } from 'react';
// import { connect } from 'react-redux';
// import * as contactAction from './actions/contactAction';

// class App extends Component {

//   constructor(props){
//     super(props);
//     this.handleChange = this.handleChange.bind(this);
//     this.handleSubmit = this.handleSubmit.bind(this);
     
//     this.state = {
//       name: ''
//     }
//   }

//   handleChange(e){
//     this.setState({
//       name: e.target.value
//     })
//   }

//   handleSubmit(e){
//     e.preventDefault();
//     let contact = {
//       name: this.state.name
//     }
//     this.setState({
//       name: ''
//     });
//     this.props.createContact(contact);
//   }

//   listView(data, index){
//     return (
//       <div className="row">
//         <div className="col-md-10">
//           <li key={index} className="list-group-item clearfix">
//             {data.name}
//           </li>
//         </div>
//         <div className="col-md-2">
//           <button onClick={(e) => this.deleteContact(e, index)} className="btn btn-danger">
//             Remove
//           </button>
//         </div>
//     </div> 
//     )
//   }

//   deleteContact(e, index){
//     e.preventDefault();
//     this.props.deleteContact(index);
//   }

//   render() {

//     return(
//       <div className="container">
//         <h1>Clientside Contacts Application</h1>
//         <hr />
//         <div>
//           <h3>Add Contact Form</h3>
//           <form onSubmit={this.handleSubmit}>
//             <input type="text" onChange={this.handleChange} className="form-control" value={this.state.name}/><br />
//             <input type="submit" className="btn btn-success" value="ADD"/>
//           </form>
//           <hr />
//         { <ul className="list-group">
//           {this.props.contacts.map((contact, i) => this.listView(contact, i))}
//         </ul> }
//         </div>
//       </div>
//     )
//   }
// }

// const mapStateToProps = (state, ownProps) => {
//   return {
//     contacts: state.contacts
//   }
// };

// const mapDispatchToProps = (dispatch) => {
//   return {
//     createContact: contact => dispatch(contactAction.createContact(contact)),
//     deleteContact: index =>dispatch(contactAction.deleteContact(index))
//   }
// };

// export default connect(mapStateToProps, mapDispatchToProps)(App);




// import React, {Component} from 'react';
// import "./css/App.css";

// class App extends Component {
//     constructor(props) {
//         super(props);
//         this.state = {message: ''};
//     }

//     componentDidMount() {

//       React.useEffect(() => {
//         fetch('/sayhello?name=Java')
//           .then((res) => res.json())
//           .then((res)=>  this.setState({message: res.data}));
//       }, []);
      
//     }

//     render() {
//         return (
//             <div className="App">
//                <header className="App-header">
//                 <img src={logo} className="App-logo" alt="logo" />
//                 <p> Message : {this.state.message}</p>
//               </header>
               
//             </div>
//         );
//     }
// }

// export default App;