// import React from "react";
// import { render } from "react-dom";
// import { Provider } from "react-redux";
// import store from "./store/store";
// import App from "./App";


// ReactDOM.render(
//   <Provider store={store}>
//     <App />
//   </Provider>, 
//   document.getElementById('root')
// );





//Working version
import React from 'react';
import ReactDOM from 'react-dom';
import './css/index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

ReactDOM.render(
    <App />,
        document.getElementById('react-mountpoint')
    );
    
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();


// import React, { Component } from "react";
// import ReactDOM from 'react-dom';
// import '../css/Main.css';

// class Main extends Component {
//     constructor(props) {
//         super(props);
//         this.state = {message: ''};
//     }

//     componentDidMount() {

//         useEffect(() => {
//           fetch('/sayhello?name=Java')
//             .then((res) => res.json())
//             .then((res)=>  this.setState({message: res.data}));
//         }, []);
        
//       }

//     render() {
//         return (
//             <div id="main">
//                 <h1>Message : {this.state.message}</h1>
//                 <img src="https://upload.wikimedia.org/wikipedia/commons/a/a7/React-icon.svg"/>
//             </div>
//         );
//     }
// }

// ReactDOM.render(
//     <Main />,
//     document.getElementById('react-mountpoint')
// );
