import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class ProductDetails extends Component {

    constructor(props) {
        super(props);

        this.state = {
            product: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <div>
                <table>
                    {
                        this.createProductDetailsTable()
                    }
                </table>
            </div>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/products/" + this.props.match.params.id)
            .then(value => {
                this.setState({
                    product: value.data,
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

    createProductDetailsTable = () => {
        let productDetailsTable = [];

        if (this.state.product.length === 1) {
            let productDetails = this.state.product[0];
            console.log(productDetails);

            productDetailsTable.push(
                <tr>
                    <td>Numer ID:</td>
                    <td>{productDetails.id}</td>
                </tr>
            );

            productDetailsTable.push(
                <tr>
                    <td>ID kategorii:</td>
                    <td>{productDetails.id_category}</td>
                </tr>
            );

            productDetailsTable.push(
                <tr>
                    <td>ID producenta:</td>
                    <td>{productDetails.id_producer}</td>
                </tr>
            );

            productDetailsTable.push(
                <tr>
                    <td>Nazwa:</td>
                    <td>{productDetails.name}</td>
                </tr>
            );

            productDetailsTable.push(
                <tr>
                    <td>Opis:</td>
                    <td>{productDetails.description}</td>
                </tr>
            );

            productDetailsTable.push(
                <tr>
                    <td>Cena:</td>
                    <td>{productDetails.price}</td>
                </tr>
            );
        }

        return productDetailsTable
    }
}