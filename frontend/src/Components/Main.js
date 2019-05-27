import React from 'react';
import {NavLink} from "react-router-dom";

export const Main = props => {
    return (
        <React.Fragment>
            <div>
                Strona glowna
            </div>
            <nav>
                <ul>
                    <li><NavLink to="/">Main</NavLink></li>
                    <li><NavLink to="/products">Products</NavLink></li>
                    <li><NavLink to="/products/add">Add Product</NavLink></li>
                    <li><NavLink to="/categories">Categories</NavLink></li>
                    <li><NavLink to="/categories/add">Add Category</NavLink></li>
                    <li><NavLink to="/producers">Producers</NavLink></li>
                    <li><NavLink to="/producers/add">Add Producer</NavLink></li>
                    <li><NavLink to="/countries">Countries</NavLink></li>
                    <li><NavLink to="/countries/add">Add Country</NavLink></li>
                    <li><NavLink to="/photos">Photos</NavLink></li>
                    <li><NavLink to="/accounts">Administration Accounts</NavLink></li>
                    <li><NavLink to="/clients">Administration Clients</NavLink></li>
                </ul>
            </nav>
        </React.Fragment>
    );
};
Main.propTypes = {};
