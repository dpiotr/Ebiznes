import React, {Component} from 'react';
import {NavLink} from "react-router-dom";
import axios from 'axios';

export class PhotosList extends Component {

    constructor(props) {
        super(props);

        this.state = {
            photos: [],
            isLoading: false,
            error: null,
        };
    }

    render() {
        return (
            <nav>
                <ul>
                    {
                        this.createPhotosList()
                    }
                    {/*<li><NavLink to="/products">Products</NavLink></li>*/}
                </ul>
            </nav>
        );
    }

    componentDidMount() {
        this.setState({isLoading: true});

        axios
            .get("http://localhost:9000/photos")
            .then(value => {
                this.setState({
                    photos: value.data,
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

    createPhotosList = () => {
        let photosList = [];

        for (let i = 0; i < this.state.photos.length; i++) {
            let photo = this.state.photos[i];

            photosList.push(<li id={photo.id}>{photo.name}</li>)
        }
        return photosList
    }
}