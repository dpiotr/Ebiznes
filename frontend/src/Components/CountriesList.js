import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class CountriesList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            countries: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <nav>
                <ul>
                    {
                        this.createCountriesList()
                    }
                    {/*<li><NavLink to="/products">Products</NavLink></li>*/}
                </ul>
            </nav>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/countries")
            .then(value => {
                this.setState({
                    countries: value.data,
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

    createCountriesList = () => {
        let countriesList = [];

        for (let i = 0; i < this.state.countries.length; i++) {
            let country = this.state.countries[i];

            countriesList.push(<li id={country.id}>{country.name}</li>)
        }
        return countriesList
    }
}