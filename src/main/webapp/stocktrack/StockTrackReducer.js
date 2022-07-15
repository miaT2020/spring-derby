import {CREATE_NEW_CONTACT, REMOVE_CONTACT} from './StockActionTypes';


export default (state = [], action) => {
    switch (action.type){
      case CREATE_NEW_CONTACT:
      return [
        ...state,
        Object.assign({}, action.contact)
      ];
      case REMOVE_CONTACT:
      return state.filter((data, i) => i !== action.id);
      default:
            return state;
    }
  };