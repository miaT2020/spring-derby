// import { configureStore } from '@reduxjs/toolkit'

// import stockTrackReducer from '../StockTrackReducer'

// const store = configureStore({
//   reducer: {
//     // Define a top-level state field named `todos`, handled by `todosReducer`
//     stockManager: stockTrackReducer,
//   }
// })

// export default store



import {applyMiddleware, compose} from 'redux';
import { configureStore } from '@reduxjs/toolkit'

import {createLogger} from 'redux-logger';

 const store = (rootReducer, middlewares) =>
configureStore(
    rootReducer,
    compose(
      applyMiddleware(...[createLogger(), ...middlewares]),
      window.devToolsExtension ? window.devToolsExtension() : f => f,
    ),
  );


  export default store;