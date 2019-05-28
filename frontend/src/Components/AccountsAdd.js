import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class AccountsAdd extends Component {

    constructor(props) {
        super(props);

        this.state = {
            formName: '',
            formPassword: '',
            isLoading: false,
            error: null,
        };
    }

    setFormName = event => {
        this.setState({formName: event.target.value});
    };

    setFormPassword = event => {
        this.setState({formPassword: event.target.value});
    };

    handleSubmit = event => {
        event.preventDefault();

        const account = {
            name: this.state.formName,
            password: this.state.formPassword
        };

        axios
            .post('http://localhost:9000/administration/accounts/add', {account})
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
            .catch(reason => {
                console.log("error: " + reason.message);
            })
    };

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Account name:<br/>
                        <input type="text" name="name" onChange={this.setFormName}/>
                    </label><br/>
                    <label>
                        Account password:<br/>
                        <input type="password" name="password" onChange={this.setFormPassword}/>
                    </label><br/>
                    <button type="submit">Add</button>
                </form>
            </div>
        )
    }
}