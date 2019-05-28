import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class ClientsOrdersList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            orders: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <table border="1">
                {
                    this.createOrdersTable()
                }
            </table>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/client/" + this.props.match.params.id + "/orders")
            .then(value => {
                this.setState({
                    orders: value.data,
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

    createOrdersTable = () => {
        let ordersTable = [];

        for (let i = 0; i < this.state.orders.length; i++) {
            let order = this.state.orders[i];

            ordersTable.push(
                <tr>
                    <td>{order.id}</td>
                    <td>{order.id_client}</td>
                    <td>{order.is_done}</td>
                </tr>
            );
        }
        return ordersTable
    }
}