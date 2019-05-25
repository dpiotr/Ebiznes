import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class CategoriesList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            categories: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <nav>
                <ul>
                    {
                        this.createCategoriesList()
                    }
                    {/*<li><NavLink to="/products">Products</NavLink></li>*/}
                </ul>
            </nav>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/categories")
            .then(value => {
                this.setState({
                    categories: value.data,
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

    createCategoriesList = () => {
        let categoriesList = [];

        for (let i = 0; i < this.state.categories.length; i++) {
            let category = this.state.categories[i];

            categoriesList.push(<li id={category.id}>{category.name}</li>)
        }
        return categoriesList
    }
}