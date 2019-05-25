import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class AccountsList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            accounts: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <table border="1">
                {
                    this.createAccountsTable()
                }
            </table>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/administration/accounts")
            .then(value => {
                this.setState({
                    accounts: value.data,
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

    createAccountsTable = () => {
        let accountsTable = [];

        for (let i = 0; i < this.state.accounts.length; i++) {
            let account = this.state.accounts[i];

            accountsTable.push(
                <tr>
                    <td>{account.login}</td>
                    <td>{account.password}</td>
                </tr>
            );
        }
        return accountsTable
    }
}