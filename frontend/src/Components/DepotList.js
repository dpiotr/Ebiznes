import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class DepotList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            depot: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <table border="1">
                {
                    this.createDepotTable()
                }
            </table>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/administration/depot")
            .then(value => {
                this.setState({
                    depot: value.data,
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

    createDepotTable = () => {
        let depotTable = [];

        for (let i = 0; i < this.state.depot.length; i++) {
            let item = this.state.depot[i];

            depotTable.push(
                <tr>
                    <td>{item.id_product}</td>
                    <td>{item.quantity}</td>
                </tr>
            );
        }
        return depotTable
    }
}