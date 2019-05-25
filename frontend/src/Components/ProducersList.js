import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class ProducersList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            producers: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <nav>
                <ul>
                    {
                        this.createProducersList()
                    }
                    {/*<li><NavLink to="/products">Products</NavLink></li>*/}
                </ul>
            </nav>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/producers")
            .then(value => {
                this.setState({
                    producers: value.data,
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

    createProducersList = () => {
        let producersList = [];

        for (let i = 0; i < this.state.producers.length; i++) {
            let producer = this.state.producers[i];

            producersList.push(<li id={producer.id}>{producer.name}</li>)
        }
        return producersList
    }
}