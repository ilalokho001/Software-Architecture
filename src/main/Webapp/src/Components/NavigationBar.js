import { faSignInAlt } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React from 'react';
import {connect} from 'react-redux';
import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom';
import {logoutUser} from '../Services/index';

class NavigationBar extends React.Component { 
    logout = () => {
        this.props.logoutUser();
    };

    
    render() { 
        const guestLinks = (
         <>
          <Nav className="navbar-right">
              <Link to={"login"} className="nav-link"><FontAwesomeIcon icon={faSignInAlt}/>Login</Link>
            </Nav>
         </>
      );

      const userLinks = (
        <>
           <Nav className="mr-auto">
              <Link to={"User"} className="navbar-brand">User</Link>
              <Link to={"Customer"} className="navbar-brand">Customer</Link>
              <Link to={"Contract"} className="navbar-brand">Contract</Link>
            </Nav>
            <Nav className="navbar-right">
              <Link to={"logout"} className="nav-link" onClick={this.logout}><FontAwesomeIcon icon={faSignInAlt}/>Logout</Link>
            </Nav>
        </>
     );
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={""} className="navbar-brand">
                User Management
                </Link>
                {this.props.auth.isLoggedIn ? userLinks : guestLinks}
            </Navbar>
        );
        
    };

};

const mapStateToProps = state => {
    return {
        auth: state.auth
    };
};

const mapDispatchToProps = dispatch => {
    return {
        logoutUser: () => dispatch(logoutUser())
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(NavigationBar);

//export default NavigationBar;