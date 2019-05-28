import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class ClientsList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            clients: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <table border="1">
                {
                    this.createClientsTable()
                }
            </table>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/administration/clients")
            .then(value => {
                this.setState({
                    clients: value.data,
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

    createClientsTable = () => {
        let clientsTable = [];

        for (let i = 0; i < this.state.clients.length; i++) {
            let client = this.state.clients[i];

            clientsTable.push(
                <tr>
                    <td>{client.name}</td>
                    <td>{client.surname}</td>
                    <td>{client.telephone}</td>
                    <td>{client.email}</td>
                    <td><a href={"/clients/" + client.id}>Szczegóły</a></td>
                    <td><a href={"/clients/" + client.id + "/addresses"}>Adresy</a></td>
                    <td><a href={"/clients/" + client.id + "/orders"}>Zamówienia</a></td>
                </tr>
            );
        }
        return clientsTable
    }
}