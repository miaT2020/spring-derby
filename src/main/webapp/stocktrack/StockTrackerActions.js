// StockTrackActions.js

import {CREATE_NEW_CONTACT, REMOVE_CONTACT} from './StockActionTypes';

export const createContact = (contact) => {
    return {
      type: CREATE_NEW_CONTACT,
      contact: contact
    }
  };

export const deleteContact = (id) => {
    return {
        type: REMOVE_CONTACT,
        id: id
    }
}