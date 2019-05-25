import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class ProductsList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            products: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <nav>
                <ul>
                    {
                        this.createProductsList()
                    }
                    {/*<li><NavLink to="/products">Products</NavLink></li>*/}
                </ul>
            </nav>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/products")
            .then(value => {
                this.setState({
                    products: value.data,
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

    createProductsList = () => {
        let productsList = [];

        for (let i = 0; i < this.state.products.length; i++) {
            let product = this.state.products[i];

            productsList.push(<li id={product.id}>{product.name}</li>)
        }
        return productsList
    }
}