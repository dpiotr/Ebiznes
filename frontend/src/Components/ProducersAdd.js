import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class ProducersAdd extends Component {

    state = {
        name: '',
    };

    handleChange = event => {
        this.setState(
            {
                name: event.target.value
            }
        );
    };

    handleSubmit = event => {
        event.preventDefault();

        const producer = {
            name: this.state.name
        };

        axios
            .post('http://localhost:9000/administration/producers/add', {producer})
            .then(res => {
                console.log(res);
                console.log(res.data);
            })
    };

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Producer name:<br/>
                        <input type="text" name="name" onChange={this.handleChange}/>
                    </label><br/>
                    <button type="submit">Add</button>
                </form>
            </div>
        )
    }
}