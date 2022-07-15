import {combineReducers} from 'redux';
import thunk from 'redux-thunk';

import rootReducers from './rootReducer';

const {configureStore} =

    require('./store');

export default () =>
  configureStore(
    combineReducers({
      pros: rootReducers,
    }),
    [thunk],
  );
