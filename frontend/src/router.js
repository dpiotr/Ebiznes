import React from 'react';
import {Route} from 'react-router-dom';
import {Main} from './Components/Main';
import {ProductsList} from './Components/ProductsList';
import {ProducersAdd} from './Components/ProducersAdd';
import {ProducersList} from './Components/ProducersList';
import {CategoriesList} from './Components/CategoriesList';
import {CategoriesAdd} from './Components/CategoriesAdd';
import {CountriesList} from './Components/CountriesList';
import {CountriesAdd} from './Components/CountriesAdd';
import {PhotosList} from './Components/PhotosList';
import {ClientsList} from './Components/ClientsList';
import {AccountsList} from './Components/AccountsList';

export default (
    <React.Fragment>
        <Route exact path="/" component={Main} myname={"Main page"}/>
        <Route path="/products" component={ProductsList}/>
        <Route path="/producers" component={ProducersList}/>
        <Route path="/producers/add" component={ProducersAdd}/>
        <Route path="/categories" component={CategoriesList}/>
        <Route path="/categories/add" component={CategoriesAdd}/>
        <Route path="/countries" component={CountriesList}/>
        <Route path="/countries/add" component={CountriesAdd}/>
        <Route path="/photos" component={PhotosList}/>
        <Route path="/accounts" component={AccountsList}/>
        <Route path="/clients" component={ClientsList}/>
    </React.Fragment>
);