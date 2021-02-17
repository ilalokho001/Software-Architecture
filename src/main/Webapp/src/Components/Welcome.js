import React, { Component } from 'react';
import {Jumbotron} from 'react-bootstrap';

class Welcome extends Component {
    render() {
        return (
            <Jumbotron>
            <h1>Welcome!</h1>
            <p>
              This is usermanagement
            </p>
            </Jumbotron>
        );
    }
}

export default Welcome;