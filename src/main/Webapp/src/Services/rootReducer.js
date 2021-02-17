  
import {combineReducers} from 'redux';
import userReducer from './User/userReducer';
import authReducer from './User/auth/authReducer'

const rootReducer = combineReducers({
    user: userReducer,
    auth: authReducer
});

export default rootReducer;