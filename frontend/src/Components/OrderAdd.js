import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class OrderAdd extends Component {

    constructor(props) {
        super(props);

        this.state = {
            formClientId: '',
            formAddressId: '',
            formDate: '',
            isLoading: false,
            error: null,
        };
    }

    setFormClientId = event => {
        this.setState({formClientId: event.target.value});
    };

    setFormAddressId = event => {
        this.setState({formAddressId: event.target.value});
    };

    setFormDate = event => {
        this.setState({formDate: event.target.value});
    };

    handleSubmit = event => {
        event.preventDefault();

        const order = {
            id_client: parseInt(this.state.formClientId),
            id_address: parseInt(this.state.formAddressId),
            date: this.state.formDate,
            is_done: 0
        };

        axios
            .post('http://localhost:9000/order', {order})
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
                        Client ID:<br/>
                        <input type="number" name="clientId" onChange={this.setFormClientId}/>
                    </label><br/>
                    <label>
                        Address ID:<br/>
                        <input type="number" name="addressId" onChange={this.setFormAddressId}/>
                    </label><br/>
                    <label>
                        Date:<br/>
                        <input type="date" name="date" onChange={this.setFormDate}/>
                    </label><br/>
                    <button type="submit">Add</button>
                </form>
            </div>
        )
    }
}