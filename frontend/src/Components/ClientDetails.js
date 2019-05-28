import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class ClientDetails extends Component {

    constructor(props) {
        super(props);

        this.state = {
            client: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <div>
                <table>
                    {
                        this.createClientDetails()
                    }
                </table>
            </div>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/client/" + this.props.match.params.id)
            .then(value => {
                this.setState({
                    client: value.data,
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

    createClientDetails = () => {
        let clientDetailsTable = [];

        if (this.state.client.length === 1) {
            let clientDetails = this.state.client[0];

            clientDetailsTable.push(
                <tr>
                    <td>Numer ID:</td>
                    <td>{clientDetails.id}</td>
                </tr>
            );

            clientDetailsTable.push(
                <tr>
                    <td>Imie:</td>
                    <td>{clientDetails.name}</td>
                </tr>
            );


            clientDetailsTable.push(
                <tr>
                    <td>Nazwisko:</td>
                    <td>{clientDetails.surname}</td>
                </tr>
            );

            clientDetailsTable.push(
                <tr>
                    <td>Telefon:</td>
                    <td>{clientDetails.telephone}</td>
                </tr>
            );

            clientDetailsTable.push(
                <tr>
                    <td>Email:</td>
                    <td>{clientDetails.email}</td>
                </tr>
            );
        }

        return clientDetailsTable
    }
}