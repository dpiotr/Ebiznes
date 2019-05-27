import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class ProductsAdd extends Component {

    constructor(props) {
        super(props);

        this.state = {
            formName: '',
            formDescription: '',
            formProducerId: 0,
            formCategoryId: 0,
            formPrice: 0,
            formPhotoId: 0,
            productCategories: [],
            productProducers: [],
            productPhotos: [],
            isLoading: false,
            error: null,
        };
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios.all([
            axios.get('http://localhost:9000/producers'),
            axios.get('http://localhost:9000/categories'),
            axios.get('http://localhost:9000/photos')
        ])
            .then(axios.spread((producers, categories, photos) => {
                this.setState({
                    productCategories: categories.data,
                    productProducers: producers.data,
                    productPhotos: photos.data,
                    isLoading: false
                })
            }))
            .catch(reason => {
                this.setState({
                    error: reason,
                    isLoading: false
                })
            });
    }

    setFormProducerId = event => {
        this.setState({formProducerId: parseInt(event.target.value)});
    };

    setFormCategoryId = event => {
        this.setState({formCategoryId: parseInt(event.target.value)});
    };

    setFormPhotoId = event => {
        this.setState({formPhotoId: parseInt(event.target.value)});
    };

    setFormName = event => {
        this.setState({formName: event.target.value});
    };

    setFormDescription = event => {
        this.setState({formDescription: event.target.value});
    };

    setFormPrice = event => {
        this.setState({formPrice: parseInt(event.target.value)});
    };

    handleSubmit = event => {
        event.preventDefault();

        const product = {
            name: this.state.formName,
            description: this.state.formDescription,
            id_category: this.state.formCategoryId,
            id_producer: this.state.formProducerId,
            price: this.state.formPrice,
            id_photo: this.state.formPhotoId
        };

        axios
            .post('http://localhost:9000/administration/products/add', {product})
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
                        Product category:<br/>
                        <select onChange={this.setFormCategoryId}>
                            {
                                this.createProductsCategories()
                            }
                        </select>
                    </label><br/>
                    <label>
                        Product producer:<br/>
                        <select onChange={this.setFormProducerId}>
                            {
                                this.createProductsProducers()
                            }
                        </select>
                    </label><br/>
                    <label>
                        Product name:<br/>
                        <input type="text" name="name" onChange={this.setFormName}/>
                    </label><br/>
                    <label>
                        Product description:<br/>
                        <input type="text" name="description" onChange={this.setFormDescription}/>
                    </label><br/>
                    <label>
                        Price:<br/>
                        <input type="number" name="price" onChange={this.setFormPrice}/>
                    </label><br/>
                    <label>
                        Product photo:<br/>
                        <select onChange={this.setFormPhotoId}>
                            {
                                this.createProductsPhotos()
                            }
                        </select>
                    </label><br/>
                    <button type="submit">Add</button>
                </form>
            </div>
        )
    }

    createProductsCategories = () => {
        let productsCategories = [];

        for (let i = 0; i < this.state.productCategories.length; i++) {
            let category = this.state.productCategories[i];

            productsCategories.push(<option value={category.id}>{category.name}</option>)
        }

        return productsCategories
    };

    createProductsProducers = () => {
        let productsProducers = [];

        for (let i = 0; i < this.state.productProducers.length; i++) {
            let producer = this.state.productProducers[i];

            productsProducers.push(<option value={producer.id}>{producer.name}</option>)
        }

        return productsProducers
    };

    createProductsPhotos = () => {
        let productsPhotos = [];

        for (let i = 0; i < this.state.productPhotos.length; i++) {
            let photo = this.state.productPhotos[i];

            productsPhotos.push(<option value={photo.id}>{photo.name}</option>)
        }

        return productsPhotos
    }
}