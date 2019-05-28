import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class ClientsAddressesList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            addresses: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <table border="1">
                {
                    this.createAddresses()
                }
            </table>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/client/" + this.props.match.params.id + "/addresses")
            .then(value => {
                this.setState({
                    addresses: value.data,
                    isLoading: false
                })
            })
            .catch(reason => {
                this.setState({
                    error: reason,
                    isLoading: false
                })
            });
    }

    createAddresses = () => {
        let addressesTable = [];

        for (let i = 0; i < this.state.addresses.length; i++) {
            let address = this.state.addresses[i];

            addressesTable.push(
                <tr>
                    <td>{address.name}</td>
                    <td>{address.street}</td>
                    <td>{address.city}</td>
                    <td>{address.state}</td>
                    <td>{address.postal_code}</td>
                    <td>{address.id_country}</td>
                </tr>
            );
        }
        return addressesTable
    }
}