import React, { Component } from 'react';
import { Card, Table } from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList} from '@fortawesome/free-solid-svg-icons'
import axios from 'axios';

class ContractComponent extends Component {
    constructor(props){
        super(props);
        this.state = {
            contracts : []
        };
    }

    componentDidMount(){
        this.getContracts().then(response => response.data)
        .then((data) => {
           this.setState({contracts: data})
        });
     }

      getContracts(){
        return axios.get('http://localhost:8080/step4/contracts')
     }

    
    render() {
        return (
            <div>
               <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header><FontAwesomeIcon icon={faList} /> Contracts</Card.Header>
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <thead>
                                <tr>
                                <th>Contract Id</th>
                                <th>Contract End Date</th>
                                <th>Contract Feature Number 1</th>
                                <th>Contract Feature Number 2</th>
                                <th>Contract Feature Number 3</th>
                                <th>Contract IP1</th>
                                <th>Contract IP2</th>
                                <th>Contract IP3</th>
                                <th>Contract License Key</th>
                                <th>Contract Start Date</th>
                                </tr>
                            </thead>
                            <tbody>
                            {this.state.contracts.length === 0 ?
                  <tr align="center">
                  <td colSpan="6">No Contracts Available</td>
              </tr> :
                   this.state.contracts.map(
                       contract =>
                       <tr key = {contract.id}>
                           <td> {contract.id}</td>
                           <td> {contract.enddate}</td>
                           <td> {contract.featurenumber1}</td>
                           <td> {contract.featurenumber2}</td>
                           <td> {contract.featurenumber3}</td>
                           <td> {contract.ip1}</td>
                           <td> {contract.ip2}</td>
                           <td> {contract.ip3}</td>
                           <td> {contract.licenseKey}</td>
                           <td> {contract.startdate}</td>
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

export default ContractComponent;