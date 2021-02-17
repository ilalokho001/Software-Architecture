import React, { Component } from 'react';
import {connect} from 'react-redux';
import {fetchUsers} from '../../Services/User/userActions';

import { Card, Table, Alert } from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList} from '@fortawesome/free-solid-svg-icons'
//import axios from 'axios';

//const USERS_REST_API_URL = 'http://localhost:8080/step4/users';

class UserComponent extends Component {

    constructor(props){
        super(props);
        this.state = {
            users:[]
        };
    }

    componentDidMount(){
        // this.getUsers().then(response => response.data)
        // .then((data) => {
        //    this.setState({users: data})
        // });
        this.props.fetchUsers();
     }



    //  getUsers(){
    //     return axios.get(USERS_REST_API_URL)
    //  }

    render() {
         const userData = this.props.userData;
         const users = userData.users;
        return (
            <div>
                {userData.error ?
                <Alert variant="danger">
                    {userData.error}
                </Alert> :
                <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header><FontAwesomeIcon icon={faList} /> Users</Card.Header>
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <thead>
                                <tr>
                                <th>User Id</th>
                                <th>User Email Id</th>
                                <th>User Username</th>
                                <th>User Password</th>
                                <th>User First Name</th>
                                <th>User Last Name</th>
                                {/* <th>Actions</th> */}
                                {/*  <th>User Admin</th>  */}  
                                </tr>
                            </thead>
                            <tbody>
                            {users.length === 0 ?
                  <tr align="center">
                  <td colSpan="6">No Users Available</td>
              </tr> :
                   users.map(
                       user =>
                       <tr key = {user.id}>
                           <td> {user.id}</td>
                           <td> {user.email}</td>
                           <td> {user.username}</td>
                           <td> {user.password}</td>
                           <td> {user.firstName}</td>
                           <td> {user.lastName}</td>
                           {/* <td> {user.isAdmin}</td> */}
                       </tr>
                   )
               }
                            </tbody>
                        </Table>
                    </Card.Body>
                </Card>
                }    
            </div>
        );
    }
}

const mapStateToProps = state => {
    return {
        userData: state.user
    }
};

const mapDispatchToProps = dispatch => {
    return {
        fetchUsers: () => dispatch(fetchUsers())
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(UserComponent);

//export default UserComponent;