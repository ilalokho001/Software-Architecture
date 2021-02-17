import React, { Component } from 'react';
import { Card, Table } from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList} from '@fortawesome/free-solid-svg-icons'
import axios from 'axios';

class CustomerComponent extends Component {
    constructor(props){
        super(props);
        this.state = {
            customers : []
        };
    }

    componentDidMount(){
        this.getCustomers().then(response => response.data)
        .then((data) => {
           this.setState({customers: data})
        });
     }

      getCustomers(){
        return axios.get('http://localhost:8080/step4/customers')
     }

    
    render() {
        return (
            <div>
               <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header><FontAwesomeIcon icon={faList} /> Customers</Card.Header>
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <thead>
                                <tr>
                                <th>Customer Id</th>
                                <th>Customer Name</th>
                                <th>Customer Address</th>
                                <th>Customer Phonenumber</th>
                                </tr>
                            </thead>
                            <tbody>
                            {this.state.customers.length === 0 ?
                  <tr align="center">
                  <td colSpan="6">No Customers Available</td>
              </tr> :
                   this.state.customers.map(
                       customer =>
                       <tr key = {customer.id}>
                           <td> {customer.id}</td>
                           <td> {customer.name}</td>
                           <td> {customer.address}</td>
                           <td> {customer.phoneNumber}</td>
                       </tr>
                   )
               }
                            </tbody>
                        </Table>
                    </Card.Body>
                </Card>
            </div>
        );
    }
}

export default CustomerComponent;