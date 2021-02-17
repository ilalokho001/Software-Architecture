//import logo from './logo.svg';
import React from 'react';
import './App.css';


import {Container, Row, Col} from 'react-bootstrap';

import NavigationBar from './Components/NavigationBar';
import Welcome from './Components/Welcome';
import UserComponent from './Components/User/UserComponent';
import CustomerComponent from './Components/CustomerComponent';
import ContractComponent from './Components/ContractComponent';
import Login from './Components/User/Login'
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

function App() {
const marginTop = { 
  marginTop:"20px"
};

  return (
    <Router>
      <NavigationBar/>
      <Container>
         <Row>
           <Col lg={12} style={marginTop}>
             <Switch>
               <Route path="/" exact component={Welcome}/>
               <Route path="/User" exact component={UserComponent}/>
               <Route path="/Customer" exact component={CustomerComponent}/>
               <Route path="/Contract" exact component={ContractComponent}/>
              <Route path="/login" exact component={Login}/>
              <Route path="/logout" exact component={Login }/>
             </Switch>
           </Col>
         </Row>
      </Container>
    </Router>
  );
}

export default App;
