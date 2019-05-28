import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class DepotAdd extends Component {

    constructor(props) {
        super(props);

        this.state = {
            formItemId: '',
            formItemQuantity: '',
            isLoading: false,
            error: null,
        };
    }

    setFormItemId = event => {
        this.setState({formItemId: event.target.value});
    };

    setFormItemQuantity = event => {
        this.setState({formItemQuantity: event.target.value});
    };

    handleSubmit = event => {
        event.preventDefault();

        const depot = {
            id_product: parseInt(this.state.formItemId),
            quantity: parseInt(this.state.formItemQuantity)
        };

        axios
            .post('http://localhost:9000/administration/depot/add', {depot})
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
                        Item ID:<br/>
                        <input type="number" name="name" onChange={this.setFormItemId}/>
                    </label><br/>
                    <label>
                        Item quantity:<br/>
                        <input type="number" name="password" onChange={this.setFormItemQuantity}/>
                    </label><br/>
                    <button type="submit">Add</button>
                </form>
            </div>
        )
    }
}