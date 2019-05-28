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
                    <li><NavLink to="/photos/add">Photo Add</NavLink></li>
                    <li><NavLink to="/accounts">Accounts</NavLink></li>
                    <li><NavLink to="/clients/add">Account Add</NavLink></li>
                    <li><NavLink to="/clients">Clients</NavLink></li>
                    <li><NavLink to="/depot">Depot</NavLink></li>
                    <li><NavLink to="/depot/add">Add Depot</NavLink></li>
                    <li><NavLink to="/order/add">Add Order</NavLink></li>
                </ul>
            </nav>
        </React.Fragment>
    );
};
Main.propTypes = {};
